package service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

import models.SearchResultItem;

public class YouTubeServiceTest
{

	@Inject
	YouTubeService service;

	private static String SEARCH_TERM = "youtube";

	@Before
	public void init()
	{
		this.service = new YouTubeService();
	}

	@Test
	public void getSearchResults()
	{
		List<SearchResultItem> searchResults = this.service.getSearchResult(SEARCH_TERM);
		Assert.assertNotNull(searchResults);
		Assert.assertEquals(searchResults.size(), 10);
	}

	@After
	public void end()
	{
		service = null;
	}
}
