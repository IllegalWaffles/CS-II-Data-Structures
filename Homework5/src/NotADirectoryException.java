/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #4
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * Thrown when a node
 * is not a directory and therefore
 * cannot be operated on.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class NotADirectoryException extends Exception {

	public NotADirectoryException()
	{
		
		super();
		
	}
	
	public NotADirectoryException(String message)
	{
		
		super(message);
		
	}
	
}
