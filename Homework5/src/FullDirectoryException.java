/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #5
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * Thrown when all of a
 * directory's children are
 * occupied, and therefore has
 * no room for any additional children. 
 * 
 * @author Kuba Gasiorowski
 *
 */
public class FullDirectoryException extends Exception {
	
	public FullDirectoryException()
	{
		
		super();
		
	}
	
	public FullDirectoryException(String message)
	{
		
		super(message);
		
	}
	
}
