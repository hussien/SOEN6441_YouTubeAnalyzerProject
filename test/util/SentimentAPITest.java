package util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;

/**
 * this class represents a test class for EmoijIcons Sentiment analysis Api 
 * to get happy and sad comments
 * @author Hussein
 */
public class SentimentAPITest {
		/**
		 * represents list of comments to analysis
		 */
	 	List<String> lst_comments;	    
	 	/**
	 	 * initialize Emoijicon sentiment analysis test environment
	 	 * @author Hussein 
	 	 */
	    @Before
		public void init() {
	    	lst_comments=Arrays.asList("we 😆 are 😀happy", "they are 😀happy 😃","😃😃😃","👍",
	    			"this is too boring 😒😒😒😒😒😒😒"," this is 😬","no 😪","😴😴😴 😴","😕😕","this bug ☹ 👎👎👎");
		}
	    
	    /**
	 	 * test if Happy Emoij not empty
	 	 * @author Hussein 
	 	 */	    
	    @Test 
	    public void TestgetHappyEmoij()
	    {
	    	List<String> lst=SentimentAPI.getHappyEmoij();
	    	Assert.assertNotNull(lst);
	        Assert.assertTrue(lst.contains("😀"));
	    }
	    

	    /**
	 	 * test if Sad Emoij not empty
	 	 * @author Hussein 
	 	 */	    
	    @Test 
	    public void TestgetSadEmoij()
	    {
	    	List<String> lst=SentimentAPI.getSadEmoij();
	    	Assert.assertNotNull(lst);
	        Assert.assertTrue(lst.contains("😴"));
	    }
	    
	    /**
	 	 * test if comments list contains happy Emoij
	 	 * @author Hussein 
	 	 */
	    @Test 
	    public void TestgetHappyEmoij_Comments()
	    {
	    	String emoij=SentimentAPI.getHappyEmoij(lst_comments);
	    	Assert.assertNotNull(emoij);
	        Assert.assertTrue(emoij.contains("😀"));
	    }
	    
	    /**
	 	 * test if comments list contains Sad Emoij
	 	 * @author Hussein 
	 	 */	    
	    @Test 
	    public void TestgetSadEmoij_Comments()
	    {
	    	String emoij=SentimentAPI.getSadEmoij(lst_comments);
	    	Assert.assertNotNull(emoij);
	        Assert.assertTrue(emoij.contains("😕"));
	    }
	    
	    /**
	 	 * check count of Sad Emoij in comments list
	 	 * @author Hussein 
	 	 */	    
	    @Test 
	    public void TestgetSadEmoijCount()
	    {
	    	int count=SentimentAPI.getSadEmoijCount(lst_comments);
	        Assert.assertEquals(count,19);
	    }
	    
	    /**
	 	 * check count of Happy Emoij in comments list
	 	 * @author Hussein 
	 	 */	    	    
	    @Test 
	    public void TestgetHappyEmoijCount()
	    {
	    	int count=SentimentAPI.getHappyEmoijCount(lst_comments);
	    	Assert.assertEquals(count,8);
	    }
	    
	    /**
	 	 *  test if comments list sentiment analysis is negative
	 	 * @author Hussein 
	 	 */	    	    
	    @Test 
	    public void TestgetCommentsSentiment()
	    {
	    	int sent=SentimentAPI.getCommentsSentiment(lst_comments);
	        Assert.assertEquals(sent,-1);
	    }
	    /**
	 	 * Reset test environment
	 	 * @author Hussein 
	 	 */	    	    
	    @After
		public void reset() {
	    	lst_comments=null;
		}

}
