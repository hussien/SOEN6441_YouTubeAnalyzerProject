package util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.actors.SimilarityAPIActor;
import com.actors.SimilarityAPIActor.Similarity;
import com.actors.SentimentAPIActor;
import com.actors.SentimentAPIActor.Sentiment;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.CommentSnippet;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.VideoListResponse;

import akka.actor.typed.javadsl.Adapter;
import akka.actor.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.Scheduler;
import akka.actor.typed.javadsl.AskPattern;
import models.SearchResultItem;
import akka.actor.typed.ActorSystem;
import akka.Done;
import akka.NotUsed;
import akka.actor.*;
import akka.stream.javadsl.Flow;

import com.actors.ChannelResponse;
/**
 * this class represents youtube Data Api3 client that
 * can request youtube services like search,video list, etc..
 * 
 * @author Hussein
 */
public class YoutubeAPI
{
	/**
	 * represents google API Secret Key
	 */
	//private static final String GOOGLE_API_KEY = "AIzaSyA3DR9oHPzjleT1G9lhvnRG7HFyQuZ3rks";
	private static final String GOOGLE_API_KEY = "AIzaSyA_Iflgh0hAseVsxmvuqujPI4QTRj8eyt4";
	
	/**
	 * represents youtube application name
	 */
	private static final String APP_NAME = "youtube_search";
	/**
	 * represents JSON parser for Api response
	 */
	private static final JsonFactory JSON_FACT = JacksonFactory.getDefaultInstance();

	/**
	 * Build and return an authorized API client service. *
	 * 
	 * @return an authorized API client service
	 * @throws GeneralSecurityException,
	 *             IOException
	 * @author Hussein
	 */
	public static YouTube getService() throws GeneralSecurityException, IOException
	{
		final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		return new YouTube.Builder(httpTransport, JSON_FACT, null)
			.setApplicationName(APP_NAME)
			.build();
	}

	/**
	 * call youtube api search service
	 * to get list of videos by specefiying search keywords
	 * 
	 * @param searchTerm
	 *            Search keywords
	 * @param nextPageToken
	 *            Page of search results
	 * @return youtube SearchListResponse
	 * @author Hussein
	 */

	public static SearchListResponse search(String searchTerm, String nextPageToken, Long results)
	{
		try
		{
			YouTube youtubeService = getService();
			// Define and execute the API request
			YouTube.Search.List request = youtubeService.search()
				.list("snippet");
			return request.setKey(GOOGLE_API_KEY)
				.setOrder("relevance")
				// .setPart("contentDetails,statistics")//for viewCount,duration
				.setQ(searchTerm)
				// .setVideoDuration(duration)
				.setPageToken(nextPageToken)
				.setMaxResults(results)
				.setType("video")
				.execute();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * get detailed youtube video information by video Id
	 * 
	 * @param VideoId
	 *            youtube Video Id
	 * @return youtube VideoListResponse
	 * @author Hussein
	 */
	public static VideoListResponse getVideoInfo(String VideoId)
	{
		try
		{
			YouTube youtubeService = getService();
			YouTube.Videos.List request = youtubeService.videos().list("snippet,statistics,contentDetails");
			return request.setKey(GOOGLE_API_KEY)
				.setId(VideoId).execute();
		}
		catch (Exception ex)
		{
			// ex.printStackTrace();
		}
		return null;
	}

	/**
	 * get list of youtube video comments
	 * 
	 * @param VID
	 *            youtube video Id
	 * @param count
	 *            number of comments to retrieve
	 * @return youtube CommentThreadListResponse
	 * @author Hussein
	 */
	public static CommentThreadListResponse comments(String VID, int count)
	{
		try
		{

			YouTube youtubeService = getService();

			// Define and execute the API request
			YouTube.CommentThreads.List request = youtubeService.commentThreads()
				.list("snippet");
			return request.setKey(GOOGLE_API_KEY)
				.setVideoId(VID)
				.setMaxResults((long)count)
				.execute();
		}
		catch (Exception e)
		{
			// e.printStackTrace();
		}

		return null;
	}

	/**
	 * get youtube video list of comments
	 * 
	 * @param VID
	 *            youtube Videi Id
	 * @param count
	 *            number of comment to retrieve
	 * @return list of string represents comments
	 * @author Hussein
	 */
	public static List<String> getVideoComments(String VID, int count)
	{

		CommentThreadListResponse res = YoutubeAPI.comments(VID, count);
		if (res != null)
		{
			List<CommentThread> videoComments = res.getItems();
			// System.out.println(res.getItems());
			List<String> lst = new ArrayList<String>();
			if (videoComments.size() > 0)
			{
				for (CommentThread videoComment : videoComments)
				{
					CommentSnippet snippet = videoComment.getSnippet().getTopLevelComment().getSnippet();
					lst.add(snippet.getTextDisplay());
				}
				System.out.println("comment Count=" + videoComments.size());
				return lst;
			}
		}
		return new ArrayList<String>();
	}

	/**
	 * search for latest 10 youtube videos using keywords
	 * and fill result into SearchResultItem
	 * that represents a data model for youtube search response record
	 * 
	 * @param term
	 *            youtube search keyword
	 * @return list of youtube SearchResultItem data model
	 * @author Hussein
	 */
	public static List<SearchResultItem> getSearchResult(String term)
	{
		return getSearchResult(term,10L);
	}
	/**
	 * search for youtube videos using keywords
	 * and fill result into SearchResultItem
	 * that represents a data model for youtube search response record
	 * 
	 * @param term
	 *            youtube search keyword
	 *@param Count
	 *            number of videos to return;
	 * @return list of youtube SearchResultItem data model
	 * @author Hussein
	 */
	public static List<SearchResultItem> getSearchResult(String term,Long Count)
	{
		SearchListResponse resp = YoutubeAPI.search(term, "", Count);

		if (resp != null && resp.getItems() != null && resp.getItems().size() > 0)
		{

			List<SearchResultItem> items = new ArrayList<>();
			resp.getItems().stream()
				.forEach(sr -> {
					SearchResultItem sri = new SearchResultItem();
					// sri.setPublishDate(new SimpleDateFormat("MM-dd-yyyy").format(sr.getSnippet().getPublishedAt()));
					sri.setVideoId(sr.getId().getVideoId());
					sri.setTitle(sr.getSnippet().getTitle());
					sri.setChannelTitle(sr.getSnippet().getChannelTitle());
					sri.setDescription(sr.getSnippet().getDescription());
					sri.setChannelId(sr.getSnippet().getChannelId());
					/////////////////////////////
					CompletableFuture.supplyAsync(() -> YoutubeAPI.getVideoInfo(sr.getId().getVideoId()))
						.thenApply(x -> {
							x.getItems().stream()
								.forEach(v -> {
									sri.setViewsCount(v.getStatistics().getViewCount().toString());
									sri.setDuration(v.getContentDetails().getDuration());
								});
							return x;
						});
					////////////////////////////
					/*VideoListResponse  vlr=YoutubeAPI.getVideoInfo(sr.getId().getVideoId());
					List<Video> video=vlr.getItems();
					if(video!=null)
					{
						//System.out.println("video info length="+video.size());
					    sri.setViewsCount(video.get(0).getStatistics().getViewCount().toString());
					    sri.setDuration(video.get(0).getContentDetails().getDuration());
					}*/
					/////////////////////////////
					CompletableFuture.supplyAsync(() -> YoutubeAPI.getVideoComments(sr.getId().getVideoId(), 100))
						.thenApply(x -> {
							sri.setEmoijIcons(SentimentAPI.getHappyEmoij(x) + "\r\n" + SentimentAPI.getSadEmoij(x));
							int res=SentimentAPI.getCommentsSentiment(x);
							System.out.println("CommentsSentiment="+res);
							sri.setSentiment(SentimentAPI.getCommentsSentiment(x));
							
//							ActorSystem<SentimentAPIActor.Sentiment> SentActor = ActorSystem.create(SentimentAPIActor.create(), "SimilarityAPIActor");
//							//ActorSystem<SentimentAPIActor.Sentiment> SentReplyActor = ActorSystem.create(SentimentAPIActor.create(), "SentReplyActor ");
//							Sentiment mysent=new Sentiment(x,null);
//							SentActor.tell(mysent);
//							System.out.println("mysent object class="+mysent.getSentiment_class());							
//							sri.setSentiment(mysent.getSentiment_class());							
							return x;
						});
					////////////////////////////
					/*List<String> lst_comments=YoutubeAPI.getVideoComments(sr.getId().getVideoId(), 100);
					//sri.setComments(lst_comments);
					sri.setEmoijIcons(SentimentAPI.getHappyEmoij(lst_comments)+"\r\n"+SentimentAPI.getSadEmoij(lst_comments));
					sri.setSentiment(SentimentAPI.getCommentsSentiment(lst_comments));
					//sri.setDuration(duration);
					*/

					// int occurance = StringUtils.countMatches(sri.getTitle().toLowerCase(), term.toLowerCase());

					ActorSystem<SimilarityAPIActor.Similarity> similarityActor = ActorSystem.create(SimilarityAPIActor.create(), "SimilarityAPIActor");
					//ActorSystem<SimilarityAPIActor.Similarity> similarityReplyActor = ActorSystem.create(SimilarityAPIActor.create(), "similarityReplyActor");
					similarityActor.tell(new Similarity(sri.getTitle(), term, null));

					int occurance = SimilarityAPI.measureSimilarity(sri.getTitle(), term);
					sri.setSimilarity(occurance);
					items.add(sri);

				});
			// for (com.google.api.services.youtube.model.SearchResult sr : resp.getItems()) {}
			return items;
		}

		return null;
	}
	
	/**
	 * search for youtube channel search
	 * @author Jagan
	 */
	public static ChannelListResponse searchChannel(String channelID){
		try{
			YouTube youtubeService = getService();

			YouTube.Channels.List request = youtubeService.channels()
					.list("id,snippet,statistics");

			return request.setKey(GOOGLE_API_KEY)
					.setId(channelID)
					.setFields("items(snippet/description, statistics/viewCount)")
					.execute();
		}
		catch (Exception e)
		{
			// e.printStackTrace();
		}
		return null;
	}
	/**
	 * search for youtube channel
	 * @author Jagan
	 */
	public static ChannelResponse getChannelResult(String channelID)
	{
		ChannelListResponse resp = YoutubeAPI.searchChannel(channelID);

		if(resp.getItems() != null){
			String title = resp.getItems().get(0).getSnippet().getTitle();
			String des = resp.getItems().get(0).getSnippet().getDescription();
			String country = resp.getItems().get(0).getSnippet().getCountry();
			String viewCount  = resp.getItems().get(0).getStatistics().getViewCount().toString();
			String subscriberCount  = resp.getItems().get(0).getStatistics().getSubscriberCount() == null ? "0" :
					resp.getItems().get(0).getStatistics().getSubscriberCount().toString();

			return new ChannelResponse(title, des, country, viewCount, subscriberCount);

		}
		return null;

	}



}
