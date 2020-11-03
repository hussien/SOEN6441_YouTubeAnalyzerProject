package util;

import java.util.List;

import models.SearchResult;
import models.SearchResultItem;

import org.junit.Assert;
import org.junit.Test;

import util.YoutubeAPI;

/*
 * @author Marwah
 */
public class YoutubeAPITest {

    private static final String SEARCH_TERM = "youtube";

    @Test
    public void testGetSearchResult() {
        List<SearchResultItem> searchResults = YoutubeAPI.getSearchResult(SEARCH_TERM);
        Assert.assertNotNull(searchResults);
        Assert.assertFalse(searchResults.size()==0);
    }
}
