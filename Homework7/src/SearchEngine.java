import java.util.ArrayList;
import java.util.Scanner;

/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #7
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * Represents a search engine, of
 * sorts. This is the main driving 
 * class behind the program. Contains 
 * a simple command-driven UI to allow
 * the user to manipulate a WebGraph.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class SearchEngine {

	private static WebGraph web;
	private static final String TAB = "   ";
	
	public static final String PAGES_FILE = "pages.txt";
	public static final String LINKS_FILE = "links.txt";
	
	/**
	 * Main driving method behind this program.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		web = WebGraph.buildFromFiles(PAGES_FILE, LINKS_FILE);
		
		boolean finished = false;
		Scanner sc = new Scanner(System.in);
		String command;
		
		System.out.println("Menu:\n" 
				 		  + TAB + "(AP) - Add a new page to the graph.\n"
				 	 	  + TAB + "(RP) - Remove a page from the graph.\n"
					 	  + TAB + "(AL) - Add a link between pages in the graph.\n"
						  + TAB + "(RM) - Remove a link between pages in the graph.\n"
						  + TAB + "(P) - Print the graph.\n"
						  + TAB + "(S) - Search for pages with a keyword.\n"
						  + TAB + "(Q) - Quit.");
		
		while(!finished)
		{
			
			System.out.print("> ");
			command = sc.nextLine().toUpperCase().split(" ")[0];
			
			if(command.equals("Q") || command.equals("EXIT") || command.equals("QUIT"))
			{
				
				//Code to exit the program
				System.out.println("Exiting...");
				finished = true;
				
			}
			else if(command.equals("AP"))
			{
				
				//Code to add a webpage
				String url, keywordArray[];
				ArrayList<String> keywords = new ArrayList<String>();
				
				System.out.print("Enter a URL: ");
				url = sc.nextLine();
				System.out.print("Enter keywords (space-separated): ");
				keywordArray = sc.nextLine().split(" ");
				
				//Converts this String array into an arraylist
				for(int i = 0; i < keywordArray.length; i++)
					keywords.add(keywordArray[i]);
					
				if(web.addPage(url, keywords))
					System.out.println("Page " + url + " successfully added.");
				else
					System.out.println("Page not added. Something went wrong");
				
			}
			else if(command.equals("RP"))
			{
				
				//Code to remove a webpage
				String toRemove;
				System.out.print("Enter a URL: ");
				toRemove = sc.nextLine();
				
				if(web.removePage(toRemove))
					System.out.println("Page " + toRemove + " successfully removed.");
				else
					System.out.println("Page not removed. Something went wrong");
				
			}
			else if(command.equals("AL"))
			{
				
				//Code to add a link
				String src, dest;
				
				System.out.print("Enter a source URL: ");
				src = sc.nextLine();
				System.out.print("Enter a destination URL: ");
				dest = sc.nextLine();
				
				if(web.addLink(src, dest))
					System.out.println("Link added.");
				else
					System.out.println("Link not added. Something went wrong");
				
			}
			else if(command.equals("RL"))
			{
				
				String src, dest;
				
				//Code to remove a link
				System.out.print("Enter a source URL: ");
				src = sc.nextLine();
				System.out.print("Enter a destination URL: ");
				dest = sc.nextLine();
				
				if(web.removeLink(src, dest))
					System.out.println("Link removed.");
				else
					System.out.println("Link not removed. Something went wrong");
				
			}
			else if(command.equals("P"))
			{
				
				//Code to print the graph
				System.out.println(TAB + "(I) Sort based on index (ASC)\n"
							    + TAB + "(U) Sort based on URL (ASC)\n"
							    + TAB + "(R) Sort based on rank (DSC)\n"
							    + TAB + "(Q) Quit (return to previous menu)");
				
				
				String printCommand; 
				boolean printFinished = false;
				
				while(!printFinished)
				{
					
					System.out.print("> ");
					printCommand = sc.nextLine().toUpperCase().split(" ")[0];
					
					if(printCommand.equals("Q"))
					{
						
						//Exits the printing command
						printFinished = true;
						web.sortByIndex();
						
					}
					else if(printCommand.equals("I"))
					{
						
						//Prints the table sorted by index
						System.out.println("\nPrinting table by index:\n");

						web.sortByIndex();
						web.printTable();
						System.out.println();
						
					}
					else if(printCommand.equals("U"))
					{
						
						//Prints the table sorted by URL
						System.out.println("\nPrinting table by URL:\n");
						
						web.sortByURL();
						web.printTable();
						System.out.println();
						
					}
					else if(printCommand.equals("R"))
					{
						
						//Prints the table sorted by pagerank
						System.out.println("\nPrinting table by pagerank:\n");
						
						web.sortByRank();
						web.printTable();
						System.out.println();
						
					}
					else
					{
						
						System.out.println("Command not recognized. Try again");						
						
					}
					
				}
				
				
			}
			else if(command.equals("S"))
			{
				
				//Code to do search for a certain keyword
				System.out.println("You chose S");
				
			}
			else
			{
				
				System.out.println("Command not recognized");
				
			}
			
			
		}
		
		sc.close();
		
	}
	
}
