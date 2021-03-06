/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #5
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * Represents a node in a directory
 * tree. Can be either a directory or
 * a file.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class DirectoryNode {

	//In order to simplify how this system works
	public static final boolean FILE = true, DIRECTORY = false;
	
	//Since the default \t tab is too long, defined a new shorter one
	private static final String TAB = "   ";
	
	//Used when printing a recursive directory listing
	private static int numTabs = 0;
	
	//The name of this node. The root is always called "root"
	private String name;
	
	//The children of this node
	private DirectoryNode children[];
	
	//The type of this file
	private boolean isFile;
	
	//These fields are used when the find method is called
	private static final int MAX_NUM_MATCHES  = 20;
	private static DirectoryNode[] nodeMatches = new DirectoryNode[MAX_NUM_MATCHES];
	private static int matchIndex = 0;
	
	/**
	 * Generates a new DirectoryNode object with the given name.
	 * 
	 * @param name
	 * 		the name of this node
	 */
	public DirectoryNode(String name, int numChildren)
	{
		
		children = new DirectoryNode[numChildren];
		this.name = name;
		
	}
	
	/**
	 * Generates a node with the given name and given status.
	 * 
	 * @param name
	 * 		the name of this node
	 * @param isFile
	 * 		if this node is a file or directory
	 */
	public DirectoryNode(String name, int numChildren, boolean isFile)
	{
		
		this(name, numChildren);
		this.isFile = isFile;
		
	}
	
	/**
	 * Gets the name of this node.
	 * 
	 * @return
	 * 		the name of this node
	 */
	public String getName(){return name;}
	
	/**
	 * Sets the name of this node.
	 * 
	 * @param in
	 * 		the new name of this node
	 */
	public void setName(String in){name=in;}
	
	/**
	 * Returns the children of this node	
	 * 
	 * @return
	 * 		the children of this node
	 */
	public DirectoryNode[] getChildren(){return children;}
	
	/**
	 * Returns if this node is a file or directory
	 * 
	 * @return
	 * 		if this node is a file
	 */
	public boolean isFile(){return this.isFile;}
	
	/**
	 * Adds the given node into this node's children if it has space and
	 * if it is a directory.
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 * 		This node is not a file.
	 * 		There is at least one empty position in the children of this node.
	 * <dt><b>Postconditions:</b><dd>
	 * 		DirectoryNode node has been added as a child of this node, or an
	 * 	exception has been thrown.
	 * 
	 * @param node
	 * 		the new node to add to this node's children
	 * @throws NotADirectoryException
	 * 		indicates that this node is a file
	 * @throws FullDirectoryException
	 * 		indicates that this node has no room to store any more children
	 */
	public void addChild(DirectoryNode node) throws NotADirectoryException, FullDirectoryException
	{
		
		if(this.isFile())
			throw new NotADirectoryException(this.name + ": Not a directory");
		
		//Loops through each child
		for(int i = 0; i < children.length; i++)
			if(children[i] == null)
			{
				//If there's an empty node, put the new child there
				children[i] = node;
				//Immediately exit the method
				return;
			}
		
		throw new FullDirectoryException(this.name + ": Directory is full");
		
	}
	
	/**
	 * Prints this node and all of its subnodes recursively
	 * in preorder.
	 */
	public void preOrder()
	{

		for(int i = 0; i < numTabs; i++)
			System.out.print(TAB);
		
		if(this.isFile())
			System.out.println("- " + name);
		else
			System.out.println("|- " + name);
		
		numTabs++;
		
		//Recursive call here
		for(int i = 0; i < children.length; i++)
			if(children[i] != null)
				children[i].preOrder();
		
		
		numTabs--;
		
	}
	
	/**
	 * Clears the list of DirectoryNodes that were saved during a 
	 * recursive find call. 
	 * 
	 * Call this method whenever outside code calls the DirectoryNode.find() method.
	 */
	public static void clearList()
	{
		
		nodeMatches = new DirectoryNode[MAX_NUM_MATCHES];
		matchIndex = 0;
		
	}
	
	/**
	 * Finds the node with the name 'name' in
	 * this node and all of its subnodes. 
	 * 
	 * Returns an array of matches at the 
	 * end of the recursive call.
	 * 
	 * @param name
	 * 		the name to search for
	 * @return
	 * 		an array of references to matches in the tree
	 * 
	 */
	public DirectoryNode[] find(String name)
	{
		
		if(this.getName().equals(name))
		{
			
			//System.out.println("Instance found. Adding reference to array");
			nodeMatches[matchIndex++] = this;
			
		}
			
		for(int i=0;i<children.length;i++)
			if(children[i] != null)
				children[i].find(name);
		
		
		return nodeMatches;
		
	}
	
	/**
	 * Returns a string representation of this object.
	 * 
	 * @return
	 * 		a string representation of this object
	 */
	public String toString()
	{
		
		return name;
		
	}
	
}