package util;
import org.apache.commons.lang3.StringUtils;

import sun.net.www.content.audio.x_aiff;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentListResponse;
import com.google.api.services.youtube.model.CommentSnippet;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import models.SearchResult;
import models.SearchResultItem;

/**
 * this class represents youtube Data Api3 client that 
 * can request youtube services like search,video list, etc..
 * @author Hussein
 */
public class YoutubeAPI {

    /**
     * represents google API Secret Key 
     */
    private static final String GOOGLE_API_KEY = "AIzaSyA_YYcVesTWnka5ZaF3imIHZoFhX0dBi_Q";
    /**
     * represents youtube application name 
     */
    private static final String APP_NAME = "youtube_search";
    /**
     * represents JSON parser for Api response
     */
    private static final JsonFactory JSON_FACT = JacksonFactory.getDefaultInstance();

    /**
     * Build and return an authorized API client service.     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     * @author Hussein
     */
    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport,JSON_FACT, null)
                .setApplicationName(APP_NAME)
                .build();
    }

    /**
     * call youtube api search service 
     * to get list of videos by specefiying search keywords
     * @param searchTerm Search keywords
     * @param nextPageToken Page of search results
     * @return youtube SearchListResponse
     * @author Hussein
     */

    public static SearchListResponse search(String searchTerm, String nextPageToken/*, String duration*/)
    {
        try {
            YouTube youtubeService = getService();
            // Define and execute the API request
            YouTube.Search.List request = youtubeService.search()
                    .list("snippet");
            return request.setKey(GOOGLE_API_KEY)
                    .setOrder("relevance")
//                    .setPart("contentDetails,statistics")//for viewCount,duration
                    .setQ(searchTerm)
                    //.setVideoDuration(duration)
                    .setPageToken(nextPageToken)
                    .setMaxResults((long)10)
                    .setType("video")
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    /**
     * get detailed youtube video information by video Id
     * @param VideoId youtube Video Id
     * @return youtube VideoListResponse  
     * @author Hussein 
     */
    public static VideoListResponse  getVideoInfo(String VideoId) 
    {
    	try {
		    YouTube youtubeService = getService();
		    YouTube.Videos.List request = youtubeService.videos().list("snippet,statistics,contentDetails");
		     return request.setKey(GOOGLE_API_KEY)
		    		 		.setId(VideoId).execute();
    	}
    	catch(Exception ex)
    	{
    		//ex.printStackTrace();
    	}
    	return null;
	}
    
    /**
     * get list of youtube video comments
     * @param VID youtube video Id
     * @param count number of comments to retrieve
     * @return youtube CommentThreadListResponse 
     * @author Hussein
     */
    public static CommentThreadListResponse comments(String VID,int count) 
    {
        try {
        	
            YouTube youtubeService = getService();
            
            // Define and execute the API request
            YouTube.CommentThreads.List request = youtubeService.commentThreads()
                    .list("snippet");
            return request.setKey(GOOGLE_API_KEY)
                    .setVideoId(VID)
                    .setMaxResults((long)count)
                    .execute();
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return null;
    }
    
    /**
     * get youtube video list of comments
     * @param VID youtube Videi Id
     * @param count number of comment to retrieve
     * @return list of string represents comments
     * @author Hussein
     */
    public static List<String> getVideoComments(String VID,int count)
    {
    	
    	CommentThreadListResponse res=YoutubeAPI.comments(VID,count);
    	if(res!=null)
    	{
	        List<CommentThread> videoComments = res.getItems();
	        //System.out.println(res.getItems());
	        List<String> lst=new ArrayList<String>();
	        if(videoComments.size()>0)
	        {
	        	for (CommentThread videoComment : videoComments) {
	        		CommentSnippet snippet =videoComment.getSnippet().getTopLevelComment().getSnippet();
	        		lst.add(snippet.getTextDisplay());
	            }
	        	System.out.println("comment Count="+videoComments.size());
	        	return lst;
	        }
    	}
    	return new ArrayList<String>();
    }
    
    /**
     * search for youtube videos using keywords 
     * and fill result into SearchResultItem 
     * that represents a data model for youtube search response record
     * @param term youtube search keyword
     * @return list of youtube SearchResultItem data model
     * @author Hussein 
     */
    
    public static List<SearchResultItem> getSearchResult(String term/*, String duration*/) {
        SearchListResponse resp = YoutubeAPI.search(term, ""/*, duration*/);
        
        if (resp != null && resp.getItems() != null && resp.getItems().size() > 0) {

            List<SearchResultItem> items = new ArrayList<>();
            resp.getItems().stream()
            .forEach(sr->
            {
            	SearchResultItem sri = new SearchResultItem();                
                //sri.setPublishDate(new SimpleDateFormat("MM-dd-yyyy").format(sr.getSnippet().getPublishedAt()));
                sri.setVideoId(sr.getId().getVideoId());
                sri.setTitle(sr.getSnippet().getTitle());                
                sri.setChannelTitle(sr.getSnippet().getChannelTitle());
                sri.setDescription(sr.getSnippet().getDescription());
                sri.setChannelId(sr.getSnippet().getChannelId());
                /////////////////////////////
                CompletableFuture.supplyAsync(()->YoutubeAPI.getVideoInfo(sr.getId().getVideoId()))
                .thenApply(x->
                		{x.getItems().stream()
                		.forEach(v->{
                			sri.setViewsCount(v.getStatistics().getViewCount().toString());
        	                sri.setDuration(v.getContentDetails().getDuration());
                		}
                		); return x;}
                	);
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
                CompletableFuture.supplyAsync(()->YoutubeAPI.getVideoComments(sr.getId().getVideoId(), 100))
                .thenApply(x->
                		{
                			sri.setEmoijIcons(SentimentAPI.getHappyEmoij(x)+"\r\n"+SentimentAPI.getSadEmoij(x));
                            sri.setSentiment(SentimentAPI.getCommentsSentiment(x));
                            return x;
                		 }
                	);
                ////////////////////////////                
                /*List<String> lst_comments=YoutubeAPI.getVideoComments(sr.getId().getVideoId(), 100);
                //sri.setComments(lst_comments);
                sri.setEmoijIcons(SentimentAPI.getHappyEmoij(lst_comments)+"\r\n"+SentimentAPI.getSadEmoij(lst_comments));
                sri.setSentiment(SentimentAPI.getCommentsSentiment(lst_comments));
                //sri.setDuration(duration);
                */
                
                //int occurance = StringUtils.countMatches(sri.getTitle().toLowerCase(), term.toLowerCase());
                int occurance = SimilarityAPI.measureSimilarity(sri.getTitle(), term);
                sri.setSimilarity(occurance);
                items.add(sri);

            }
            );
            //for (com.google.api.services.youtube.model.SearchResult sr : resp.getItems()) {}
            return items;
        }

        return null;
    }
    
}
