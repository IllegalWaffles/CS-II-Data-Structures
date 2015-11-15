import java.io.*;
import java.util.Scanner;

/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #6
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * Main driver class behind this program.
 * Allows the user to interact and manipulate
 * an AuctionTable.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class AuctionSystem{
	
	/**
	 * Main driving method behind this entire program.
	 * Contains a simple UI so the user can interact
	 * with the AuctionTable.
	 * 
	 * @param args
	 * 		standard input arguments
	 */
	public static void main(String[] args)
	{
		
		boolean finished = false;
		Scanner sc = new Scanner(System.in);
		String input, parsedInput[];
		AuctionTable myTable = null;
		String username;
		
		//Code to read the auctiontable
		try{
			
			ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream("auction.obj"));
			
			System.out.print("Existing auction table detected. Load? (y/n)");
			
			String answer = sc.nextLine().toUpperCase();
			
			if(answer.equals("Y") || answer.equals("YES"))
			{
				
				myTable = (AuctionTable)fileIn.readObject();
				System.out.println("Auction table loaded from file.");
				
			}
			else
			{
				
				myTable = new AuctionTable();
				System.out.println("Auction table not loaded. Empty table created.");
				
			}
			
			fileIn.close();
			
		}
		catch(FileNotFoundException e)
		{
			
			System.out.println("File not detected. Creating new AuctionTable for this program");
			myTable = new AuctionTable();
			
		}
		catch(Exception e)
		{
			
			System.out.println("Something went wrong. I don't know what, but something did.");
			
		}
		
		
		System.out.print("LOGIN - Enter your username: ");
		username = sc.nextLine();	
		
		System.out.println("\n(D) - Import Data from URL\n"
						   + "(A) - Create a New Auction\n"
						   + "(B) - Bid on an Item\n"
						   + "(I) - Get Info on Auction\n"
						   + "(P) - Print All Auctions\n"
						   + "(R) - Remove Expired Auctions\n"
						   + "(T) - Let Time Pass\n"
						   + "(Q) - Quit");
		
		while(!finished)
		{
			
			System.out.print("> ");
			input = sc.nextLine().toUpperCase();
			parsedInput = input.split(" ");
			
			if(parsedInput[0].equals("Q") || parsedInput[0].equals("EXIT"))
			{
				
				System.out.println("Exiting..");
				finished = true;
				
			}
			else if(parsedInput[0].equals("D"))
			{
				
				System.out.print("URL: ");
				
				try{
				
					myTable = AuctionTable.buildFromUrl(sc.nextLine());
				
				}
				catch(IllegalArgumentException e)
				{
					
					System.out.println(e.getMessage());
					
				}
					
					
			}
			else if(parsedInput[0].equals("A"))
			{
				
				String newID = "", itemInfo;
				int timeRemaining;
				
				System.out.println("Creating a new Auction as " + username);
				
				try{
				
					System.out.print("Please enter an Auction ID: ");
					newID = sc.nextLine();
					
					System.out.print("Please enter an Auction time (hours): ");
					timeRemaining = Integer.parseInt(sc.nextLine());
					
					System.out.print("Please enter some Item Info: ");
					itemInfo = sc.nextLine();
					
					myTable.putAuction(newID, new Auction(newID, username, itemInfo, timeRemaining));
					
				}
				catch(NumberFormatException e)
				{
					
					System.out.println("Invalid input. Try again");
					
				}
				
				System.out.println("Auction " + newID + " added to table.");
				
				
			}
			else if(parsedInput[0].equals("B"))
			{
				
				System.out.print("Enter an auction ID to bid on: ");
				
				try{
					
					String auctionID = sc.nextLine();
					Auction toBidOn = myTable.getAuction(auctionID);
					
					if(toBidOn == null)
						throw new IllegalArgumentException("Auction " + auctionID + " not found.");
						
					if(toBidOn.getTimeRemaining() > 0)
					{
						
						System.out.print("Auction " + toBidOn.getAuctionID() + " is OPEN."
								 + "\nCurrent Bid: $ " + toBidOn.getCurrentBid()
								 + "\n"
								 + "What would you like to bid?: $");
						
						double  bidAmt = Double.parseDouble(sc.nextLine());
						
						toBidOn.newBid(username, bidAmt);
						
						System.out.println("Bid accepted.");
						
					}
					else
					{
						
						System.out.println("Auction " + toBidOn.getAuctionID() + " is CLOSED."
								+ "\nCurrent Bid: $ " + toBidOn.getCurrentBid()
								+ "\n"
								+ "You can no longer bid on this item.");
						
					}
					
				}
				catch(NumberFormatException e)
				{
					
					System.out.println("Input not valid. Try again");
					
				}
				catch(IllegalArgumentException e)
				{
						
					System.out.println(e.getMessage());
					
				}
				catch(ClosedAuctionException e){}
				
			}
			else if(parsedInput[0].equals("I"))
			{
				
				System.out.print("Enter an auction ID: ");
				
				try{
					
					myTable.printAuctionInfo(sc.nextLine());
				
				}
				catch(IllegalArgumentException e)
				{
						
					System.out.println(e.getMessage());
				
				}
			
			
			}
			else if(parsedInput[0].equals("P"))
			{
				
				myTable.printTable();
				
			}
			else if(parsedInput[0].equals("R"))
			{
				
				System.out.println("Removing closed auctions...");
				myTable.removeClosedAuctions();
				System.out.println("Done.");
				
			}
			else if(parsedInput[0].equals("T"))
			{
				
				System.out.print("How many hours to pass?\nHours:");
				int hours = 0;
				
				try{
				
					hours = Integer.parseInt(sc.nextLine());
				
				}
				catch(NumberFormatException e)
				{
					
					System.out.println("Invalid input. Try again");
					
				}
				
				myTable.letTimePass(hours);
				
			}
			else
			{
				
				System.out.println("Command not recognized. Try again");
				
			}
			
		}
		
		//Code to save the auctiontable
		System.out.print("Save the current auction table to file? (y/n): ");
		String answer = sc.nextLine().toUpperCase();
		
		if(answer.equals("Y") || answer.equals("YES"))
		{
			
			try{
			
				ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("auction.obj"));
				fileOut.writeObject(myTable);
				fileOut.close();
				System.out.println("Table saved. Goodbye!");
				
			}
			catch(Exception e)
			{
			
				System.out.println(e.getMessage() + " : something went wrong");
				
			}
				
		}
		else
		{
			
			System.out.print("Table not saved. Goodbye!");
			
		}
		
		sc.close();
		
	}
	
}