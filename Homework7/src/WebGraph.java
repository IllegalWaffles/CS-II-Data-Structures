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
		
		updatePageRanks();
		
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
		
		updatePageRanks();
		
	}
	
	public void removePage(String url)
	{
	
		//Code to remove pages from the collection
		//Also to remove all links as well
		
		ArrayList<WebPage> pageList = (ArrayList<WebPage>)pages;
		
		//If the url is null, do nothing
		if(url == null)
			return;
		
		int indexToRemove = -1;
		
		for(int i = 0; i < pages.size(); i++)
			if(pageList.get(i).getURL().equals(url))
			{
				
				indexToRemove = i;
				break;
			
			}
		
		//If the url was not found, do nothing
		if(indexToRemove == -1)
			return;
		
		//Remove the page from pages
		pageList.remove(indexToRemove);
		
		//Remove the row and columns from the list array
		removeRowAndColumn(indexToRemove);
		
		updatePageRanks();
		
	}
	
	private void removeRowAndColumn(int index)
	{
		
		for(int j = index; j < MAX_PAGES - 1; j++)
			for(int i = 0; i < MAX_PAGES - 1; i++)
				links[i][j] = links[i][j+1];
		
		for(int i = index; i < MAX_PAGES - 1; i++)
			for(int j = 0; j < MAX_PAGES - 1; j++)
				links[i][j] = links[i+1][j];
		
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
		
		updatePageRanks();
		
	}
	
	private void updatePageRanks()
	{
		
		//This code updates the page ranks for each page
		//Call after removing or adding a page or link
		
	}
	
	public void printTable()
	{
		
		
		
	}
	
	public void printLinkTable()
	{
		
		for(int i = 0; i < pages.size(); i++)
		{
			
			for(int j = 0; j < pages.size(); j++)
				System.out.print(links[i][j] + " ");
	
			System.out.println();
			
		}
		
	}
	
}
