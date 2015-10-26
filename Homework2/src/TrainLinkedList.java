/**
 * 
 * @author Kuba Gasiorowski
 *
 */
public class TrainLinkedList {

	private TrainCarNode head, tail, cursor;
	private int numCars; 
	private double totalLength, totalWeight, totalValue;
	private boolean isDangerous;
	
	public TrainLinkedList()
	{
		
		head = null;
		tail = null;
		cursor = null;
		numCars = 0;
		totalLength = 0;
		totalWeight = 0;
		isDangerous = false;
		
	}
	
	public TrainCarNode getCursor(){return cursor;}
	
	public TrainCar getCursorData()
	{
	
		return cursor.getTrainCar();
		
	}
	
	public void setCursorData(TrainCar car)
	{
	
		cursor.setTrainCar(car);
		
	}
	
	public void cursorForward(){cursor = cursor.getNext();}
	
	public void cursorBackward(){cursor = cursor.getPrev();}
	
	public void insertAfterCursor(TrainCar newCar) throws IllegalArgumentException
	{
		
		if(newCar==null)
			throw new IllegalArgumentException();
		
		TrainCarNode newNode = new TrainCarNode(newCar);
		
		/*
		 * Needs the following cases:
		 * size() == 0
		 * size() == 1
		 * size() > 1
		 * cursor == tail
		 * 
		 */
		
		if(size() == 0)
		{
			
			tail = newNode;
			head = newNode;
			
		}
		else if(cursor == tail)
		{
			
			cursor.setNext(newNode);
			newNode.setPrev(cursor);
			
			tail = cursor.getNext();
			
		}
		else if(size() == 1)
		{
			
			cursor.setNext(newNode);
			newNode.setPrev(cursor);
			
		}
		else if(size() > 1)
		{
			
			//Sets the links from the new node to the list
			newNode.setPrev(cursor);
			newNode.setNext(cursor.getNext());
			
			//Sets the links 
			cursor.getNext().setPrev(newNode);
			cursor.setNext(newNode);
			
		}
		
		cursor = newNode;
		numCars++;
		
	}
	
	public TrainCar removeCursor() throws EmptyListException
	{
		
		if(cursor == null)
			throw new EmptyListException();
		
		TrainCar toReturn = cursor.getTrainCar();
		
		/*
		 * Needs the following cases:
		 * size() == 0 (do nothing)
		 * size() == 1 (list is empty)
		 * cursor == head, tail
		 * size() > 0
		 * 
		 */
		
		
		if(size() == 1)
		{
			
			//The list is now empty
			head = null;
			tail = null;
			cursor = null;
			
		}
		else if(cursor == head)
		{
			
			head.getNext().setPrev(null);//Erases the connection from the second node to the head
			head = head.getNext();//Sets the head as the next node
			
			cursor = head;
			
		}
		else if(cursor == tail)
		{
			
			//Same as above except with the tail
			tail.getPrev().setNext(null);
			tail = tail.getPrev();
			
			cursor = tail;
			
		}
		
		numCars--;
		return toReturn;
		
	}
	
	/**
	 * Returns the current number of cars in the train, or the size.
	 * 
	 * @return
	 * 		the size of the linked list
	 */
	public int size(){return numCars;}
	
	/**
	 * Returns the entire length of the train.
	 * 
	 * @return
	 * 		the total length of the train
	 */
	public double getLength(){return totalLength;}
	
	/**
	 * Returns the total value of the train
	 * 
	 * @return
	 * 		returns the total value of the train
	 */
	public double getValue(){return totalValue;}
	
	/**
	 * Returns the total weight of the train
	 * 
	 * @return
	 * 		returns the weight of the train
	 */
	public double getWeight(){return totalWeight;}
	
	/**
	 * Returns if the train has a dangerous car or not.
	 * 
	 * @return
	 * 		if the train is dangerous
	 */
	public boolean isDangerous(){return isDangerous;}
	
	/**
	 * A helper method which cycles through the linked list and returns true if there is
	 * at least one dangerous car in the list. Returns false if all of the traincars are safe.
	 * 
	 * Call this method when a TrainCarNode is added or removed!!!
	 * 
	 * @return
	 * 		if the train has a dangerous car
	 */
	public void updateIfDangerous()
	{
		
		TrainCarNode cursorPosition = cursor;//Saves the position of the cursor
		
		cursor = head;//Start at the top of the list
		
		while(cursor != null)
		{
			
			if(cursor.getTrainCar().isDangerous()){
			
				cursor = cursorPosition;
				isDangerous = true;
				return;
			
			}
			
			cursorForward();
			
		}
		
		cursor = cursorPosition;
		isDangerous = false;
		
	}
	
	public void updateTotalLength()
	{
		
		totalLength = 0;
		
		TrainCarNode savedCursor = cursor;
		cursor = head;
		
		while(cursor != null)
		{
			
			totalLength += cursor.getTrainCar().getCarLength();
			cursorForward();
			
		}
		
		cursor = savedCursor;
		
	}
	
	public void updateTotalWeight()
	{
		
		totalWeight = 0;
		
		TrainCarNode savedCursor = cursor;
		cursor = head;
		
		while(cursor != null)
		{
			
			if(cursor.getTrainCar().getProductLoad() == null)
				totalWeight += cursor.getTrainCar().getCarWeight();
			else
				totalWeight += cursor.getTrainCar().getCarWeight() + cursor.getTrainCar().getProductLoad().getWeight();
			
			cursorForward();
			
		}
		
		cursor = savedCursor;
		
	}
	
	public void updateTotalValue()
	{
		
		totalValue = 0;
		
		TrainCarNode savedCursor = cursor;
		cursor = head;
		
		while(cursor != null)
		{
			
			if(cursor.getTrainCar().getProductLoad() != null)
				totalValue += cursor.getTrainCar().getProductLoad().getValue();
			
			cursorForward();
			
		}
		
		cursor = savedCursor;
		
	}
	
	public void findProduct(String name)
	{
		
		int counter = 0, totalWeight = 0, totalValue = 0;
		boolean isDangerous = false;
		TrainCarNode savedCursor = cursor;
		cursor = head;
		
		while(cursor != null)
		{
			
			if(cursor.getTrainCar().getProductLoad() != null && cursor.getTrainCar().getProductLoad().getName().equals(name)){
				
				counter++;
				
				isDangerous = cursor.getTrainCar().getProductLoad().isDangerous();
				totalWeight += cursor.getTrainCar().getProductLoad().getWeight();
				totalValue += cursor.getTrainCar().getProductLoad().getValue();
				
			}
			
			cursorForward();
			
		}
		
		if(counter == 0)
			System.out.println("No matches found on the current train.");
		else
		{
			
			
			System.out.println( counter + " matches found on the current train for " + name + ":\n");
			System.out.println("    Name      Weight (t)     Value ($)   Dangerous");
			System.out.println("===================================================");
			
			Object outArray[] = new Object[4];
			
			outArray[0] = name;
			outArray[1] = new Double(totalWeight);
			outArray[2] = new Double(totalValue);
			outArray[3] = new Boolean(isDangerous);
			
			System.out.println(String.format("%10s%14.1f%14.2f%12s", outArray));
			
		}
	
		cursor = savedCursor;
		
	}
	
	public void printManifest()
	{
		
		System.out.println("Train: " + numCars + " cars, " + totalLength + " meters, " + totalWeight + " tons, $" + totalValue + ", " + isDangerous + ".");
		
	}
	
	/**
	 * Cycles through the linked list, removing each dangerous car on the way.
	 */
	public void removeDangerousCars()
	{
		
		//Start at the top of the LL
		cursor = head;
		
		while(cursor != null)//Cycles through the LL
		{
			
			if(!cursor.getTrainCar().isEmpty() && cursor.getTrainCar().getProductLoad().isDangerous())
			{	//If the TrainCar is not empty, and dangerous, attempt to remove
				
				try{
				
					removeCursor();
				
				}catch(EmptyListException e){System.out.println("CONSOLE: System: There was an issue.");}
				
			}
			
			if(cursor == null)//Checks if the cursor is null again.
			{				  //This could cause a NullPointerException if not for this second check.
				
				head = null;
				return;
				
			}
			
			cursorForward();
			
		}
		
	}
	
	/**
	 * Displays each train car sequentially. An arrow is printed to the left of the cursor.
	 * 
	 * @return
	 * 		a string representation of this object
	 */
	public String toString()
	{
		
		int i = 1;
		TrainCarNode save = cursor;
		String out = "", format = "%4d";
		
		Object[] outArray = new Object[1];
		
		cursor = head;
		
		while(cursor != null)
		{
			
			outArray[0] = new Integer(i);
			
			if(cursor == save)
				out+="-->" + String.format(format, outArray) + cursor.getTrainCar() + "\n";
			else
				out+="   " + String.format(format, outArray) + cursor.getTrainCar() + "\n";
			
			i++;
			cursorForward();
			
		}
		
		cursor = save;
		
		return out;
		
	}
	
}
