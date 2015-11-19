import java.util.Scanner;

/**
 * 
 * @author Kuba Gasiorowski
 *
 */
public class SearchEngine {

	private static WebGraph web;
	private static final String TAB = "   ";
	
	public static void main(String[] args)
	{
		
		web = new WebGraph();
		boolean finished = false;
		Scanner sc = new Scanner(System.in);
		String command;
		
		System.out.println(TAB + "(AP) - Add a new page to the graph.\n"
					 	 + TAB + "(RP) - Remove a page from the graph.\n"
					 	 + TAB + "(AL) - Add a link between pages in the graph.\n"
						 + TAB + "(RM) - Remove a link between pages in the graph.\n"
						 + TAB + "(P) - Print the graph.\n"
						 + TAB + "(S) - Search for pages with a keyword.\n"
						 + TAB + "(Q) - Quit.");
		
		while(!finished)
		{
			
			System.out.print(">");
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
				
				System.out.println("You chose AL");
				
			}
			else if(command.equals("RM"))
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
				
				while(!finished)
				{
					
					printCommand = sc.nextLine().toUpperCase().split(" ")[0];
					
					if(printCommand.equals("Q"))
					{
						
						finished = true;
						
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
