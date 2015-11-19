import java.util.ArrayList;

/**
 * 
 * @author Kuba Gasiorowski
 *
 */
public class WebPage {

	private String url;
	private int rank, index;
	private ArrayList<String> keywords;
	
	public WebPage(String url, ArrayList<String> keywords, int index)
	{
		
		this(url, keywords);
		this.index = index;
		
	}
	
	public WebPage(String url, ArrayList<String> keywords)
	{
		
		this.url = url;
		this.keywords = keywords;
		index = 0;
		
	}
	
	public int getIndex()
	{
		
		return index;
		
	}
	
	public int getRank()
	{
		
		return rank;
		
	}
	
	public ArrayList<String> getKeywords()
	{
		
		return keywords;
		
	}
	
	public String getURL()
	{
		
		return url;
		
	}
	
	public String toString()
	{
		
		return " | " + url + " |###| " + keywords;
		
	}
	
}
