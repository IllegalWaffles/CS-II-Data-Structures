import java.util.Comparator;

/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #7
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * Class used to compare two WebPages by URL.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class URLComparator implements Comparator<WebPage>{

	public int compare(WebPage a, WebPage b)
	{
	
		return a.getURL().compareTo(b.getURL());
		
	}

}
