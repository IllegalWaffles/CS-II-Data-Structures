import java.io.Serializable;
import big.data.*;

public class AuctionTable implements Serializable {

	public static AuctionTable buildFromUrl(String URL) throws IllegalArgumentException
	{
		
		return null;
		
	}
	
	public boolean put(String auctionID, Auction auction)
	{
		
		return false;
		
	}
	
	public Auction get(String auctionID)
	{
		
		return null;
		
	}
	
	public void letTimePass(int numHours) throws IllegalArgumentException
	{
		
		if(numHours < 0)
			throw new IllegalArgumentException("Cannot pass a negative amount of time");
		
		
	}
	
	public void printTable()
	{
		
		
		
		
	}
	
}