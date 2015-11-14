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
	
		/*
		 
		If we have the URL of the XML file, we can connect to it using the library functions. 
		First, we must connect() to the URL, then load() the data. Finally, we can fetch() the information.

		Sample Code:

		The file <sample_file.xml> contains the following content:
	
		<root>
    		<item>
        		<attr>1</attr>
    		</item>
    		<item>
        		<attr>2</attr>
    		</item>
		</root>
	
		import big.data.*;
		DataSource ds = DataSource.connect("sample_file.xml").load();
		String str = ds.fetchString("item/attr");
		// normally, we can extract a single string data in the path item/attr, but we have multiple values in our case. Instead,
		we should do the following:
		String[] myList = ds.fetchStringArray("item/attr");
		You will mostly use fetchStringArray() in this assignment. Sometimes, the information on the XML may not be complete. 
		You might find that your fetch will return an empty string (i.e. ""). In that case, you can replace it with the string: "N/A"
		 
		 */
	
		//Declare the variables we need
		AuctionTable newTable = new AuctionTable();
		DataSource ds = DataSource.connect(URL).load();
		String sellerNames[], auctionIDs[], itemInfos[], buyerNames[], timesRemaining[], highestBids[];
		String infoMemory[], infoHD[], infoCPU[];
		
		Auction toAdd;
		
		int numAuctions;
		
		//Extract the necessary data
		sellerNames = ds.fetchStringArray("listing/seller_info/seller_name");
		auctionIDs = ds.fetchStringArray("listing/auction_info/id_num");
		timesRemaining = ds.fetchStringArray("listing/auction_info/time_left");
		
		buyerNames = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
		highestBids = ds.fetchStringArray("listing/auction_info/current_bid");
		
		infoMemory = ds.fetchStringArray("listing/item_info/memory");
		infoHD = ds.fetchStringArray("listing/item_info/hard_drive");
		infoCPU = ds.fetchStringArray("listing/item_info/cpu");
		
		buyerNames = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
		
		numAuctions = auctionIDs.length;
		itemInfos = new String[numAuctions];
		
		for(int i = 0; i < numAuctions; i++)
		{
			
			//Organize the data
			itemInfos[i] = infoMemory[i] + " - " + infoHD[i] + " - " + infoCPU[i];
			
			//Create auction objects
			toAdd = new Auction(auctionIDs[i], sellerNames[i], itemInfos[i], parseTime(timesRemaining[i]));
			
			
			try{
			
				toAdd.newBid(buyerNames[i], parseCurrencyAmount(highestBids[i]));
			
				//Add them to the AuctionTable
				newTable.putAuction(auctionIDs[i], toAdd);
				
			}
			catch(ClosedAuctionException e)
			{
				
				System.out.println(" Error: " + e.getMessage() + "\n Auction " + auctionIDs[i] + "not added to table");
				
			}
			
		}
		
		//Return
		return newTable;
		
	}
	
	private static int parseTime(String s)
	{
		
		
		
		return 0;
		
	}
	
	private static double parseCurrencyAmount(String s)
	{
		
		
		
		return 0;
		
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
		
		return this.get(auctionID);
		
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
		
		
		
		
	}
	
}