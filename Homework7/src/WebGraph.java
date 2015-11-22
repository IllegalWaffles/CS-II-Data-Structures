import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #7
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * Represents a web graph. Contains a collection
 * of webpages, with links coming from certain 
 * pages to others.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class WebGraph {

	private ArrayList<WebPage> pages;
	private int links[][];
	
	public static final int MAX_PAGES = 40;
	
	/**
	 * Creates a new WebGraph with empty fields.
	 */
	public WebGraph()
	{
		
		links = new int[MAX_PAGES][MAX_PAGES];
		pages = new ArrayList<WebPage>();
		
	}
	
	/**
	 * Builds a WebGraph from the two files passed in, one
	 * containing pages, and the other containing 
	 * 
	 * @param pagesFile
	 * 		the file where page information is stored
	 * @param linksFile
	 * 		the file where link information is stored
	 * @return
	 * 		a new WebGraph build from these two files
	 * @throws IllegalArgumentException
	 * 		one or both do not exist
	 */
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
			
			web.addPage(url, keywords);
			
			
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
	
	/**
	 * Adds a page to this WebGraph
	 * 
	 * @param url
	 * 		the url of the new page
	 * @param keywords
	 * 		the keywords associated with the new page to add
	 * @throws IllegalArgumentException
	 * 		indicates that one of the arguments was null
	 * 		indicates that the page is already contained
	 */
	public boolean addPage(String url, Collection<String> keywords)
	{
	
		if(url == null || keywords == null)
			return false;
		
		for(WebPage w : pages)
			if(w.getURL().equals(url))
				return false;
		
		pages.add(new WebPage(url, (ArrayList<String>)keywords, pages.size()));		
		
		updatePageRanks();
		
		//Indicates everything went smoothly and the method
		//executed without issue
		return true;
		
	}
	
	/**
	 * Adds a link between two pages of this WebGraph.
	 * 
	 * @param source
	 * 		the source of the link
	 * @param destination
	 * 		the destination of this link
	 */
	public boolean addLink(String source, String destination)
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
			return false;
		else if(sourceIndex == -1)
			return false;
		
		links[sourceIndex][destinationIndex] = 1;
		
		updatePageRanks();
		
		//Indicates everything went smoothly and the method
		//executed without issue
		return true;
		
	}
	
	/**
	 * Removes a page from this WebGraph.
	 * 
	 * @param url
	 * 		the url of the page to remove
	 */
	public boolean removePage(String url)
	{
	
		//Code to remove pages from the collection
		//Also to remove all links as well
		
		//If the url is null, do nothing
		if(url == null)
			return false;
		
		int indexToRemove = -1;
		
		for(int i = 0; i < pages.size(); i++)
			if(pages.get(i).getURL().equals(url))
			{
				
				indexToRemove = i;
				break;
			
			}
		
		//If the url was not found, do nothing
		if(indexToRemove == -1)
			return false;
		
		//Remove the page from pages
		pages.remove(indexToRemove);
		
		//Resets the indexes of each WebPage
		int i = 0;
		for(WebPage w : pages)
			w.setIndex(i++);
			
		//Remove the row and columns from the list array
		removeRowAndColumn(indexToRemove);
		
		//Updates the displaced page indices
		updatePageIndices();
		
		//Updates the new page ranks for this graph
		updatePageRanks();
		
		//Indicates everything went smoothly and the method
		//executed without issue
		return true;
		
	}
	
	/**
	 * A helper method to remove a row and column of a matrix.
	 * 
	 * @param index
	 * 		
	 */
	private void removeRowAndColumn(int index)
	{
		
		for(int j = index; j < MAX_PAGES - 2; j++)
			for(int i = 0; i < MAX_PAGES - 1; i++)
				links[i][j] = links[i][j+1];
		
		for(int i = index; i < MAX_PAGES - 2; i++)
			for(int j = 0; j < MAX_PAGES - 1; j++)
				links[i][j] = links[i+1][j];
		
		//Code to set the outer row and column links to 0. 
		//So if pages.size() is 3, set row and column index
		//3 to all 0's (remove all these links), since they are
		//Out of the bounds of our logical matrix
		
		for(int i = 0; i < pages.size(); i++)
			links[pages.size()][i] = 0;
		
		for(int i = 0; i < pages.size(); i++)
			links[i][pages.size()] = 0;
		
	}
	
	/**
	 * Updates the page indices of each WebPage in this WebGraph.
	 */
	private void updatePageIndices()
	{
		
		int i = 0;
		for(WebPage w : pages)
			w.setIndex(i++);
		
	}
	
	/**
	 * Removes a link between two pages.
	 * 
	 * @param source
	 * 		the source of the link
	 * @param destination
	 * 		the destination of the link
	 */
	public boolean removeLink(String source, String destination)
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
			return false;
		
		if(destinationIndex == -1)
			return false;
		
		links[sourceIndex][destinationIndex] = 0;
		
		updatePageRanks();
		
		//Indicates everything went smoothly and the method
		//executed without issue
		return true;
		
	}
	
	/**
	 * Cycles through each page and updates the pagerank for each one.
	 */
	private void updatePageRanks()
	{
		
		//This code updates the page ranks for each page
		//Call after removing or adding a page or link
		int rank = 0;
		
		for(WebPage w : pages)
		{
			
			rank = 0;
			
			for(int i = 0; i < pages.size(); i++)
				rank += links[i][w.getIndex()];
			
			w.setRank(rank);
			
		}
		
	}
	
	/**
	 * Prints a neatly formatted table for this webgraph.
	 */
	public void printTable()
	{
		
		System.out.println("Index   URL                  PageRank   Links               Keywords\n"
						 + "-------------------------------------------------------------------------------------------------------");
		
		for(WebPage p : pages)
			System.out.println(p);
		
	}
	
	/**
	 * Sorts the WebGraph page list by index.
	 */
	public void sortByIndex()
	{
		
		Collections.sort(pages, new IndexComparator());
		
	}
	
	/**
	 * Sorts the WebGraph page list by pagerank.
	 */
	public void sortByRank()
	{
		
		Collections.sort(pages, new RankComparator());
		
	}
	
	/**
	 * Sorts the WebGraph page list by URL. (alphabetically)
	 */
	public void sortByURL()
	{
		
		Collections.sort(pages, new URLComparator());
		
	}
	
	/**
	 * Gets a string representing the links from this page.
	 * 
	 * @param src
	 * 		the source of links to look for
	 * @return
	 * 		a string that represents each link to each page from src
	 */
	private String getLinks(int src)
	{
		
		String toReturn = "";
		
		
		
		return toReturn;
		
	}
	
	/**
	 * Searches the graph for a certain keyword and
	 * prints a neatly formatted table of results.
	 * 
	 * @param keyword
	 * 		the keyword to search for
	 */
	public void searchForKeyword(String keyword)
	{
		
		//Code to find our search results and sort them in
		//PageRank order
		ArrayList<WebPage> searchResults = new ArrayList<WebPage>();
		
		for(WebPage w : pages)
			for(String s : w.getKeywords())
				if(s.equals(keyword))
				{
					
					searchResults.add(w);
					break;
					
				}
		
		Collections.sort(searchResults, new RankComparator());
		
		//Code to print the results in a tabular format
		
		String format = "%3d  |%5d     | %s";
		int i = 1;
		
		System.out.println("\nResults:\nRank   PageRank    URL\n---------------------------------------------");
		
		if(searchResults.size() == 0)
			System.out.println("(No results)");
		else
			for(WebPage w: searchResults)
				System.out.println(String.format(format, i++, w.getRank(), w.getURL()));
		
		
	}
	
	
	/**
	 * Prints the two-dimensional link array.
	 * 
	 * Used for debugging purposes.
	 * 
	 */
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
