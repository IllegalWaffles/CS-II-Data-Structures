/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #4
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
	private static int numTabs;
	
	//The name of this node. The root is always called "/"
	private String name;
	
	//The children of this node, as well as a reference to the node's parent
	private DirectoryNode children[], parent;
	
	//The type of this file
	private boolean isFile;
	
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
		numTabs = 0;
		
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
	 * 
	 * @param name
	 * 		the name of this directoryNode
	 * @param isFile
	 * 		the type of this directoryNode
	 * @param parent
	 * 		the parent of this directoryNode,
	 * 		null iff this object is the root 
	 * 		of a tree
	 */
	public DirectoryNode(String name, int numChildren, boolean isFile, DirectoryNode parent)
	{
		
		this(name, numChildren, isFile);
		this.parent = parent;
		
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
	 * Gets the parent directory associated with this node
	 * 
	 * @return
	 * 		the parent directory of this node
	 */
	public DirectoryNode getParent(){return parent;}
	
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
		
		//Loop here
		/*
		if(left == null)
			left = node;
		else if(middle == null)
			middle = node;
		else if(right == null)
			right = node;
		*/
		//Loop terminates
		
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
		
		for(int i = 0; i < children.length; i++)
			if(children[i] != null)
				children[i].preOrder();
		
		//Loop here
		
		/*
		if(left != null)
			left.preOrder();
		
		if(middle != null)
			middle.preOrder();
		
		if(right != null)
			right.preOrder();
		*/	
		//Loop terminates
		
		numTabs--;
		
	}
	
}