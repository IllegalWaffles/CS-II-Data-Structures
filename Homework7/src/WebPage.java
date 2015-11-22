import java.util.ArrayList;

/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #7
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * This class represents a basic webpage.
 * Contains a url, a collection of keywords,
 * and a pagerank.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class WebPage {

	private String url;
	private int rank, index;
	private ArrayList<String> keywords;
	
	/**
	 * Creates a new WebPage with the given parameters.
	 * 
	 * @param url
	 * 		the url of this webpage
	 * @param keywords
	 * 		the keywords associated with this page
	 * @param index
	 * 		the index of this page
	 */
	public WebPage(String url, ArrayList<String> keywords, int index)
	{
		
		this.url = url;
		this.keywords = keywords;
		this.index = index;
		
	}
	
	/**
	 * Gets the index of this page.
	 * 
	 * @return
	 * 		the index of this page
	 */
	public int getIndex()
	{
		
		return index;
		
	}
	
	/**
	 * Sets the index of this WebPage to whatever is passed in.
	 * 
	 * @param index
	 * 		The new index of this WebPage.
	 */
	public void setIndex(int index)
	{
		
		this.index = index;
		
	}
	
	/**
	 * Returns the calculated pagerank of this page.
	 * 
	 * @return
	 * 		the rank of this page
	 */
	public int getRank()
	{
		
		return rank;
		
	}
	
	/**
	 * Sets the pagerank for this webpage.
	 * 
	 * @param rank
	 * 		the new rank of this WebPage
	 */
	public void setRank(int rank)
	{
		
		this.rank = rank;
		
	}
	
	/**
	 * Gets the keywords associated with this page.
	 * 
	 * @return
	 * 		the keywords associated with this page
	 */
	public ArrayList<String> getKeywords()
	{
		
		return keywords;
		
	}
	
	/**
	 * Gets the URL of this page.
	 * 
	 * @return
	 * 		the url of this page
	 */
	public String getURL()
	{
		
		return url;
		
	}
	
	/**
	 * Returns a String representation of this object.
	 */
	public String toString()
	{
		
		String format = "%3d   | %-19s |%5d    | %s";
		return String.format(format, index, url, rank, "###");
		
	}
	
}
