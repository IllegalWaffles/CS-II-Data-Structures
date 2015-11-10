import java.io.Serializable;
import big.data.*;
import java.util.Hashtable;;

public class AuctionTable implements Serializable {

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
		
		try{
		
		DataSource ds = DataSource.connect(URL).load();
		String input = ds.fetchString("listing/seller_info/seller_name");
		
		System.out.println(input);
		
		}
		catch(Exception e)
		{
			
			System.out.println(e.getMessage());
			
		}
		
		return newTable;
		
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