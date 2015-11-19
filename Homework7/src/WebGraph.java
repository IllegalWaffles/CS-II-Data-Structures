import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Kuba Gasiorowski
 *
 */
public class WebGraph {

	private ArrayList<WebPage> pages;
	private int links[][];
	
	public static final int MAX_PAGES = 40;
	
	public WebGraph()
	{
		
		links = new int[MAX_PAGES][MAX_PAGES];
		pages = new ArrayList<WebPage>();
		
	}
	
	public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException
	{
		
		Scanner sc;
		WebGraph web = new WebGraph();
		
		System.out.println("Loading WebGraph data...");
		
		//Code to read and parse the pages in the system
		try{
		
			sc = new Scanner(new File(pagesFile));
		
		}
		catch(FileNotFoundException e)
		{
			
			System.out.println("File " + pagesFile + " was not found. Cannot build data.");
			return null;
			
		}
		
		String[] parsedInput;
		String url;
		ArrayList<String> keywords;
		
		while(sc.hasNextLine())
		{
		
			keywords = new ArrayList<String>();
			
			parsedInput = sc.nextLine().trim().split(" ");
			url = parsedInput[0];
			
			for(int i = 1; i < parsedInput.length; i++)
				keywords.add(parsedInput[i]);
			
			web.getPages().add(new WebPage(url, keywords));
			
			
		}
		
		sc.close();
		
		//Code to read and parse the links between pages
		try{
			
			sc = new Scanner(new File(linksFile));
			
		}
		catch(FileNotFoundException e)
		{
			
			System.out.println("File " + pagesFile + " was not found. Cannot build data.");
			return null;
			
		}
		
		String src, dest;
		
		while(sc.hasNextLine())
		{
			
			parsedInput = sc.nextLine().trim().split(" ");
			src = parsedInput[0];
			dest = parsedInput[1];
			
			web.addLink(src, dest);
			
		}
		
		sc.close();
		
		System.out.println("Success!\n");
		
		return web;
		
	}
	
	public ArrayList<WebPage> getPages()
	{
		
		return pages;
		
	}
	
	public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException
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
			if(pages.get(i).getURL().equals(source))
			{
			
				sourceIndex = i;
				break;
			
			}
	
		//Find the index of the destination page
		for(int i = 0; i < pages.size(); i++)
			if(pages.get(i).getURL().equals(destination))
			{
				
				destinationIndex = i;
				break;
				
			}
		
		if(sourceIndex == -1)
			throw new IllegalArgumentException("Error: " + source + " not found in this WebGraph");
		else if(sourceIndex == -1)
			throw new IllegalArgumentException("Error: " + destination + " not found in this WebGraph");
		
		links[sourceIndex][destinationIndex] = 1;
		
		updatePageRanks();
		
	}
	
	public void removePage(String url)
	{
	
		//Code to remove pages from the collection
		//Also to remove all links as well
		
		//If the url is null, do nothing
		if(url == null)
			return;
		
		int indexToRemove = -1;
		
		for(int i = 0; i < pages.size(); i++)
			if(pages.get(i).getURL().equals(url))
			{
				
				indexToRemove = i;
				break;
			
			}
		
		//If the url was not found, do nothing
		if(indexToRemove == -1)
			return;
		
		//Remove the page from pages
		pages.remove(indexToRemove);
		
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
			if(pages.get(i).getURL().equals(source))
			{
			
				sourceIndex = i;
				break;
			
			}
	
		//Find the index of the destination page
		for(int i = 0; i < pages.size(); i++)
			if(pages.get(i).getURL().equals(destination))
			{
				
				destinationIndex = i;
				break;
				
			}
		
		if(sourceIndex == -1)
			throw new IllegalArgumentException("Error: " + source + " not found in this WebGraph");
		
		else if(sourceIndex == -1)
			throw new IllegalArgumentException("Error: " + destination + " not found in this WebGraph");
		
		links[sourceIndex][destinationIndex] = 0;
		
		updatePageRanks();
		
	}
	
	private void updatePageRanks()
	{
		
		//This code updates the page ranks for each page
		//Call after removing or adding a page or link
		
	}
	
	public void printTable()
	{
		
		String toPrint = "";
		int i = 0;
		
		for(WebPage p : pages)
		{
		
			toPrint = i++ + "" + p;			
			//toPrint = toPrint.replace("###", getLinks(i));
			System.out.println(toPrint);
		
		}
		
	}
	
	private String getLinks(int src)
	{
		
		String toReturn = "";
		
		/*
		
		for(int i = 0; i < pages.size(); i++)
		{
			
			if(links[i][src] == 1)
				toReturn = i + ", ";
				
		}
		
		*/
		
		return toReturn;
		
	}
	
	public void printLinkArray()
	{
		
		for(int i = 0; i < pages.size(); i++)
		{
			
			for(int j = 0; j < pages.size(); j++)
				System.out.print(links[i][j] + " ");
	
			System.out.println();
			
		}
		
	}
	
}
