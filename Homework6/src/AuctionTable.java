import java.io.Serializable;
import big.data.*;
import java.util.Hashtable;
import java.util.Set;

/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #6
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * This class represents a table of auctions.
 * Utilizes a java-API Hashtable.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class AuctionTable extends Hashtable<String, Auction> implements Serializable {
	
	private final String TAB = "   ";
	
	/**
	 * Default constructor for AuctionTable
	 */
	public AuctionTable()
	{
		
		super();
		
	}
	
	/**
	 * Takes a url and builds a table of auctions
	 * based upon the input.
	 * 
	 * @param URL
	 * 		the url of the source of data
	 * @return
	 * 		a new table of auctions corresponding to the
	 * 		data found at "URL" 
	 * @throws IllegalArgumentException
	 * 		indicates the URL is malformed or contains no data
	 */
	public static AuctionTable buildFromUrl(String URL) throws IllegalArgumentException
	{
	
		//Declare the variables we need
		AuctionTable newTable = new AuctionTable();
		DataSource ds = DataSource.connect(URL).load();
		String sellerNames[], auctionIDs[], itemInfos[], buyerNames[], timesRemaining[], highestBids[];
		String infoMemory[], infoHD[], infoCPU[];
		
		Auction toAdd;
		
		int numAuctions;
		
		//Extract the necessary data
		try{
		
			sellerNames = ds.fetchStringArray("listing/seller_info/seller_name");
			replaceEmptyStrings(sellerNames);
			auctionIDs = ds.fetchStringArray("listing/auction_info/id_num");
			replaceEmptyStrings(auctionIDs);
			timesRemaining = ds.fetchStringArray("listing/auction_info/time_left");
			replaceEmptyStrings(timesRemaining);
			
			buyerNames = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
			replaceEmptyStrings(buyerNames);
			highestBids = ds.fetchStringArray("listing/auction_info/current_bid");
			replaceEmptyStrings(highestBids);
			
			infoMemory = ds.fetchStringArray("listing/item_info/memory");
			replaceEmptyStrings(infoMemory);
			infoHD = ds.fetchStringArray("listing/item_info/hard_drive");
			replaceEmptyStrings(infoHD);
			infoCPU = ds.fetchStringArray("listing/item_info/cpu");
			replaceEmptyStrings(infoCPU);

			numAuctions = auctionIDs.length;
			itemInfos = new String[numAuctions];

			for(int i = 0; i < numAuctions; i++)
			{

				//Organize the data
				itemInfos[i] = infoMemory[i] + " - " + infoHD[i] + " - " + infoCPU[i];

				//Create auction objects
				toAdd = new Auction(auctionIDs[i], sellerNames[i], itemInfos[i], parseTime(timesRemaining[i]), parseCurrencyAmount(highestBids[i]), buyerNames[i]);

				//Add them to the AuctionTable
				newTable.putAuction(auctionIDs[i], toAdd);

			}
		
		}
		catch(DataSourceException e)
		{
			
			throw new IllegalArgumentException("No data found. Possible malformed URL");
			
		}
		
		return newTable;
		
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	private static int parseTime(String s)
	{
		
		int time = 0;
		
		s = s.replaceAll(",", "");
		s = s.trim();
		
		String parsedTime[] = s.split(" ");
		
		for(int i = 0; i < parsedTime.length; i++)
			if(parsedTime[i].equals("days") || parsedTime[i].equals("day"))
				time += 24 * Integer.parseInt(parsedTime[i-1]);
			else if(parsedTime[i].equals("hours") || parsedTime[i].equals("hour"))
				time += Integer.parseInt(parsedTime[i-1]);
		
		return time;
		
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	private static double parseCurrencyAmount(String s)
	{
		
		return Double.parseDouble(s.substring(1).replaceAll(",", ""));
		
	}
	
	/**
	 * 
	 * @param array
	 */
	private static void replaceEmptyStrings(String[] array)
	{
		
		for(int i = 0; i < array.length; i++)
			if(array[i].equals(""))
				array[i] = "N/A";
		
	}
	
	/**
	 * Adds a new auction into this AuctionTable
	 * 
	 * @param auctionID
	 * 		the key of the new auction
	 * @param auction
	 * 		the new auction to insert
	 * @throws IllegalArgumentException
	 * 		indicates that the given auction is already contained in this
	 * 		AuctionTable
	 */
	public void putAuction(String auctionID, Auction auction) throws IllegalArgumentException
	{
		
		if(this.containsKey(auctionID))
			throw new IllegalArgumentException("AuctionTable already contains this auctionID");
		
		put(auctionID, auction);
		
	}
	
	/**
	 * Returns the auction with the given auctionId.
	 * 
	 * @param auctionID
	 * 		the id of the auction to get
	 * @return
	 * 		the auction with the id auctionID
	 */
	public Auction getAuction(String auctionID)
	{
		
		return get(auctionID);
		
	}
	
	/**
	 * Passes 'numHours' hours for all auctions
	 * in the Hashmap.
	 * 
	 * @param numHours
	 * 		number of hours to be passed
	 * @throws IllegalArgumentException
	 * 		indicates an illegal argument (i.e. negative time)
	 */
	public void letTimePass(int numHours) throws IllegalArgumentException
	{
		
		//If the time to pass is invalid, throw an exception
		if(numHours < 0)
			throw new IllegalArgumentException("Cannot pass a negative amount of time");
		
		//Gets a list of keys used in this table
		Set<String> keySet = this.keySet();
		
		//Gets each object and operates on it
		for(String s: keySet)
			this.getAuction(s).decrementTimeRemaining(numHours);
		
	}
	
	/**
	 * Prints a neatly formatted representation of this AuctionTable.
	 */
	public void printTable()
	{
		
		System.out.println(" Auction ID |      Bid   |        Seller         |          Buyer          |    Time   |  Item Info\n"
						 + "===================================================================================================================================");
		
		Set<String> keySet = this.keySet();
		
		for(String s: keySet)
			System.out.println(get(s));
		
	}
	
	/**
	 * Removes all closed auctions from this AuctionTable
	 */
	public void removeClosedAuctions()
	{
		
		Set<String> keySet = this.keySet();
		String[] keysToRemove = new String[keySet.size()];
		int i = 0;
		
		for(String s : keySet)
			if(this.getAuction(s).getTimeRemaining() == 0)
				keysToRemove[i++] = s;
		
		for(String s: keysToRemove)
			if(s != null)
				remove(s);
		
	}
	
	/**
	 * 
	 * @param auctionID
	 * @throws IllegalArgumentException
	 */
	public void printAuctionInfo(String auctionID) throws IllegalArgumentException
	{
		
		Auction auctionToPrint = get(auctionID);
		
		if(auctionToPrint == null)
			throw new IllegalArgumentException("Auction " + auctionID + " not found");
		
		
		System.out.println("Auction: " + auctionID
				+ "\n" + TAB + "Seller: " + auctionToPrint.getSellerName()
				+ "\n" + TAB + "Buyer: " + auctionToPrint.getBuyerName()
				+ "\n" + TAB + "Time: " + auctionToPrint.getTimeRemaining() + " hours"
				+ "\n" + TAB + "Info: " + auctionToPrint.getItemInfo());
		
	}
	
}