import java.io.Serializable;

/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #6
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * This class represents an auction.
 * This auction can be bid upon and 
 * time can be passed without anything 
 * taking place.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class Auction implements Serializable {

	private int timeRemaining;
	private double currentBid;
	private String auctionID, sellerName, buyerName, itemInfo;
	
	/**
	 * Creates a new auction with an empty bid and empty buyer.
	 * These aren't set until at least one bidder bids.
	 * 
	 * @param auctionID
	 * 		the id of this auction
	 * @param sellerName
	 * 		the name of the seller
	 * @param itemInfo
	 * 		the information of the item on sale
	 * @param timeRemaining
	 * 		the time remaining for this auction
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
	
	/**
	 * Returns the time remaining
	 * until this auction closes.
	 * 
	 * @return
	 * 		the time until this auction closes
	 */
	public int getTimeRemaining(){return timeRemaining;}
	
	/**
	 * Returns the current highest
	 * bid.
	 * 
	 * @return
	 * 		gets the current highest bid
	 */
	public double getCurrentBid(){return currentBid;}
	
	/**
	 * Returns the AuctionID of this object.
	 * 
	 * @return
	 * 		the AuctionID of this Auction
	 */
	public String getAuctionID(){return auctionID;}
	
	/**
	 * Returns the seller name of this
	 * auction.
	 * 
	 * @return
	 * 		the name of the seller of this auction
	 */
	public String getSellerName(){return sellerName;}
	
	/**
	 * Gets the name of the buyer of this auction
	 * 
	 * @return
	 * 		the name of the buyer of this auction
	 */
	public String getBuyerName(){return buyerName;}
	
	/**
	 * Returns the information about this auction item.
	 * 
	 * @return
	 * 		the information of this auction
	 */
	public String getItemInfo(){return itemInfo;}
	
	/**
	 * Decrements the timeRemaining of this
	 * object by time.
	 * 
	 * @param time
	 * 		the time to decrement this auction by
	 */
	public void decrementTimeRemaining(int time)
	{
		
		timeRemaining -= time;
		
		if(timeRemaining < 0)
			timeRemaining = 0;
		
	}
	
	/**
	 * Places a new bid on this auction.
	 * 
	 * @param bidderName
	 * 		the name of the person bidding
	 * @param bidAmt
	 * 		the amount the person is bidding
	 * @throws ClosedAuctionException
	 * 		indicates this auction is closed and cannot be bid upon
	 */
	public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException
	{
		
		if(timeRemaining == 0)
			throw new ClosedAuctionException("Auction is closed");
		
		if(bidAmt > currentBid)
		{
		
			currentBid = bidAmt;
			buyerName = bidderName;
		
		}
		
	}
	
	/**
	 * Returns a String representation of this object.
	 */
	public String toString()
	{
		
		String format = "%11s | $%9.2f | %-22s| %-24s| %3d hours | %-1.42s";
		
		return String.format(format, auctionID, currentBid, sellerName, buyerName, timeRemaining, itemInfo);
		
	}
	
}
