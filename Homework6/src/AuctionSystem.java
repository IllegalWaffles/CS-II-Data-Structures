import java.io.Serializable;
import java.util.Scanner;

public class AuctionSystem implements Serializable {

	private AuctionTable myTable;
	private String username;
	
	public static void main(String[] args)
	{
		
		boolean finished = false;
		Scanner sc = new Scanner(System.in);
		String input, parsedInput[];
		
		System.out.println("(D) - Import Data from URL\n"
				+ "(A) - Create a New Auction\n"
				+ "(B) - Bid on an Item\n"
				+ "(I) - Get Info on Auction\n"
				+ "(P) - Print All Auctions\n"
				+ "(R) - Remove Expired Auctions\n"
				+ "(T) - Let Time Pass\n"
				+ "(Q) - Quit");
		
		while(!finished)
		{
			
			System.out.print(" > ");
			input = sc.nextLine().toUpperCase();
			parsedInput = input.split(" ");
			
			if(parsedInput[0].equals("Q"))
			{
				
				System.out.println("\nExiting..");
				finished = true;
				
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
				
				
				
			}
			else if(parsedInput[0].equals("R"))
			{
				
				
				
			}
			else if(parsedInput[0].equals("T"))
			{
				
				
				
			}
			else
			{
				
				System.out.println("Input not recognized. Try again");
				
			}
		}
		
	}
	
}
