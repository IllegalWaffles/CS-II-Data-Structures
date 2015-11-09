import java.io.Serializable;

public class Auction implements Serializable {

	private int timeRemaining;
	private double currentBid;
	private String auctionID, sellerName, buyerName, itemInfo;
	
	public Auction()
	{
		
		
		
	}
	
	public void decrementTimeRemaining(int time)
	{
		
		timeRemaining -= time;
		
	}
	
	public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException
	{
		
		if(timeRemaining == 0)
			throw new ClosedAuctionException("Auction is closed");
		
		
	}
	
	public String toString()
	{
		
		return "";
		
	}
	
}
