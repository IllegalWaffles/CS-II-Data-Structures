import java.util.Comparator;

/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #7
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun
 * 
 * Class used to compare two WebPages by Pagerank.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class RankComparator implements Comparator<WebPage>{

	public int compare(WebPage a, WebPage b) 
	{
		
		if(a.getRank() == b.getRank())
			return 0;
		else if(a.getRank() < b.getRank())
			return 1;
		else
			return -1;
		
	}
	
}
