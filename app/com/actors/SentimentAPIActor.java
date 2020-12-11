package com.actors;

import java.util.Arrays;
import java.util.List;

import util.SentimentAPI;
import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
/**
 * Sentiment Actor 
 * @author Hussein
 */
public class SentimentAPIActor extends AbstractBehavior<SentimentAPIActor.Sentiment>
{
	/**
	 * Sentiment Message 
	 * @author Hussein
	 */
	public static final class Sentiment
	{

		public final List<String> lst_comments;
		public Integer Sentiment_class;

		public Integer getSentiment_class() {
			return Sentiment_class;
		}

		public void setSentiment_class(Integer sentiment_class) {
			Sentiment_class = sentiment_class;
		}

		public final ActorRef<SentimentAPIActor.Sentiment> replyTo;
		
		
		public Sentiment(List<String> lst_comments, ActorRef<SentimentAPIActor.Sentiment> replyTo)
		{
			this.lst_comments= lst_comments;
			this.replyTo = replyTo;
		}
	}
	
	public static Props getProps()
	{
		return Props.create(SentimentAPIActor.class);
	}
	
	/**
	 * Sentiment Actor create 
	 * @author Hussein
	 */
	public static Behavior<SentimentAPIActor.Sentiment> create()
	{
		return Behaviors.setup((ctx) -> new SentimentAPIActor(ctx));
	}

	public SentimentAPIActor(ActorContext context)
	{
		super(context);
	}
	/*@Override
	public Receive createReceive()
	{
		return receiveBuilder()
			.match(
				Sentiment.class,
				sent -> {
					int Sentiment_class=SentimentAPI.getCommentsSentiment(sent.lst_comments);
					sent.setSentiment_class(Sentiment_class);
					sender().tell(Sentiment_class, self());
				})
			.build();
	}*/

	
	
	/**
	 * Sentiment Actor receive 
	 * @author Hussein
	 */
	@Override
	public Receive<SentimentAPIActor.Sentiment> createReceive()
	{
		return newReceiveBuilder().onMessage(Sentiment.class, this::measureSentiment).build();
	}

	/**
	 * Sentiment Actor measure Sentiment class
	 * @author Hussein
	 */
	private Behavior<Sentiment> measureSentiment(Sentiment message)
	{
			int Sentiment_class=SentimentAPI.getCommentsSentiment(message.lst_comments);
			message.setSentiment_class(Sentiment_class);
			if(message.replyTo!=null)
				message.replyTo.tell(message);
		return this;
	}
}
