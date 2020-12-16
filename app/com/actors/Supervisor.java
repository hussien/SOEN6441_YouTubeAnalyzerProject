package com.actors;

import java.time.Duration;

import com.actors.SearchHistory.UserHistory;
import com.google.inject.Inject;

import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import akka.japi.pf.ReceiveBuilder;
import service.YouTubeService;

import static akka.actor.SupervisorStrategy.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Hussein
 */
public class Supervisor extends AbstractActor
{

	@Inject
	YouTubeService service;

	final ActorRef child = getContext().actorOf(ChildActor.getProps(), "child");

	public static Props getProps()
	{
		return Props.create(Supervisor.class);
	}

	private ActorRef out;

	public Supervisor(ActorRef out)
	{
		this.out = out;
	}

	public Supervisor()
	{
	}

	public static Props getProps1(ActorRef out)
	{
		return Props.create(Supervisor.class, out);
	}

	@Override
	public Receive createReceive()
	{
		return receiveBuilder()
			.match(UserHistory.class, message -> {
				out.tell("I received your message: " + message.getSearchResult(), self());
			})
			.build();
	}

	/**
	 * OneForOneStrategy SupervisorStrategy
	 * @author Hussein 
	 */
	@Override
	public SupervisorStrategy supervisorStrategy()
	{
		return new OneForOneStrategy(
			10,
			Duration.ofDays(1),
			DeciderBuilder
				.match(RuntimeException.class, ex -> stop())
				.build());
	}

}
