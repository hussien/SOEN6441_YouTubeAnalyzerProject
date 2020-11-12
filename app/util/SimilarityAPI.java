package util;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
/**
 * this class measures text Similarity 
 * by comparing words between source and destination
 * @author Marwah
 *
 */
public class SimilarityAPI {
	
	/**
	 * this function measures number of target term found in source string
	 * @param src source string to search in
	 * @param target the term that we need to measure its similarity
	 * @return number of target term found in source string
	 * @author Marwah
	 */
	public static int measureSimilarity(String src,String target)
	{
		/*return Stream.of(src.split(" "))
	      .map (elem -> new String(elem))
	      .collect(Collectors.toList())
	      .parallelStream()
	      .map(elem-> elem.trim().equals(target.trim().toLowerCase())?1:0)
	      .reduce(0,Integer::sum);
		*/
		return StringUtils.countMatches(src.toLowerCase(), target.toLowerCase());
	}
	

}

