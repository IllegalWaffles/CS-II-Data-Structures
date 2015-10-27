/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #4
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * Main driving class of this program.
 * Allows the user to interact with
 * the DirectoryTree class.
 * 
 * @author Kuba
 *
 */

import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BashTerminal {
	
	public static void main(String args[])
	{
		
		ArrayList<String> history = new ArrayList<String>();
		DirectoryTree myTree;
		Scanner sc = new Scanner(System.in);
		String user, host, prompt, date, time, rawInput, input[];
		String dateTime[]  = LocalDateTime.now().toString().split("T");
		boolean finished = false;		
		
		/*
		 * Code below exists so a predefined set of instructions
		 * can be read from a file instead of entering them all
	 	 * each time. Commenting it out when you want to read
	 	 * from console will suffice.
		 */
		
		
		try{
			
			sc = new Scanner(new File("src/test2"));
			
		}
		catch(FileNotFoundException e)
		{
			
			System.out.println("File not found");
			
		}
		
		
		date = dateTime[0];
		time = dateTime[1].substring(0, dateTime[1].length()-4);
		
		user = "109776237";
		host = "kuba.laptop";
		
		myTree = new DirectoryTree();
		
		prompt = "[" + user + "@" + host + "]: $ ";
		System.out.println("Logged in on " + date + " at " + time);
		
		while(!finished){
		
			//System.out.print(prompt);
			rawInput = sc.nextLine();
			
			history.add(rawInput);
			
			input = rawInput.split(" ");
			
			if(input[0].equals("exit") || input[0].equals("logout"))
			{
				//Exits the program
				System.out.println("logout");
				finished = true;
				
			}
			else if(input[0].equals("echo"))
			{
				//Prints whatever args are after echo.
				//This serves no purpose in this program.
				//But I just couldn't resist.
				for(int i = 1; i < input.length; i++)
					System.out.print(input[i] + " ");
				
				System.out.print("\n");
				
			}
			else if(input[0].equals("pwd"))
			{
				//Prints the current path
				System.out.println(myTree.presentWorkingDirectory());
				
			}
			else if(input[0].equals("ls"))
			{
			
				try{
					
					if(input[1].equals("-R"))
					{
						//Prints a recursive listing of the current directory
						myTree.printDirectoryTree();
						
						
					}
					else
					{
						
						//If the args passed in were invalid
						System.out.println("ls: Invalid input: type \"help ls\" for help");
						
					}
					
				}catch(ArrayIndexOutOfBoundsException e){
					
					//Otherwise if no other input was passed in just do a local ls
					System.out.println(myTree.listDirectory());
					
				}
				
			}
			else if(input[0].equals("cd"))
			{
			
				try{
					
					//Checks if it points to root dir
					if(input[1].equals("/"))
						myTree.resetCursor();
					else
						//Otherwise changes dir to whatever second arg is
						myTree.changeDirectory(input[1]);
						
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					//If "cd" is the only input
					myTree.resetCursor();
				}
				catch(Exception e)
				{
					//Otherwise, something went wrong
					System.out.println("cd: " + e.getMessage());	
				}
				
			}
			else if(input[0].equals("mkdir"))
			{
				
				{
					try{
				
						//If the name of the new folder includes a space
						if(input.length > 2)
							throw new IllegalArgumentException(input[0] + ": Invalid file name");
							
						//Try to make the directory
						myTree.makeDirectory(input[1]);
				
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
					
						System.out.println("mkdir: invalid input: type \"help mkdir\" for help");
						
					}
					catch(Exception e)
					{
						//Otherwise something else went wrong
						System.out.println("mkdir: " + e.getMessage());
					
					}

				}	
				
			}
			else if(input[0].equals("touch"))
			{
				
				try{
				
					//If the name of the file has a space
					if(input.length > 2)
						throw new IllegalArgumentException(input[1] + ": Invalid file name");
						
					//Attempt to create the file
					myTree.makeFile(input[1]);
				
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					
					//No arguments were passed in
					System.out.println("touch: invalid input: type \"help touch\" for help");
					
				}
				catch(Exception e)
				{
				
					//Otherise something else went wrong
					System.out.println("touch: " + e.getMessage());
					
				}
				
			}
			else if(input[0].equals("help"))
			{
				
				try{
					//If there was a second argument passed in
					help(input[1]);
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					//Otherwise print the generic help if no other args passed in
					help("GENERIC");
				}
				catch(Exception e)
				{	
					
					//Otherwise something went wrong
					System.out.println("help: " + e.getMessage());
					
				}
				
				
			}
			else if(input[0].equals("clear"))
			{
				
				//Clears the console, somewhat haphazardly.
				//This doesn't work quite like the bash clear
				//would, but until I figure out how to clear the
				//java console, this will have to do.
				for(int i = 0; i < 100; i++)
					System.out.println();
				
			}
			else if(input[0].equals("history"))
			{
				
				try{
				
					if(input[1].equals("-c"))
					{
					
						System.out.println("History cleared");
						history = new ArrayList<String>();
						
					}
					else throw new IllegalArgumentException(input[1] + ": Invalid input");
						
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					
					for(int i = 0; i < history.size(); i++)
						System.out.println((i+1) + ": " + history.get(i));	
					
				}
				catch(Exception e)
				{
					
					System.out.println("history: " + e.getMessage());
				
				}
				
				
			}
			else if(input[0].equals("find"))
			{
				
				try{
					
					myTree.find(input[1]);
					
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					
					System.out.println("find: invalid input: type \"help find\" for help");
					
				}
				catch(Exception e)
				{
					
					System.out.println("find: " + e.getMessage());
					
				}
				
			}
			else
			{
				//Or the command was not found
				System.out.println(input[0] + ": command not found");
				
			}
			
		}
		
		sc.close();
		
	}
	
	/**
	 * For each command, prints a certain
	 * "help" text to assist the user.
	 */
	private static void help(String cmd) throws IllegalArgumentException
	{
		
		if(cmd.equals("exit"))
			System.out.println("Exits the program safely\n"
					+ "Usage:\n"
					+ " exit - exits the program");
		else if(cmd.equals("history"))
			System.out.println("Displays the command history for this session\n"
					+ "Usage:\n"
					+ " history - displays the history of commands"
					+ " history -c - clears history");
		else if(cmd.equals("logout"))
			System.out.println("Logs the user out of the current session. Identical to command 'exit'\n"
					+ "Usage:\n"
					+ " logout - logs the user out of the current shell");
		else if(cmd.equals("echo"))
			System.out.println("Prints whatever arguments come after echo\n"
					+ "Usage:\n"
					+ " echo <args> - prints whatever is in <args> to the console");
		else if(cmd.equals("pwd"))
			System.out.println("Prints the path of the current working directory\n"
					+ "Usage:\n"
					+ " pwd - prints the path");
		else if(cmd.equals("ls"))
			System.out.println("Lists the contents of the current directory\n"
					+ "Usage:\n"
					+ " ls - prints the contents of the current directory\n"
					+ " ls -R - prints a recursive listing of the current directory");
		else if(cmd.equals("cd"))
			System.out.println("Changes the current directory\n"
					+ "Usage:\n"
					+ " cd [directory] - changes the current directory to 'directory'\n"
					+ " cd .. - changes to the parent directory of the current directory\n"
					+ " cd - changes to the root directory");
		else if(cmd.equals("mkdir"))
			System.out.println("Creates a directory\n"
					+ "Usage:\n"
					+ " mkdir [dirname] - creates a directory with name 'dirname'");
		else if(cmd.equals("touch"))
			System.out.println("Creates a file\n"
					+ "Usage:\n"
					+ " touch [filename] - creates a file with name 'filename'");
		else if(cmd.equals("help"))
			System.out.println("Provides the user with help for commands, or a general guide to the user interface\n"
					+ "Usage:\n"
					+ " help [command] - provides help for the command 'command'\n"
					+ " help - displays a general help message");
		else if(cmd.equals("clear"))
			System.out.println("Clears the console\n"
					+ "Usage:\n"
					+ " clear - clears the screen");
		else if(cmd.equals("find"))
			System.out.println("Finds all files with the name given and prints their paths\n"
					+ "Usage:\n"
					+ " find [filename] - finds and prints the paths of all files matching this name");
		else if(cmd.equals("GENERIC"))
			System.out.println("Available commands:\n"
					+ " pwd - prints the path of the current working directory\n"
					+ " ls - prints the contents of the current directory\n"
					+ " cd - changes current directory\n"
					+ " mkdir - generates a new directory\n"
					+ " touch - generates a new file\n"
					+ " clear - clears the console\n"
					+ " find - finds all instances of the given name\n"
					+ " help - prints this message, or type help [command] to find out more about a specific command");
		else throw new IllegalArgumentException(cmd + ": command not found");
		
		
	}
	
}
