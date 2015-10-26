/*
 *  
 * Finish javadoc implementation
 * Finish input validation
 * Finish comments
 * Fix error with remove all dangerous cars - cursor disappears after certain cases
 */

/**
 * 
 * @author Kuba Gasiorowski
 *
 */

import java.util.Scanner;

public class TrainManager {


	public static void main(String[] args) throws NumberFormatException
	{	
		
		Scanner in = new Scanner(System.in);
		boolean done = false;
		String firstToken;
		String[] input;
		
		TrainLinkedList myList = new TrainLinkedList();
		
		System.out.println("(F) Cursor Forward\n" + 
						   "(B) Cursor Backward\n" + 
						   "(I) Insert Car After Cursor\n" + 
						   "(R) Remove Car At Cursor\n" + 
						   "(L) Set Product Load\n" +
						   "(S) Search For Product\n" + 
						   "(T) Display Train\n" + 
						   "(M) Display Manifest\n" + 
						   "(D) Remove Dangerous Cars\n" + 
						   "(Q) Quit\n");
		
		
		
		while(!done)
		{
			
			System.out.print("\nEnter a selection: ");
			
			input = in.nextLine().split(" ");
			firstToken = input[0];
			
			myList.updateIfDangerous();
			myList.updateTotalLength();
			myList.updateTotalWeight();
			myList.updateTotalValue();
			
			System.out.println("~~~" + myList.isDangerous() + "~~~");
			
			if(firstToken.toUpperCase().equals("F"))
			{

				if(myList.size() == 0)
					System.out.println("CONSOLE: Cannot move - the list is empty!");
				else if(myList.size() == 1)
					System.out.println("CONSOLE: Cannot move - there's only one node!");
				else if(myList.getCursor().getNext() == null)
					System.out.println("CONSOLE: End of list reached - can't move");
				else
				{
					
					myList.cursorForward();
					System.out.println("CONSOLE: Cursor successfully moved forwards");
					
				}
				
			}
			else if(firstToken.toUpperCase().equals("B"))
			{
				
				if(myList.size() == 0)
					System.out.println("CONSOLE: Cannot move - the list is empty!");
				else if(myList.size() == 1)
					System.out.println("CONSOLE: Cannot move - there's only one node!");
				else if(myList.getCursor().getPrev() == null)
					System.out.println("CONSOLE: End of list reached - can't move");
				else
				{
					
					myList.cursorBackward();
					System.out.println("CONSOLE: Cursor successfully moved backwards");
					
				}
				
			}
			else if(firstToken.toUpperCase().equals("I"))
			{

				//Insert Car After Cursor <Length> <Weight>

				System.out.print("\nCONSOLE: Enter Car Length:"); 
				double carLength = Integer.parseInt(in.nextLine());
				System.out.print("CONSOLE: Enter Car Weight:");
				double carWeight = Integer.parseInt(in.nextLine());
				
				myList.insertAfterCursor(new TrainCar(carLength, carWeight));
				
				System.out.println("CONSOLE: New train car " + carLength + " meters " + carWeight + " tons inserted into train.");
				
				
			}
			else if(firstToken.toUpperCase().equals("R"))
			{
				
				//Remove Car At Cursor 
				try{
				
					myList.removeCursor();
					
				}catch(EmptyListException e){
					System.out.println("CONSOLE: Cannot remove - empty list!");
				}
				
			}
			else if(firstToken.toUpperCase().equals("L"))
			{
				
				//Set Load At Cursor <Name> <Weight> <Value> <Is Dangerous> 
				
				String name = "";
				double weight = 0, value = 0;
				boolean isDangerous = false;
				
				try{
				
					if(myList.getCursor() == null)
						System.out.println("CONSOLE: Cannot load a product into nothing");
					else
					{
					
						System.out.print("\nEnter product name: ");
						name = in.nextLine();
				
						System.out.print("Enter product weight in tons: ");
						weight = Double.parseDouble(in.nextLine());
				
						System.out.print("Enter product value in dollars: ");
						value = Double.parseDouble(in.nextLine());
				
						System.out.print("Is product dangerous? (y/n): ");
						String danger = in.nextLine().toUpperCase();
					
						if(danger.equals("Y") || danger.equals("YES"))
							isDangerous = true;
						else if(danger.equals("N") || danger.equals("NO"))
							isDangerous = false;
						else
							throw new NumberFormatException();
				
						TrainCar newCar = myList.getCursor().getTrainCar();
				
						newCar.setProductLoad(new ProductLoad(name, weight, value, isDangerous));
					
					}
				
				}
				catch(NumberFormatException e)
				{
					
					System.out.println("CONSOLE: Bad input! Try again.");
					
				}
				
			}
			else if(firstToken.toUpperCase().equals("S"))
			{
				
				//Search For Product <name> 
				System.out.print("\nEnter Product Name: ");
				String toSearchFor = in.nextLine();
				
				myList.findProduct(toSearchFor);
				
			}
			else if(firstToken.toUpperCase().equals("T"))
			{
				
				//Print Train 
				myList.printManifest();
				
			}
			else if(firstToken.toUpperCase().equals("M"))
			{
				
				//Print Manifest 
				displayTopOutputBar();
				System.out.println(myList);
				
			}
			else if(firstToken.toUpperCase().equals("D"))
			{
				
				//Remove Dangerous Cars 
				String decision;
				System.out.print("CONSOLE: Are you sure you want to remove all dangerous cars? (y/n): ");
				decision = in.nextLine().toUpperCase();
				
				if(decision.equals("YES") || decision.equals("Y"))
				{
					myList.removeDangerousCars();
					System.out.println("CONSOLE: Dangerous cars removed.");
				}
				else
					System.out.println("CONSOLE: Removal aborted.");
				
			}
			else if(firstToken.toUpperCase().equals("Q") || input[0].toUpperCase().equals("EXIT"))
			{
				
				System.out.println("\n\nCONSOLE: Terminating the program...");
				done = true;
				
			}
			else
			{
				
				System.out.println("CONSOLE: Error: input not recognized! Try again");
				
			}
			
		}
		
		in.close();
	
		
	}
	
	private static void displayTopOutputBar()
	{
		
		System.out.println("     Num   Length (m)    Weight (t)  |    Name      Weight (t)     Value ($)   Dangerous");
		System.out.println("   ==================================+==================================================");
		
	}
	
}
