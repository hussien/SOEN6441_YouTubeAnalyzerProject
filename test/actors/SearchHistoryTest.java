package actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.actors.SearchHistory;
import com.google.inject.Inject;

import models.SearchResult;

public class SearchHistoryTest
{

	@Inject
	SearchHistory history;

	@Before
	public void init()
	{
		history = new SearchHistory();
	}

	@Test
	void getSearchResul()
	{
		Map<String, List<SearchResult>> map_searchResults = new HashMap();
		List<SearchResult> search = new ArrayList<SearchResult>();
		SearchResult sr = new SearchResult();
		sr.setTerm("Term");

		search.add(sr);

		map_searchResults.put("User1", search);
		Assert.assertNotNull(map_searchResults);
		Assert.assertEquals(map_searchResults.size(), 1);
	}

	@After
	void end()
	{

	}
}
