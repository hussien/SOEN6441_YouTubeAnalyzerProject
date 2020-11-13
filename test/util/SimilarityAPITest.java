package util;

import java.util.List;

import models.SearchResultItem;

import org.junit.Assert;
import org.junit.Test;

/**
 * this class represents a test class for text SimilarityAPI 
 * @author Marwah
 */
public class SimilarityAPITest {
	
	/**
	 * test for measure similarity function
	 * @author Marwah
	 */
	@Test
    public void testmeasureSimilarity() 
 	{
        Assert.assertEquals(SimilarityAPI.measureSimilarity("ahmed went to  school with ahmed ", "ahmed"),2);
    }
}
