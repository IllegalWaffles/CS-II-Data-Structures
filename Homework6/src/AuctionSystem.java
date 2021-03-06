import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;

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
		
		File data = new File("data");
		
		//Dir to hold auctions is called "data"
		//If it does not exist, create it
		if(data.exists() && data.isDirectory())
			System.out.println("Data directory detected, continuing...");
		else if(!data.mkdir())
			System.out.println("These was a problem with creating the data directory");
		else{
			System.out.println("Data directory missing! Generating new Data Directory...\nData directory successfully generated...");
		}
		//If it is empty, automatically create a new auctiontable
		if(data.list().length == 0)
		{
			
			myTable = new AuctionTable();
			System.out.println("No saved data detected: generating new auction table...");
			
		}
		else //If it is not, ask user if they want to load a new table
		{
			
			System.out.print("Saved auctions detected. Load from file? (y/n): ");
			String answer = sc.nextLine().toUpperCase();
			
			if(answer.equals("Y") || answer.equals("YES"))
			{
			
				for(String file: data.list())
					System.out.print("\"" + file + "\"" + " ");
					
				System.out.print("\nAuction to load (do not include .obj):");
				String auctionToRead = "data/" + sc.nextLine() + ".obj";
			
				try{
			
					ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(auctionToRead));
					myTable = (AuctionTable)fileIn.readObject();
					fileIn.close();
				
				}
				catch(FileNotFoundException e)
				{
				
					System.out.println(auctionToRead + " was not found.\nAborting program, try again!");
					sc.close();
					return;
					
				}
				catch(Exception e)
				{
				
					System.out.println(e.getMessage() + ": Something went wrong");
				
				}
			
			
			}
			else
			{
				
				System.out.println("New auction table generated.");
				myTable = new AuctionTable();
				
			}
			
		}
		//Proceed as normal
		
		System.out.print("\nLOGIN - Enter your username: ");
		username = sc.nextLine();	
		
		System.out.println("\n(D) - Import Data from URL (WARNING: This will overwrite the current auction table\n"
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
				
				//Code to add a function
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
				
				//Code to bid on an auction
				System.out.print("Enter an auction ID to bid on: ");
				
				try{
					
					String auctionID = sc.nextLine();
					Auction toBidOn = myTable.getAuction(auctionID);
					
					if(toBidOn == null)
						throw new IllegalArgumentException("Auction " + auctionID + " not found.");
						
					//Checks if the auction is closed
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
				//Technically this exception will never be thrown since it's already checked for
				catch(ClosedAuctionException e){}
				
			}
			else if(parsedInput[0].equals("I"))
			{
				
				//Code to get information on a specific auction
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
				
				//Prints a neatly formatted table of auctions
				myTable.printTable();
				
			}
			else if(parsedInput[0].equals("R"))
			{
				
				//Removes all closed auctions from this table
				System.out.println("Removing closed auctions...");
				myTable.removeClosedAuctions();
				System.out.println("Done.");
				
			}
			else if(parsedInput[0].equals("T"))
			{
				
				//Passes a certain number of hours over each auction
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
				
				//Otherwise, the input was wrong
				System.out.println("Command not recognized. Try again");
				
			}
			
		}
		
		//Code to save the auctiontable
		
		//save the auction as this name under "data"
		
		//Otherwise terminate the program
		
		
		//Prompts to save the auction table (y/n)
		System.out.print("Save the current auction table to file? (y/n): ");
		String answer = sc.nextLine().toUpperCase();
		
		if(answer.equals("Y") || answer.equals("YES"))
		{
			
			//If yes, prompt for a name
			System.out.print("Name to save as (do not include .obj): ");
			String fileName = "data/" + sc.nextLine() + ".obj";
			
			try{
			
				ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream(fileName));
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