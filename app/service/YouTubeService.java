package service;

import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;

import models.SearchResultItem;
import util.YoutubeAPI;

public class YouTubeService
{

	@Inject
	YoutubeAPI api;

	public List<SearchResultItem> getSearchResult(String term)
	{
		if (term == null)
			return null;

		List<SearchResultItem> searchResult = api.getSearchResult(term);

		if (searchResult != null)
			searchResult.stream().distinct().collect(Collectors.toList());

		return searchResult;
	}

}
