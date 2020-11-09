package util;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import models.SearchResult;
import models.SearchResultItem;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.VideoListResponse;

import util.YoutubeAPI;

/**
 * this class representes a test class for youtube data api 
 * service like search,list and get comments
 * @author Marwah
 */
public class YoutubeAPITest {
	/**
	 * private final string that represents youtube search keywords
	 */
    private static  String SEARCH_TERM = "youtube";
    
    @Before
	public void init() {
    	SEARCH_TERM = "youtube";
	}

    /**
     * this method test youtube data search api using search keyword
     * and compares number of returned query results  and test if 
     * it is null or of size 0
     */
    @Test
    public void testGetSearchResult() {
        List<SearchResultItem> searchResults = YoutubeAPI.getSearchResult(SEARCH_TERM);
        Assert.assertNotNull(searchResults);
        Assert.assertEquals(searchResults.size(),0);
    }
    @Test
    public void testgetService(){
    	YouTube youTube=null;
		try {
			youTube = YoutubeAPI.getService();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Assert.assertNotNull(youTube);
    }
    @Test
    public void testgetVideoComments(){
    	List<String> lst=YoutubeAPI.getVideoComments("vLnPwxZdW4Y", 10);
        Assert.assertNotNull(lst);
        Assert.assertEquals(lst.size(),10);
    }
    
    @Test
    public void testgetVideoInfo(){
    	VideoListResponse video=YoutubeAPI.getVideoInfo("vLnPwxZdW4Y");
        Assert.assertNotNull(video);
        Assert.assertEquals(video.getItems().size(),1);
    }
    @Test
    public void testYoutubeSearch(){
    	SearchListResponse result=YoutubeAPI.search("cat","");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getItems().size(),10);
    }
    
    @After
	public void reset() {
    	SEARCH_TERM = null;
	}
}
