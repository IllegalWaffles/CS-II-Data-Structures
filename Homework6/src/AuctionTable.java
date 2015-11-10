import java.io.Serializable;
import big.data.*;
import java.util.Hashtable;;

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
public class AuctionTable implements Serializable {

	/**
	 * Takes a url and builds a table of auctions
	 * based upon to input.
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
	
		AuctionTable newTable = new AuctionTable();
		
		DataSource ds = DataSource.connect(URL).load();
		String input = ds.fetchString("listing/seller_info/seller_name");
		
		System.out.println(input);
		
		return newTable;
		
	}
	
	/**
	 * Inserts a new auction into the Hashmap.
	 * 
	 * @param auctionID
	 * 		the ID of the auction to insert
	 * @param auction
	 * 		the auction to insert
	 * @return
	 * 		true if the auction could be inserted; false otherwise
	 */
	public boolean put(String auctionID, Auction auction)
	{
		
		return false;
		
	}
	
	/**
	 * Gets the auction with the matching auctionID.
	 * 
	 * @param auctionID
	 * 		the auctionID of the auction to find
	 * 		
	 * @return
	 * 		the auction with auctionID, null if the auction does not exist
	 */
	public Auction get(String auctionID)
	{
		
		return null;
		
	}
	
	/**
	 * Passes 'numHours' hours for all auctions
	 * in the Hashmap.
	 * 
	 * @param numHours
	 * @throws IllegalArgumentException
	 */
	public void letTimePass(int numHours) throws IllegalArgumentException
	{
		
		if(numHours < 0)
			throw new IllegalArgumentException("Cannot pass a negative amount of time");
		
		
	}
	
	/**
	 * Prints a neatly formatted representation of this AuctionTable.
	 */
	public void printTable()
	{
		
		
		
		
	}
	
}