import java.util.*;

/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #4
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun 
 * 
 * This class represents a simple router
 * capable of holding a number of packets.
 * 
 * This class essentially represents a 
 * queue of packets, with the front of
 * the queue being the end of the list
 * and the end of the queue being at 
 * the front of the list.
 * 
 * Please note that the default constructor is
 * not included.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class Router extends LinkedList<Packet>{
	
	 private int maxBufferSize;
	
	/**
	 * Creates a new router object with a predefined maximum buffer length.
	 * Basically acts as a queue. Uses a linkedlist.
	 */
	public Router(int maxSize)
	{
		
		super();
		maxBufferSize = maxSize;
		
	}
	
	/**
	 * Adds a new packet to the back of the queue (which is the front of the list)
	 * 
	 * @param newPacket
	 * 		the new packet to be added to the front of the queue
	 */
	public void enqueue(Packet newPacket)
	{
		
		addFirst(newPacket);
		
	}
	
	/**
	 * Removes the packet at the front of the queue (which is the back of the list)
	 * 
	 * @return
	 * 		the packet that is popped off the end of the list
	 */
	public Packet dequeue()
	{
		
		return removeLast();
		
	}
	
	/**
	 * Returns the packet at the front of the queue,
	 * does not modify the queue. The front of the queue
	 * is the last object in the list.
	 * 
	 * @return
	 * 		the packet at the front of the queue
	 */
	public Packet peekFront()
	{
		
		return getLast();
		
	}
	
	/**
	 * Returns the packet at the back of the queue, does not
	 * modify the queue. The back of the queue is the first
	 * object in the list.
	 * 
	 * @return
	 * 		the packet at the back of the queue
	 */
	public Packet peekBack()
	{
		
		return getFirst();
		
	}
	
	/**
	 * Returns the maximum buffer size of this router.
	 * 
	 * @return
	 * 		the maximum buffer size of this router
	 */
	public int getMaxBufferSize()
	{
		
		return maxBufferSize;
		
	}
	
	/**
	 * Sets the maximum buffer to 'in'.
	 * 
	 * @param in
	 * 		sets the new buffer limit of this router
	 */
	public void setMaxBufferSize(int in)
	{
		
		maxBufferSize = in;
		
	}
	
	/**
	 * Returns a string representation of this object. 
	 * 
	 * If the router is empty, returns two curly braces.
	 * 
	 * Otherwise, cycles through each element and adds it
	 * to the end of the string.
	 */
	public String toString()
	{
		
		if(size() == 0)
			return "{}";
		
		String toReturn = "";
		Object[] myArray = this.toArray();
		
		for(int i = myArray.length-1; i > 0; i--)
			toReturn += (Packet)myArray[i] + ", ";
		
		toReturn += myArray[0];
		
		return "{" + toReturn + "}";
		
	}
	
	/**
	 * Returns an int corresponding to the index of which router
	 * a new packet should be sent to. 
	 * 
	 * @param routers
	 * 		the collection of routers to choose from
	 * @return
	 * 		an int corresponding to the index of the router to send a packet to
	 * @throws FullRoutersException
	 * 		indicated all routers are full to capacity
	 */
	public static int sendPacketTo(Collection<Router> routers /*, int maxBufferSize*/) throws FullRoutersException
	{
		
		/*
		 * There are many comments in this method
		 * 
		 * I had to do a lot of output to 
		 * debug my code.
		 * 
		 * I commented it all out because it 
		 * was not part of defaul toutput.
		 */
		
		ArrayList<Router> routerList = (ArrayList<Router>)routers;
		//Cast as arraylist. Makes it easier to deal with
		
		//This method assumes that each router in the collection has the same maximum buffer length
		int maxBufferSize = routerList.get(0).getMaxBufferSize();
		
		boolean routersAreFull = true;
		
		for(int i = 0; i < routerList.size(); i++)
		{//Cycles through the the routers
			
			//System.out.print("(max buffer size: " + maxBufferSize + ") ");
			//System.out.print("Checking router at index " + i + ": ");
			
			
			
			if(routerList.get(i).size() < maxBufferSize)
			{//Checks if they're full
			
				routersAreFull = false;//If at least one is not full, set it to false
				//System.out.println("Not full.");
				
			}
			else
			{
				
				//System.out.println("Is full.");
				
			}
				
		}
		
		//System.out.println("Routers are full: " + routersAreFull + "\n");
		
		if(routersAreFull)
			throw new FullRoutersException();
		
		
		//Finds the router with the smallest packet buffer (least packets)
		
		int smallest = Integer.MAX_VALUE;
		int index = -1;
		
		for(int i = 0; i < routerList.size(); i++)//Loops through the collection
		{
			
			if(routerList.get(i).size() < smallest)
			{//If the packet buffer is smaller than the current packet buffer
				
				smallest = routerList.get(i).size();//Reset the smallest packet buffer
				index = i;//Save the index of this router
				
			}
			
		}
		
		return index;//Returns the index of the router with the smallest packet buffer
		
	}
	
	
}