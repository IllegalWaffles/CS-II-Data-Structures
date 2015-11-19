import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author Kuba Gasiorowski
 *
 */
public class WebGraph {

	private Collection<WebPage> pages;
	private int links[][];
	
	public static final int MAX_PAGES = 40;
	
	public WebGraph()
	{
		
		links = new int[MAX_PAGES][MAX_PAGES];
		pages = new ArrayList<WebPage>();
		
	}
	
	public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException
	{
		
		
		
		return null;
		
	}
	
	public void addPage(String url, Collection<String> keywords) throws IllegalArgumentException
	{
	
		if(url == null || keywords == null)
			throw new IllegalArgumentException("Page not added, one of the arguments was null");
		
		for(WebPage w : pages)
			if(w.getURL().equals(url))
				throw new IllegalArgumentException("Page not added, this url already contained");
		
		pages.add(new WebPage(url, keywords));		
		
		
	}
	
	public void addLink(String source, String destination)
	{
		
		int sourceIndex = -1, destinationIndex = -1;
		
		//Find the index of the source page
		for(int i = 0; i < pages.size(); i++)
			if(((ArrayList<WebPage>)pages).get(i).getURL().equals(source))
			{
			
				sourceIndex = i;
				break;
			
			}
	
		//Find the index of the destination page
		for(int i = 0; i < pages.size(); i++)
			if(((ArrayList<WebPage>)pages).get(i).getURL().equals(destination))
			{
				
				destinationIndex = i;
				break;
				
			}
		
		try{
			
			links[sourceIndex][destinationIndex] = 1;
		
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
		
			System.out.println("Link not added, an exception was thrown");
			
		}
		
	}
	
	public void removePage(String url)
	{
		
		
		
	}
	
	public void removeLink(String source, String destination)
	{
		
		int sourceIndex = -1, destinationIndex = -1;
		
		//Find the index of the source page
		for(int i = 0; i < pages.size(); i++)
			if(((ArrayList<WebPage>)pages).get(i).getURL().equals(source))
			{
			
				sourceIndex = i;
				break;
			
			}
	
		//Find the index of the destination page
		for(int i = 0; i < pages.size(); i++)
			if(((ArrayList<WebPage>)pages).get(i).getURL().equals(destination))
			{
				
				destinationIndex = i;
				break;
				
			}
		
		try{
			
			links[sourceIndex][destinationIndex] = 0;
		
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
		
			System.out.println("Link not removed, an exception was thrown");
			
		}
		
	}
	
	public void printTable()
	{
		
		
		
		
	}
	
}
