/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #4
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun 
 * 
 * This class represents a packet
 * in a simple network simulation.
 * 
 * This is basically a wrapper 
 * class for a series of integers,
 * each one representing a certain 
 * aspect of this packet.
 * 
 * @author Kuba Gasiorowski
 */
public class Packet {

	private static int packetCount;
	private int id;
	private int packetSize;
	private int timeArrive;
	private int timeToDest;
	
	/**
	 * Empty constructor for a packet. 
	 * Not to be used outside of this class.
	 */
	private Packet()
	{//Not to be used outside of this class
		
		packetCount++;
		id = packetCount;
		
	}
	
	/**
	 * Creates a new packet with the predefined size 
	 * and arrival time passed in.
	 * 
	 * @param size
	 * 		the size of this packet
	 * @param time
	 */
	public Packet(int size, int time)
	{
		
		this();
		
		timeArrive = time;
		packetSize = size;
		timeToDest = packetSize/100;
		
	}
	
	/**
	 * Returns the current number of packets being handled by the system.
	 * 
	 * @return
	 * 		the number of packets being handled by the system
	 */
	public static int getPacketCount(){return packetCount;}
	
	/**
	 * Sets the new number of packets currently being managed by the system.
	 * 
	 * @param in
	 * 		the new number of packets being managed in the system.
	 */
	public static void setPacketCount(int in){packetCount=in;}
	
	/**
	 * Sets the packet's size to whatever is passed in.
	 * 
	 * @param size
	 * 		the new size of the packet
	 */
	public void setPacketSize(int size){packetSize = size;}
	
	/**
	 * Returns the size of this packet.
	 * 
	 * @return
	 * 		the size of this packet
	 */
	public int getPacketSize(){return packetSize;}
	
	/**
	 * Sets the packet's ID to whatever is passed in.
	 * 
	 * @param id
	 * 		the new id of this packet
	 */
	public void setID(int id){this.id = id;}
	
	/**
	 * Returns the id of this packet.
	 * 
	 * @return
	 * 		the id of this packet
	 */
	public int getID(){return id;}
	
	/**
	 * Sets the new time this packet was generated to whatever is passed in.
	 * 
	 * @param timeArrive
	 * 		the new time this packet arrived
	 */
	public void setTimeArrive(int timeArrive){this.timeArrive = timeArrive;}
	
	/**
	 * Returns the time that this packet was generated.
	 * 
	 * @return
	 * 		the time this packet was generated
	 */
	public int getTimeArrive(){return timeArrive;}
	
	/**
	 * Sets the time until this packet reaches its location.
	 * 
	 * @param timeToDest
	 * 		the time until this packet reaches its location
	 */
	public void setTimeToDest(int timeToDest){this.timeToDest = timeToDest;}
	
	/**
	 * Returns the time until this packet arrives at its location.
	 * 
	 * @return
	 * 		the time until this packet reaches its location
	 */
	public int getTimeToDest(){return timeToDest;}
	
	/**
	 * Decrements the time spent getting sent to the destination.
	 * Should be called every system tick.
	 */
	public void decrementTimeToDest()
	{
		
		timeToDest--;
		
	}
	
	/**
	 * Returns a string representation of this packet.
	 * 
	 * @return
	 * 		a string representation of this object
	 */
	public String toString()
	{
		
		return "[" + id + ", " + timeArrive + ", " + timeToDest + "]";
		
	}
	
}
