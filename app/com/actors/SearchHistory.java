package com.actors;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.google.api.client.json.Json;
import com.google.inject.Inject;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.impl.model.parser.HeaderParser.Result;
import models.SearchResult;
import scala.compat.java8.FutureConverters;

public class SearchHistory
{

	public static HashMap<String, List<SearchResult>> map_searchResults = new HashMap<String, List<SearchResult>>();

	public static class UserHistory
	{
		
		String termName;

		
		public String getTermName()
		{
			return termName;
		}

		
		public void setTermName(String termName)
		{
			this.termName = termName;
		}

		String username;

		public List<SearchResult> searchResult;

		public UserHistory(String username, List<SearchResult> searchResult)
		{
			this.username = username;
			this.searchResult = searchResult;
		}

		public String getUsername()
		{
			return username;
		}

		public void setUsername(String username)
		{
			this.username = username;
		}

		public List<SearchResult> getSearchResult()
		{
			return searchResult;
		}

		public void setSearchResult(List<SearchResult> searchResult)
		{
			this.searchResult = searchResult;
		}
	}
}
