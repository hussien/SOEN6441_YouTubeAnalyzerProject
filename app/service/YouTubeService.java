package service;

import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.actors.ChannelResponse;

import models.SearchResultItem;
import util.YoutubeAPI;

/**
 * YouTube Service Injector 
 * @author Hussein
 */
public class YouTubeService
{

	@Inject
	YoutubeAPI api;
	public List<SearchResultItem> getSearchResult(String term)
	{
		return getSearchResult(term,10L);
	}
	public List<SearchResultItem> getSearchResult(String term,Long Count)
	{
		if (term == null)
			return null;
		
		List<SearchResultItem> searchResult = api.getSearchResult(term,Count);

		if (searchResult != null)
			searchResult=searchResult.stream().distinct().collect(Collectors.toList());

		return searchResult;
	}
	
	public ChannelResponse getChannelResult(String channelID)
	{
		return api.getChannelResult(channelID);
	}

}
