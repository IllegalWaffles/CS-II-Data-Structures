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
		
		web.printLinkArray();
		
		System.out.println();
		
		web.printTable();
		
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
			
			if(command.equals("Q"))
			{
				
				System.out.println("Exiting...");
				finished = true;
				
			}
			else if(command.equals("AP"))
			{
				
				System.out.println("You chose AP");
				
			}
			else if(command.equals("RP"))
			{
				
				System.out.println("You chose RP");
				
			}
			else if(command.equals("AL"))
			{
				
				String src, dest;
				
				try{
				
					System.out.print("Enter a source URL: ");
					src = sc.nextLine();
					System.out.print("Enter a destination URL: ");
					dest = sc.nextLine();
				
					web.addLink(src, dest);
				
					System.out.println("Link added.");
					
				}
				catch(Exception e)
				{
					
					System.out.println(e.getMessage());
					
				}
				
			}
			else if(command.equals("RL"))
			{
				
				
				
				
				System.out.println("You chose RM");
				
			}
			else if(command.equals("P"))
			{
				
				System.out.print(TAB + "(I) Sort based on index (ASC)\n"
							   + TAB + "(U) Sort based on URL (ASC)\n"
							   + TAB + "(R) Sort based on rank (DSC)\n"
							   + TAB + "(Q) Quit (return to previous menu)\n> ");
				
				
				String printCommand; 
				boolean printFinished = false;
				
				while(!printFinished)
				{
					
					printCommand = sc.nextLine().toUpperCase().split(" ")[0];
					
					if(printCommand.equals("Q"))
					{
						
						printFinished = true;
						
					}
					else if(printCommand.equals("I"))
					{
						
						System.out.println("You chose I");
						
					}
					else if(printCommand.equals("U"))
					{
						
						System.out.println("You chose U");						
						
					}
					else if(printCommand.equals("R"))
					{
						
						System.out.println("You chose R");						
						
					}
					else
					{
						
						System.out.println("Command not recognized. Try again");						
						
					}
					
				}
				
				
			}
			else if(command.equals("S"))
			{
				
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
