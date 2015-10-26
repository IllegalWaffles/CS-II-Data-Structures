/**
 * Kuba Gasiorowski
 * 109776237
 * kuba.gasiorowski@sbu.edu
 * Homework #3
 * Recitation 03
 * Ka Wing Fong
 * Zhichuang Sun 
 * 
 * Represents a block of code that is encountered in
 * a python program. Has methods and fields that describe
 * the big-oh complexity of this object as well as the
 * total big-oh complexity of all nested blocks in this
 * object.
 * 
 * @author Kuba Gasiorowski
 */
public class CodeBlock {

	public static final String[] BLOCK_TYPES = {"def","for","while","if","else","elif"};
	public static final int DEF = 0, FOR = 1, WHILE = 2, IF = 3, ELSE = 4, ELIF = 5;
	
	private Complexity blockComplexity, highestSubComplexity;
	private String loopVariable, name;
	
	private int loopType;
	
	/**
	 * Initializes an empty CodeBlock.
	 */
	public CodeBlock()
	{
		
		blockComplexity = null;
		highestSubComplexity = new Complexity();
		name = "";
		loopVariable = "";
		loopType = 0;
		
	}
	
	/**
	 * Initializes a CodeBlock with a certain type.
	 * 
	 * @param loopType
	 * 		the type of this codeBlock
	 */
	public CodeBlock(int loopType)
	{
		
		blockComplexity = null;
		highestSubComplexity = new Complexity();
		name = "";
		loopVariable = "";
		this.loopType = loopType;
		
	}
	
	/**
	 * Returns the complexity of this block.
	 * 
	 * @return
	 * 		the complexity of this block
	 */
	public Complexity getBlockComplexity()
	{
		
		return blockComplexity;
		
	}
	
	/**
	 * Sets the complexity of this block equal to what is passed in.
	 * 
	 * @param in
	 * 		the new complexity of this block
	 */
	public void setBlockComplexity(Complexity in)
	{
		
		blockComplexity = new Complexity(in);
		
	}
	
	/**
	 * Gets the highest sub-complexity of this block.
	 * 
	 * @return
	 * 		
	 */
	public Complexity getHighestSubComplexity()
	{
		
		return highestSubComplexity;
		
	}
	
	/**
	 * Sets the highest sub-complexity of this CodeBlock to what is passed in.
	 * 
	 * @param in
	 * 		sets the highest sub-complexity of this CodeBlock to what's passed in
	 */
	public void setHighestSubComplexity(Complexity in)
	{
		
		highestSubComplexity = new Complexity(in);
		
	}
	
	/**
	 * Returns the loop variable of a while loop. This is only
	 * used with while loops and nothing else.
	 * 
	 * @return
	 * 		returns the variable that the while loop is using
	 */
	public String getLoopVariable()
	{
		
		return loopVariable;
		
	}
	
	/**
	 * Sets the loop variable of this while loop. Used only
	 * when this block is a while loop.
	 * 
	 * @param in
	 * 		sets the varaible that this while loop is using
	 */
	public void setLoopVariable(String in)
	{
		
		loopVariable = in;
		
	}
	
	/**
	 * Returns the name of this CodeBlock
	 * 
	 * @return
	 * 		the name of this CodeBlock
	 */
	public String getName()
	{
		
		return name;
		
	}
	
	/**
	 * Sets the name of this CodeBlock to whatever is passed in.
	 * 
	 * @param in
	 * 		the new name of this CodeBlock object
	 */
	public void setName(String in)
	{
		
		name = in;
		
	}
	
	/**
	 * Returns an integer corresponding with the type of CodeBlock this is.
	 * Meant to be used in conjunction with the static constants defined above.
	 * 
	 * @return
	 * 		an integer corresponding with the type of CodeBlock that this is
	 */
	public int getLoopType()
	{
		
		return loopType;
		
	}
	
	/**
	 * Sets what type of loop this CodeBlock is.
	 * Meant to be used in conjunction with the static constants defined above.
	 * 
	 * @param loopType
	 * 		the type of loop that this CodeBlock is
	 */
	public void setLoopType(int loopType)
	{
		
		this.loopType = loopType;
		
	}
	
}
