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
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import util.YoutubeAPI;

/**
 * this class representes a test class for youtube data api
 * service like search,list and get comments
 * 
 * @author Marwah
 */
public class YoutubeAPITest
{

	/**
	 * private final string that represents youtube search keywords
	 */
	private static String SEARCH_TERM = "youtube";

	@Before
	public void init()
	{
		SEARCH_TERM = "youtube";
	}

	/**
	 * this method test youtube data search api using search keyword
	 * and compares number of returned query results and test if
	 * it is null or of size 0
	 * 
	 * @author Marwah
	 */
	@Test
	public void testGetSearchResult()
	{
		List<SearchResultItem> searchResults = YoutubeAPI.getSearchResult(SEARCH_TERM);
		Assert.assertNotNull(searchResults);
		Assert.assertEquals(searchResults.size(), 10);
	}

	/**
	 * this method test getting youtube service instance
	 * 
	 * @author Marwah
	 */
	@Test
	public void testgetService()
	{
		YouTube youTube = null;
		try
		{
			youTube = YoutubeAPI.getService();
		}
		catch (GeneralSecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNotNull(youTube);
	}

	/**
	 * this method test get youtube video comments
	 * 
	 * @author Marwah
	 */
	@Test
	public void testgetVideoComments()
	{
		List<String> lst = YoutubeAPI.getVideoComments("vLnPwxZdW4Y", 10);
		Assert.assertNotNull(lst);
		Assert.assertEquals(lst.size(), 10);
	}

	/**
	 * this method test getting youtube video detailed Info
	 * 
	 * @author Marwah
	 */
	@Test
	public void testgetVideoInfo()
	{
		VideoListResponse video = YoutubeAPI.getVideoInfo("vLnPwxZdW4Y");
		Assert.assertNotNull(video);
		List<Video> lst = video.getItems();
		Assert.assertNotNull(lst);
		Assert.assertEquals(lst.size(), 1);
		System.out.println(lst.get(0).getContentDetails().getDuration());
		Assert.assertEquals(video.getItems().size(), 1);
		Assert.assertEquals(lst.get(0).getContentDetails().getDuration(), "PT4H1M19S");
	}

	/**
	 * this method test searching youtube by keyword
	 * 
	 * @author Marwah
	 */
	@Test
	public void testYoutubeSearch()
	{
		SearchListResponse result = YoutubeAPI.search("cat", "");
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getItems().size(), 10);
		Assert.assertEquals(result.getItems().get(0).getSnippet().getTitle(),
			"Baby Cats - Cute and Funny Cat Videos Compilation #34 | Aww Animals");
	}

	/**
	 * reset test environment
	 * 
	 * @author Marwah
	 */
	@After
	public void reset()
	{
		SEARCH_TERM = null;
	}
}
