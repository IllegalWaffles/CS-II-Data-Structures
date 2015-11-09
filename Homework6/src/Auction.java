import java.io.Serializable;

public class Auction implements Serializable {

	private int timeRemaining;
	private double currentBid;
	private String auctionID, sellerName, buyerName, itemInfo;
	
	/**
	 * 
	 * @param auctionID
	 * @param sellerName
	 * @param itemInfo
	 * @param timeRemaining
	 */
	public Auction(String auctionID, String sellerName, String itemInfo, int timeRemaining)
	{
		
		this.timeRemaining = timeRemaining;
		currentBid = 0;
		buyerName = "";
		this.auctionID = auctionID;
		this.sellerName = sellerName;
		this.itemInfo = itemInfo;
		
	}
	
	public int getTimeRemaining(){return timeRemaining;}
	public double getCurrentBid(){return currentBid;}
	public String getAuctionID(){return auctionID;}
	public String getSellerName(){return sellerName;}
	public String getBuyerName(){return buyerName;}
	public String getItemInfo(){return itemInfo;}
	
	/**
	 * 
	 * @param time
	 */
	public void decrementTimeRemaining(int time)
	{
		
		timeRemaining -= time;
		
	}
	
	/**
	 * 
	 * @param bidderName
	 * @param bidAmt
	 * @throws ClosedAuctionException
	 */
	public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException
	{
		
		if(timeRemaining == 0)
			throw new ClosedAuctionException("Auction is closed");
		
		if(bidAmt > currentBid)
			currentBid = bidAmt;
		
		buyerName = bidderName;
		
		
	}
	
	/**
	 * 
	 */
	public String toString()
	{
		
		
		
		return "";
		
	}
	
	
	
}
