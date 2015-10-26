import java.util.Scanner;
import java.util.Stack;
import java.io.IOException;
import java.io.File;

/**
 * Kuba Gasiorowski
 * 109776237
 * kuba.gasiorowski@sbu.edu
 * Homework #3
 * Recitation 03
 * Ka Wing Fong
 * Zhichuang Sun
 * 
 * Contains a method to open and parse a python program
 * and determine its big-oh efficiency.
 * 
 * -PLEASE READ ME-
 * 
 * This program does not work correctly. I 
 * should have gone for help but by the time 
 * I am writing this, it is too late.
 * 
 * My method correctly reads from the file, recognizes
 * what loops occur and their complexities, and pushes 
 * and pops at the correct times (pushes a new block
 * onto the stack when it's parsed, pops it when the
 * block ends). I understand the algorithm perfectly,
 * but I can't seem to correctly update my highestSubComplexity
 * when exiting a block.
 * 
 * In the output, each line is parsed one by one and
 * a block of information is printed for each line, 
 * to more easily isolate what the program is doing 
 * at each line.
 * 
 * I hate trying to give excuses, I truly do, but I just thought 
 * it was worth mentioning that I understand how stacks are 
 * meant to work, and I understand the algorithm described in 
 * the problem. Thank you for your time and I wish you best 
 * of luck with the other programs.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class PythonTracer {

	private static final int SPACE_COUNT = 4;
	
	/**
	 * Main method of my program.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		Scanner sc = new Scanner(System.in);
	
		System.out.print("Enter a file name to trace (or \"quit\" to quit): ");
		String file = sc.nextLine();
		
			if(file.toUpperCase().equals("QUIT"))
			{
			
				System.out.println("\n\nExiting...");
				sc.close();
			
			}
			else
			{
			
				try{
					
					traceFile(file);
					sc.close();
					
				}catch(IOException e)
				{
					
					System.out.println(e.getMessage());
					System.out.println("\n\nExiting...");
					
				}
					
			}
		
	}
	
	/**
	 * This method takes in a file name, parses
	 * the file, and returns the big-oh complexity
	 * of the function. Uses a stack trace.
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 * 		filename is not null and the file it names contains 
	 * 		a single Python function with valid syntax
	 * 
	 * @param inputFile
	 * 		the file to be read from
	 * @return
	 * 		the complexity of the python function passed in
	 * @throws IOException
	 * 		if the file to be parsed does not exist
	 */
	public static Complexity traceFile(String inputFile) throws IOException
	{	
		
		File file = new File(inputFile);
		
		if(!file.isFile())
			throw new IOException("File not found");
		
		Scanner sc = new Scanner(file);
		Stack<CodeBlock> blockStack =  new Stack<CodeBlock>();
		
		String line = "", keyword;
		int indents, size = 0, lineNum = 1;
		String[] parsedInput;
		
		while(sc.hasNext())
		{
			
			if(blockStack.size() > 0)
				System.out.print("\n" + lineNum++ + ": Stack size:" + size + " Block complexity: " + blockStack.peek().getBlockComplexity() + " Highest sub-complexity: " + blockStack.peek().getHighestSubComplexity() + "\n");
			else
				System.out.print("\n" + lineNum++ + ": Stack size:" + size + " Highest sub-complexity: N/A\n");
			
			line =  sc.nextLine();
			
			if(line.trim().equals("") || line.trim().charAt(0) == '#')
				System.out.println("Line skipped");
			else
			{
				
				System.out.println("Line is not a comment or empty. Reading line...");
				indents = line.indexOf(line.trim())/SPACE_COUNT;//Indicates the number of indents
				
				while(indents < blockStack.size())
					if(indents == 0)
					{
						
						sc.close();
						//Should return the total complexity 
						//of the top of the stack
						return blockStack.pop().getHighestSubComplexity();
						
					}
					else
					{
						
						//Called when a block is closed, therefore, it is taken off the stack.
						//This section of code is what is giving me the most trouble.
						
						CodeBlock oldTop = blockStack.pop();
						CodeBlock currentTop = blockStack.peek();
						
						System.out.println("Exiting " + CodeBlock.BLOCK_TYPES[oldTop.getLoopType()]);
						
						Complexity oldTopComplexity = oldTop.getHighestSubComplexity();
						
						//Complexity newTopComplexity = oldTopComplexity.add(currentTop.getHighestSubComplexity());
						
						if(oldTopComplexity.isHigherOrder(currentTop.getHighestSubComplexity()))
						{
							
							currentTop.setHighestSubComplexity(oldTopComplexity.add(currentTop.getHighestSubComplexity()));
							
						}
						
					}
				
				parsedInput = line.trim().split(" ");//Splits the line into tokens
					
				if(arrayContains(CodeBlock.BLOCK_TYPES, parsedInput[0]))//If the line contains a keyword
				{
						
					keyword = parsedInput[0];//The keyword will be the 
											 //first token on the line.
														
					if(keyword.equals(CodeBlock.BLOCK_TYPES[CodeBlock.FOR]))
					{//If the block is a for loop
							
						System.out.println("For loop detected. Finding complexity...");
						
						CodeBlock newBlock = new CodeBlock(CodeBlock.FOR);
						
						if(parsedInput[3].equals("N:"))//Determine if the forloop is n or logn
						{
							
							System.out.println("Complexity determined to be O(n)");
							newBlock.setBlockComplexity(new Complexity(1,0));
							
						}
						else
						{
							
							System.out.println("Complexity determined to be O(log(n))");
							newBlock.setBlockComplexity(new Complexity(0,1));
							
						}
						
						blockStack.push(newBlock);
						
							
					}
					else if(keyword.equals(CodeBlock.BLOCK_TYPES[CodeBlock.WHILE]))
					{//If the block is a while loop
							
						System.out.println("While loop detected.");
							
						CodeBlock newBlock = new CodeBlock(CodeBlock.WHILE);
						newBlock.setBlockComplexity(new Complexity(0,0));
						newBlock.setLoopVariable(parsedInput[1]);
						blockStack.push(newBlock);
						
						
					}	
					else
					{//If it's another type of loop
							
						int blockType = -1;
							
						//Determine the type of block encountered
						for(int i = 0; i < 6; i++)
							if(keyword.equals(CodeBlock.BLOCK_TYPES[i]))
								blockType = i;
							
						System.out.println("A block of type: " + CodeBlock.BLOCK_TYPES[blockType] + " detected.");
							
						//Push an O(1) block onto the stack
						CodeBlock newBlock = new CodeBlock(blockType);
						newBlock.setBlockComplexity(new Complexity(0,0));
						
						blockStack.push(newBlock);
						
						
					}
						
						
				}
				else if(  blockStack.peek().getLoopType() == CodeBlock.WHILE &&
							  parsedInput[0].equals(blockStack.peek().getLoopVariable()))
				{
					 //If the top block is a while loop and the current line's first token is
					 //the same as the loop variable of the top block
						
					System.out.println("Update statement for while loop encountered. Updating...");
						
					CodeBlock topBlock = blockStack.peek();
						
					if(parsedInput[1].equals("-="))//If it is of O(n), push that onto the stack
						topBlock.setBlockComplexity(new Complexity(1,0));
					else if(parsedInput[1].equals("/="))//Is it is of O(logn), push that onto the stack
						topBlock.setBlockComplexity(new Complexity(0,1));
					
						
				}	
				else
					System.out.println("Line contains nothing of interest. Continuing...");
				
				
			}
				
		}
			
		sc.close();
		
		return blockStack.pop().getHighestSubComplexity();
		
	}
	
	/**
	 * Helper method that returns true if string
	 * 'toSearchFor' is in the array 'arrayIn',
	 * false otherwise.
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 * 		arrayIn and toSearchFor are not null
	 * 
	 * @param arrayIn
	 * 		the string to search for
	 * @param toSearchFor
	 * 		array to be searched
	 * @return
	 * 		true if 'arrayIn' contains 'toSearchFor'
	 */
	private static boolean arrayContains(String[] arrayIn, String toSearchFor)
	{
		
		for(int i = 0; i < arrayIn.length; i++)
			if(toSearchFor.equals(arrayIn[i]))
				return true;
		
		return false;
		
	}
	
}
