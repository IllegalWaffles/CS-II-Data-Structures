import java.util.ArrayList;

import javafx.scene.control.TextInputControl;

/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #4
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun 
 * 
 * This class represents a simple network
 * simulator. Packets arrive at a dispatcher,
 * if there is room in the intermediate routers,
 * the packet is sent there, otherwise the
 * packet is dropped. Once the packet is in an
 * intermediate router and its countdown reaches 
 * zero, it is considered sent.
 * 
 * Several factors come into play during the 
 * simulation, such as packet size, bandwidth,
 * and router buffer size. Changing each will
 * result in a different value for total service
 * time per packet on average.
 * 
 * This class is written in such a way that 
 * new simulators with different properties 
 * can easily be instantiated. Thus, a simulation
 * can be run once with output, or many times 
 * with no output to generate statistics.
 * 
 * Additionally, after implementing a GUI, a new 
 * set of output statements were added as well 
 * as a new field that holds the designated output 
 * textbox. If the textbox is a null reference,
 * the simulation simply does not print to the 
 * textbox to avoid NPEs.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class Simulator{
	
	Router dispatcher;
	ArrayList<Router> routers;
	
	//The running sum of the total time each packet is in the network
	int totalServiceTime;
	
	//Number of packets successfully arrived and packets dropped
	int packetsArrived, packetsDropped;
	
	//Probability of a new packet arriving at the dispatch
	double arrivalProb;
	
	//The number of intermediate routers in the system
	int numIntRouters;
	
	//The maximum number of packets a router can accomodate
	int maxBufferSize;
	
	//The minimum and maximum packet size
	int minPacketSize, maxPacketSize;
	
	//The number of packets the destination router can accept at a given simulation unit
	int bandwidth;
	
	//The number of simulation units
	int duration;
	
	//If output should be printed or not
	boolean consoleOutput;
	
	//The textfield that output will be printed to
	TextInputControl output;
	
	//The maximum number of packets that can be generated in one tick
	public static final int MAX_PACKETS = 3;
	
	
	/**
	 * Generic constructor for the simulate class. 
	 * I do not recommend using it as running the
	 * simulation with all values equal to 0 will
	 * result in a very fast and boring simulation.
	 */
	public Simulator()
	{
		
		output = null;
		
		totalServiceTime = 0;
		packetsArrived = 0;
		packetsDropped = 0;

		numIntRouters = 0;
		arrivalProb = 0;
		maxBufferSize = 0;
		minPacketSize = 0;
		maxPacketSize = 0;
		bandwidth = 0;
		consoleOutput = false;
		
	}
	
	/**
	 * Constructor to initialize this simulator to the desired values.
	 * This was done to create more flexibility of the
	 * program, so a simulation could easily be run just once with
	 * output or several hundreds of thousands of times without input
	 * to generate trends from large amounts of data.
	 * 
	 * @param numIntRouters
	 * 		the number of intermediate values
	 * @param arrivalProb
	 * 		the probability of a new packet being generated
	 * @param maxBufferSize
	 * 		the maximum number of packets a router can hold at any given time
	 * @param minPacketSize
	 * 		the smallest size a packet can have
	 * @param maxPacketSize
	 * 		the largest size a packet can have
	 * @param bandwidth
	 * 		the number of packets that can be sent to the destination at
	 * 		any given time
	 * @param duration
	 * 		the number of time units this simulation will take
	 * @param consoleOutput
	 * 		if true, the simulation will generate full output to the console;
	 * 		otherwise, it will print nothing to the console (but still run)
	 * @param output
	 * 		the program will print output to this textbox. if it points to a
	 * 		null reference, the program does not output to any textbox
	 */
	public Simulator(int numIntRouters, double arrivalProb, int maxBufferSize, int minPacketSize, int maxPacketSize, int bandwidth, int duration, boolean fullOutput, TextInputControl output)
	{
		
		this();
		this.output = output;
		this.numIntRouters = numIntRouters;
		this.arrivalProb = arrivalProb;
		this.maxBufferSize = maxBufferSize;
		this.minPacketSize = minPacketSize;
		this.maxPacketSize = maxPacketSize;
		this.bandwidth = bandwidth;
		this.duration = duration;
		this.consoleOutput = fullOutput;
		
	}
	
	/**
	 * When called, runs the simulation with the current
	 * field values. This can be called any number of
	 * times without changing the variables in order
	 * to study trends, such as what happens when numbers
	 * are lowered or raised.
	 * 
	 * @return
	 * 		the average service time per packet
	 */
	public double simulate()
	{
		
		boolean printToTextBox = (output != null);
		
		if(consoleOutput)
			System.out.println("Starting simulator...\n");
			
		if(printToTextBox)
			output.appendText("Starting simulator...\n\n");
		
		//Initialize everything to 0
		totalServiceTime = 0;
		packetsArrived = 0;
		packetsDropped = 0;
		Packet.setPacketCount(0);
		
		if(consoleOutput)
			System.out.println("\n---STARTING SIMULATION---\n");
			
		if(printToTextBox)
			output.appendText("\n---STARTING SIMULATION---\n\n");
			
		routers = new ArrayList<Router>(numIntRouters);
		
		//Initialize the intermediate routers
		for(int i = 0; i<numIntRouters;i++)
			routers.add(new Router(maxBufferSize));
		
		Router dispatch = new Router(Integer.MAX_VALUE);//Our dispatch has no maximum buffer length
														//Well technically it does but it's really 
														//really large
		
		for(int time = 1; time <= duration; time++){

			if(consoleOutput)
				System.out.println("\nTime: " + time);
			
			if(printToTextBox)
				output.appendText("\nTime: " + time + "\n");
			
			int oldPacketCount = Packet.getPacketCount();
			
			for(int i = 0; i < MAX_PACKETS; i++)
				if(Math.random() <= arrivalProb)
				{
					
					int packetSize = randInt(minPacketSize, maxPacketSize);
					dispatch.enqueue(new Packet(packetSize, time));
				
					if(consoleOutput)
						System.out.println("Packet " + Packet.getPacketCount() + " arrives at dispatcher with size " + packetSize + ".");
					
					if(printToTextBox)
						output.appendText("Packet " + Packet.getPacketCount() + " arrives at dispatcher with size " + packetSize + ".\n");
					
				}//Repeat this process three times (three possible new packets generated)
			
			if(Packet.getPacketCount() == oldPacketCount && consoleOutput)
				System.out.println("No packets arrived.");
			
			if(Packet.getPacketCount() == oldPacketCount && printToTextBox)
				output.appendText("No packets arrived.\n");
				
			while(dispatch.size() > 0)
			{
				
				Packet toSend = dispatch.dequeue();
				int routerToSendTo;
				
				try{
					
					routerToSendTo = Router.sendPacketTo(routers);
					routers.get(routerToSendTo).enqueue(toSend);
				
					if(consoleOutput)
						System.out.println("Packet " + toSend.getID() + " sent to Router " + (routerToSendTo+1));
						
					if(printToTextBox)
						output.appendText("Packet " + toSend.getID() + " sent to Router " + (routerToSendTo+1) + "\n");
						
					
						
						
				}
				catch(FullRoutersException e)
				{
				
					if(consoleOutput)
						System.out.println("Network congested. Packet " + toSend.getID() + " dropped.");
					
					if(printToTextBox)
						output.appendText("Network congested. Packet " + toSend.getID() + " dropped.\n");
						
					packetsDropped++;
					
				}
				
				
			}
			
			//Decrement each packet at the beginning of each router (queue)
			
			int numPacketsSent = 0;
			
			for(int i = 0; i < routers.size(); i++)
			{
				
				if(!(routers.get(i).size() == 0))
				{//If the router is empty, skip it
					
					//If the current packet's countdown is 0, theres no need to decrement it
					if(routers.get(i).peekFront().getTimeToDest() > 0)
						routers.get(i).peekFront().decrementTimeToDest();

					
					if(routers.get(i).peekFront().getTimeToDest() <= 0)
					{//If this packet is ready to be transmitted
					
						//If the number of packets sent > bandwidth, skip the rest of this code
						if(numPacketsSent >= bandwidth)
							continue;
						
						Packet arrivedPacket = routers.get(i).dequeue();
						
						if(consoleOutput)
							System.out.println("Packet " + arrivedPacket.getID() + " arrived at destination. +" + (time - arrivedPacket.getTimeArrive()));
						
						if(printToTextBox)
							output.appendText("Packet " + arrivedPacket.getID() + " arrived at destination. +" + (time - arrivedPacket.getTimeArrive()) + "\n");
						
						packetsArrived++;
						totalServiceTime += time - arrivedPacket.getTimeArrive();
						
						numPacketsSent++;
						
					}
				}	
			}
			
			//Displays the intermediate routers
			if(consoleOutput)
				for(int i = 0; i < routers.size(); i++)
					System.out.println("R" + (i+1) + ": " + routers.get(i));
			
			if(printToTextBox)
				for(int i = 0; i < routers.size(); i++)
					output.appendText("R" + (i+1) + ": " + routers.get(i) + "\n");
			
		}
			
		if(consoleOutput){
			
			System.out.println("\nSimulation ending...");
			
			System.out.println("Total service time: " + totalServiceTime);
			System.out.println("Total packets served: " + packetsArrived);
			System.out.println("Average service time per packet: " + ((double)totalServiceTime)/packetsArrived);
			System.out.println("Total packets dropped: " + packetsDropped);
			
		}
		
		if(printToTextBox)
		{
			
			output.appendText("\nSimulation ending...\n");
			
			output.appendText("Total packets served: " + packetsArrived + "\n");
			output.appendText("Total service time: " + totalServiceTime + "\n");
			output.appendText("Average service time per packet: " + ((double)totalServiceTime)/packetsArrived + "\n");
			output.appendText("Total packets dropped: " + packetsDropped + "\n");
			
		}
		
		return ((double)totalServiceTime)/packetsArrived;
		
	}
	
	/**
	 * Returns a random integer between minVal and maxVal, inclusive.
	 * 
	 * @param minVal
	 * 		minimum random value
	 * @param maxVal
	 * 		maximum random value
	 * @return
	 * 		a random integer between minVal and maxVal, inclusive
	 */
	private int randInt(int minVal, int maxVal)
	{
		
		return minVal + (int)(Math.random() * ((maxVal - minVal) + 1));
		
	}
	
}
