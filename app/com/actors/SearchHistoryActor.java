package com.actors;

import com.actors.SearchHistory.UserHistory;
import com.google.inject.Singleton;

import akka.actor.AbstractActor;
import akka.actor.Props;

@Singleton
public class SearchHistoryActor extends AbstractActor
{

	public static Props getProps()
	{
		return Props.create(SearchHistoryActor.class);
	}

	@Override
	public Receive createReceive()
	{
		return receiveBuilder()
			.match(
				UserHistory.class,
				user -> {
					SearchHistory.map_searchResults.put(user.username, user.searchResult);
					sender().tell(SearchHistory.map_searchResults, self());
				})
			.build();
	}

}
