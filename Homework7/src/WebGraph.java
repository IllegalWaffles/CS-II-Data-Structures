import java.util.Collection;

/**
 * 
 * @author Kuba Gasiorowski
 *
 */
public class WebGraph {

	private Collection<WebPage> pages;
	private int edges[][];
	
	public static final int MAX_PAGES = 40;
	
	public WebGraph()
	{
		
		edges = new int[MAX_PAGES][MAX_PAGES];
		
	}
	
	public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException
	{
		
		
		
		return null;
		
	}
	
	public void addPage(String url, Collection<String> keywords) throws IllegalArgumentException
	{
		
		
		
	}
	
	public void addLink(String source, String destination)
	{
		
		
		
	}
	
	public void removePage(String url)
	{
		
		
		
	}
	
	public void removeLink(String url)
	{
		
		
		
	}
	
	public void printTable()
	{
		
		
		
	}
	
}
