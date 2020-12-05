package com.actors;

import com.actors.SearchHistory.UserHistory;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class ChildActor extends AbstractActor
{

	public static class Command
	{

		public String termName;

		public Command(String termName)
		{
			this.termName = termName;
		}

		public String getTermName()
		{
			return termName;
		}

		public void setTermName(String termName)
		{
			this.termName = termName;
		}
	}

	private long messages = 0L;

	public static Props getProps()
	{
		return Props.create(ChildActor.class);
	}

	@Override
	public Receive createReceive()
	{
		return receiveBuilder()
			.match(
				UserHistory.class,
				user -> {
					sender().tell(UserHistory.class, self());
				})
			.build();
	}

}
