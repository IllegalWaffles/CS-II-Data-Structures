import java.io.Serializable;
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
public class AuctionSystem implements Serializable {

	private static AuctionTable myTable;
	private static String username;
	
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
		
		
		myTable = new AuctionTable();
		boolean finished = false;
		Scanner sc = new Scanner(System.in);
		String input, parsedInput[];
		
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
			
			if(parsedInput[0].equals("Q"))
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
				
				
				
			}
			else if(parsedInput[0].equals("B"))
			{
				
				
				
			}
			else if(parsedInput[0].equals("I"))
			{
				
				
				
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
				int hours = Integer.parseInt(sc.nextLine());
				
				myTable.letTimePass(hours);
				
			}
			else
			{
				
				System.out.println("Input not recognized. Try again");
				
			}
			
		}
		
		sc.close();
		
	}
	
	
}
