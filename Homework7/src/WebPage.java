import java.util.Collection;

/**
 * 
 * @author Kuba Gasiorowski
 *
 */
public class WebPage {

	private String url;
	private int rank, index;
	private Collection<String> keywords;
	
	public WebPage(String url, Collection<String> keywords, int index)
	{
		
		this(url, keywords);
		this.index = index;
		
	}
	
	public WebPage(String url, Collection<String> keywords)
	{
		
		this.url = url;
		this.keywords = keywords;
		index = 0;
		
	}
	
	public String toString()
	{
		
		return url;
		
	}
	
	public int getIndex()
	{
		
		return index;
		
	}
	
	public int getRank()
	{
		
		return rank;
		
	}
	
	public Collection<String> getKeywords()
	{
		
		return keywords;
		
	}
	
	public String getURL()
	{
		
		return url;
		
	}
}
