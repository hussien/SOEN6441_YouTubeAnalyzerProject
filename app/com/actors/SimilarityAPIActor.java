package com.actors;

import org.apache.commons.lang3.StringUtils;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

/**
 * Similarity Actor 
 * @author Marwah
 */
public class SimilarityAPIActor extends AbstractBehavior<SimilarityAPIActor.Similarity>
{

	public static final class Similarity
	{

		public final String src;
		public final String target;
		public Integer count;
		
		public Integer getCount()
		{
			return count;
		}

		
		public void setCount(Integer count)
		{
			this.count = count;
		}

		public final ActorRef<SimilarityAPIActor.Similarity> replyTo;

		public Similarity(String src, String target, ActorRef<SimilarityAPIActor.Similarity> replyTo)
		{
			this.src = src;
			this.target = target;
			this.replyTo = replyTo;
		}
	}

	public static Behavior<SimilarityAPIActor.Similarity> create()
	{
		return Behaviors.setup((ctx) -> new SimilarityAPIActor(ctx));
	}

	public SimilarityAPIActor(ActorContext context)
	{
		super(context);
	}

	@Override
	public Receive<SimilarityAPIActor.Similarity> createReceive()
	{
		return newReceiveBuilder().onMessage(Similarity.class, this::measureSimilarity).build();
	}

	private Behavior<Similarity> measureSimilarity(Similarity message)
	{
		int count = StringUtils.countMatches(message.src.toLowerCase(), message.target.toLowerCase());
		message.setCount(count);
		if(message.replyTo!=null)
			message.replyTo.tell(message);
		return this;
	}

}
