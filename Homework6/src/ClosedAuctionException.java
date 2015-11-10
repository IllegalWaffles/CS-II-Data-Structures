/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #6
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * Thrown to indicate that an auction is closed.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class ClosedAuctionException extends Exception {

	public ClosedAuctionException()
	{
		
		super();
		
	}
	
	
	public ClosedAuctionException(String s)
	{
		
		super(s);
		
	}
	
}
