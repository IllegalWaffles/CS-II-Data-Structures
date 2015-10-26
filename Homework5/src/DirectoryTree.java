/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #4
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * Represents a directory tree. 
 * Can be navigated similarly to
 * a BASH prompt. Acts similarly
 * to a BASH prompt.
 * 
 * @author Kuba Gasiorowski
 *
 */
import java.util.ArrayList;

public class DirectoryTree {

	//The root of the whole tree
	private final DirectoryNode root;
	
	//The cursor of the tree
	private DirectoryNode cursor;
	
	//The number of children per node this tree may have
	private final int numChildrenPerNode = 10;
	
	/**
	 * Creates a new empty directory tree. 
	 * Initializes a final node as the root.
	 * 
	 * <dt><b>Postconditions:</b><dd>
	 * 		This tree now contains a single DirectoryNode, both
	 * 	root and cursor point to this object.
	 */
	public DirectoryTree()
	{
		
		root = new DirectoryNode("root", numChildrenPerNode, DirectoryNode.DIRECTORY, null);
		cursor = root;
		
	}
	/**
	 * Takes the cursor back to the top of the tree.
	 * Or bottom I guess.
	 * Depends on how you think of it.
	 * 
	 * <dt><b>Postconditions:</b><dd>
	 * 		The cursor now references the root of the tree.
	 */
	public void resetCursor()
	{
		
		cursor = root;
		
	}
	
	/**
	 * Changes the current working directory to whatever directory 
	 * has the name that is passed in. 
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 * 		'name' references a valid directory.
	 * <dt><b>Postconditions:</b><dd>
	 * 		Cursor now references the directory with the name given.
	 * 
	 * @param name
	 * 		the name of the directory to navigate to
	 * @throws NotADirectoryException
	 * 		indicates the directory chosen does not exist or is a file
	 */
	public void changeDirectory(String name) throws NotADirectoryException
	{
		
		DirectoryNode[] children = cursor.getChildren();
	
		if(name.equals(".."))
		{
			
			if(cursor == root)
				throw new NotADirectoryException(name + ": Already in root directory");
			else
				cursor = cursor.getParent();
			
		}
		else
		{
				
			for(int i = 0; i < children.length; i++)
				if(children[i] != null && children[i].getName().equals(name))
				{
					
					if(children[i].isFile())
						throw new NotADirectoryException(name + ": Not a directory");
					else
						cursor = children[i];
					
					return;
				
				}
			
			//If the loop goes through each child and none match,
			//the directory does not exist. Throw an exception.
			throw new NotADirectoryException(name + ": No such file or directory");
		}
		
	}
	
	/**
	 * Returns a string corresponding with the path of the cursor.
	 * 
	 * <dt><b>Postconditions:</b><dd>
	 * 		Cursor has not moved.
	 * 
	 * @return
	 * 		a string corresponding with the current
	 * 		path of the cursor
	 * 
	 */
	public String presentWorkingDirectory()
	{
		
		DirectoryNode saved = cursor;
		
		String toReturn = cursor.getName();
		
		while(cursor.getParent() != null)
		{
		
			cursor = cursor.getParent();
			toReturn = cursor.getName() + "/" + toReturn;
			
		}
		
		
		cursor = saved;
		return toReturn;
		
		
	}
	
	/**
	 * When called, returns a string of all children
	 * that exist in the current directory
	 * 
	 * <dt><b>Postconditions:</b><dd>
	 * 		Cursor has not moved.
	 * 
	 * @return
	 * 		the names of each directory or file
	 * 		in the current directory
	 */
	public String listDirectory()
	{
		
		String toReturn = "";
		
		DirectoryNode[] children = cursor.getChildren();
		
		for(int i=0; i < children.length; i++)
			if(children[i] != null)
				toReturn += children[i].getName() + " ";
		
		return toReturn.trim();
		
		
		
	}
	
	/**
	 * Recursively prints the directory tree of the current working
	 * directory.
	 * 
	 * <dt><b>Postconditions:</b><dd>
	 * 		Cursor has not moved.
	 * 
	 */
	public void printDirectoryTree()
	{
		
		cursor.preOrder();
		
	}
	
	/**
	 * Creates a new directory in the current directory.
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 * 		'name' is a valid directory name
	 * <dt><b>Postconditions:</b><dd>
	 * 		A new DirectoryNode has been added to the children of the cursor
	 * 	or an Exception has been thrown.
	 * 
	 * @param name
	 * 		the name of the new directory
	 * @throws IllegalArgumentException
	 * 		indicates the given name is invalid
	 * @throws FullDirectoryException
	 * 		indicates the current directory is full and cannot accept 
	 * 		another subdirectory
	 */
	public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException
	{
		
		if(!isValidName(name))
			throw new IllegalArgumentException(name + ": Invalid directory name");
		
		try
		{
			cursor.addChild(new DirectoryNode(name, numChildrenPerNode, DirectoryNode.DIRECTORY, cursor));
		}
		catch(NotADirectoryException e)
		{
			System.out.println("Error: selected node is not a directory. How did you accomplish this?");
		}
		catch(FullDirectoryException e)
		{
			throw e;	
		}
		
	}
	
	/**
	 * Creates a new file in the current directory.
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 * 		'name' is a valid file name
	 * <dt><b>Postconditions:</b><dd>
	 * 		A new DirectoryNode has been added to the children of the cursor, or an exception has been thrown.
	 * 
	 * @param name
	 * 		the name of the new file
	 * @throws IllegalArgumentException
	 * 		indicates the name given is not valid
	 * @throws FullDirectoryException
	 * 		indicates the current directory is full and cannot accept
	 * 		another file
	 */
	public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException
	{
		
		if(!isValidName(name))
			throw new IllegalArgumentException(name + ": Invalid file name");
		
		try
		{
			cursor.addChild(new DirectoryNode(name, numChildrenPerNode, DirectoryNode.FILE, cursor));
		}
		catch(NotADirectoryException e)
		{
			System.out.println("Error: selected node is not a directory. How did you accomplish this?");	
		}
		catch(FullDirectoryException e)
		{
			throw e;
		}
		
	}
	
	/**
	 * 
	 * @param name
	 * @throws IllegalArgumentException
	 * @throws NotADirectoryException
	 */
	public void find(String name) throws IllegalArgumentException, NotADirectoryException
	{
		
		//Saves the current position of the cursor
		DirectoryNode saved = cursor;
		
		//If the name is invalid, throw an exception
		if(!isValidName(name))
			throw new IllegalArgumentException(name + ": Not a valid name");
		
		//Finds all matches of the nodes with the name passed in, stores into an array
		ArrayList<DirectoryNode> matches = cursor.find(name);
		
		//If no matches were found, throw an exception
		if(matches.size() == 0)
			throw new NotADirectoryException(name + ": File not found");
		
		//Prints the path of each node
		for(int i = 0; i < matches.size(); i++)
		{
			
			cursor = matches.get(i);
			System.out.println(presentWorkingDirectory());
		
		}
		
		DirectoryNode.clearList();
		cursor = saved;
		
	}
	
	/**
	 * Helper method which determines if a file/directory has a valid name.
	 * 
	 * @param s
	 * 		the name to be verified
	 * @return
	 * 		false if a ' ' or '/' is present in the string, true otherwise
	 */
	private boolean isValidName(String s)
	{
		
		for(int i = 0; i < s.length(); i++)
			if(s.charAt(i) == ' ' || s.charAt(i) == '/')
				return false;
		
		return true;
		
	}
	
}
