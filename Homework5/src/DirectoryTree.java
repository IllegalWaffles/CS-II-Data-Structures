/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #5
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * Represents a directory tree. 
 * Can be navigated similarly to
 * a BASH prompt. Acts similarly
 * to a BASH file system.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class DirectoryTree {

	//The root of the whole tree
	private final DirectoryNode root;
	
	//The cursor of the tree
	private DirectoryNode cursor;
	
	//The number of children per node this tree may have
	private final int numChildrenPerNode = 10;
	
	//The current path of the tree. Is updated whenever the directory is changed.
	private String path;
	
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
		
		root = new DirectoryNode("root", numChildrenPerNode, DirectoryNode.DIRECTORY);
		cursor = root;
		path = "root";
		
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
		path = "root";
		
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
	
		if(name.equals(".."))
		{
			
			if(cursor == root)
				throw new NotADirectoryException("root: Already in root directory");
			else
			{
				
				//Remove last directory from path
				//Change to this new path
				
				
				path = path.substring(0, path.lastIndexOf('/'));
				changeDirectory(path);
				
				
			}
		}
		else
		{

			String[] parsedInput = name.split("/");
			DirectoryNode children[], initialCursor;

			if(parsedInput[0].equals("root"))
			{

				String[] temp = new String[parsedInput.length-1];

				resetCursor();

				for(int i = 0; i < parsedInput.length-1;i++)
					temp[i] = parsedInput[i+1];

				parsedInput = temp;

			}

			//Loops through each next directory name
			for(int i = 0; i < parsedInput.length; i++)
			{

				//Gets the children from the current cursor
				children = cursor.getChildren();

				//Saves the initial cursor location.
				initialCursor = cursor;

				//Loops through each child of the current node
				for(int j = 0; j < children.length; j++)
				{
					//If the child matches
					if(children[j] != null && children[j].getName().equals(parsedInput[i]))
					{

						if(children[j].isFile())
							throw new NotADirectoryException(name + ": Not a directory");
						else
						{
							cursor = children[j];
							path += "/" + cursor.getName();
							break;
						}

					}

				}

				//If the directory has not changed,
				//the cursor has not moved and the specified file does not exist
				if(initialCursor == cursor)
					throw new NotADirectoryException(parsedInput[i] + ": No such file or directory");

			}
		
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
		
		return path;
		
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
		
		if(!isValidName(name) || name.equals("root"))
			throw new IllegalArgumentException(name + ": Invalid directory name");
			
		try
		{
			cursor.addChild(new DirectoryNode(name, numChildrenPerNode, DirectoryNode.DIRECTORY));
		}
		catch(NotADirectoryException e)
		{
			System.out.println("Error: selected node is not a directory. How did you even accomplish this?");
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
			cursor.addChild(new DirectoryNode(name, numChildrenPerNode, DirectoryNode.FILE));
		}
		catch(NotADirectoryException e)
		{
			System.out.println("Error: selected node is not a directory. How did you even accomplish this?");	
		}
		catch(FullDirectoryException e)
		{
			throw e;
		}
		
	}
	
	/**
	 * Searches the entire tree for a node with
	 * the name passed in. Prints the path of each node.
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 * 		'name' is a valid node name
	 * 		'name' exists in this file system
	 * <dt><b>Postconditions:</b><dd>
	 * 		Cursor has not moved
	 * 
	 * @param name
	 * 		the node name to look for
	 * @throws IllegalArgumentException
	 * 		indicates the node name is invalid
	 * @throws NotADirectoryException
	 * 		indicates that no matches were found
	 */
	public void find(String name) throws IllegalArgumentException, NotADirectoryException
	{
		
		//If the name is invalid, throw an exception
		if(!isValidName(name))
			throw new IllegalArgumentException(name + ": Not a valid name");
		
		//Finds all matches of the nodes with the name passed in, stores into an array
		DirectoryNode[] matches = root.find(name);
		
		//Count the number of matches found
		int numMatches = 0;
		while(matches[numMatches] != null)
			numMatches++;
		
		//If no matches were found, throw an exception
		if(numMatches == 0)
			throw new NotADirectoryException(name + ": File not found");
	
		//Saves the current position of the cursor
		DirectoryNode saved = cursor;
		
		//Prints the path of each node
		for(int i = 0; i < numMatches; i++)
		{
			
			//Fix this
			cursor = matches[i];
			System.out.println(presentWorkingDirectory());
		
		}
		
		DirectoryNode.clearList();
		cursor = saved;
		
	}
	
	/**
	 * Removes a node from the tree. 
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 * 		'path' is a valid name
	 * 		'path' references an absolute path
	 * 
	 * <dt><b>Postconditions:</b><dd>
	 * 		the node referenced by 'path' and all of its subnodes have been removed from the tree
	 * 		the cursor is at the root position   
	 * 
	 * @param path
	 * 		the path of the node to delete
	 */
	public void remove(String path) throws NotADirectoryException, IllegalArgumentException
	{
		
		//An array containing each next directory
		String parsedInput[] = path.split("/");
		
		//Gets the name of the node to delete,
		//which is the last node name of the string
		String nameToDelete = parsedInput[parsedInput.length-1];
		
		//If the user is trying to delete root, throw an exception
		if(nameToDelete.equals("root"))
			throw new IllegalArgumentException(nameToDelete+ ": cannot delete root node. That's a little bit like deleting the universe");
		//Otherwise if the path input is not an absolute path, throw an exception
		else if(parsedInput.length == 1)
			throw new IllegalArgumentException(path + ": please use an absolute path");
		
		//Create a new path to enter the node that contains what we want to delete
		String newPath = "";
		
		for(int i = 0; i < parsedInput.length-2; i++)
			newPath += parsedInput[i] + "/";
		
		newPath += parsedInput[parsedInput.length-2];
		
		//NewPath now has the form root/<path to parent of node to delete>
		
		//Change the cursor into the node to be deleted
		changeDirectory(newPath);
		
		//Saves the children
		DirectoryNode[] children = cursor.getChildren();
		
		for(int i = 0; i < children.length; i++)
		{
			//If the child is not null and its name matches
			if(children[i] != null && children[i].getName().equals(nameToDelete))
			{
				//Delete the node
				children[i] = null;
				
				resetCursor();
				
				return;//Don't do anything else
				
			}
			
		}
		
		//Otherwise the node wasn't found - throw an exception
		throw new NotADirectoryException(nameToDelete + ": file/directory not found");
		
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
