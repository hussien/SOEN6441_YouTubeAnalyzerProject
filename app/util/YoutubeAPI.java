package util;
import org.apache.commons.lang3.StringUtils;
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

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import models.SearchResult;
import models.SearchResultItem;

public class YoutubeAPI {

    // You need to set this value for your code to compile.
    // For example: ... DEVELOPER_KEY = "YOUR ACTUAL KEY";
    private static final String GOOGLE_API_KEY = "AIzaSyA_YYcVesTWnka5ZaF3imIHZoFhX0dBi_Q";

    private static final String APP_NAME = "youtube_search";
    private static final JsonFactory JSON_FACT = JacksonFactory.getDefaultInstance();

    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     */
    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport,JSON_FACT, null)
                .setApplicationName(APP_NAME)
                .build();
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
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
            e.printStackTrace();
        }

        return null;
    }
    
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

    public static List<SearchResultItem> getSearchResult(String term/*, String duration*/) {
        SearchListResponse resp = YoutubeAPI.search(term, ""/*, duration*/);

        if (resp != null && resp.getItems() != null && resp.getItems().size() > 0) {

            List<SearchResultItem> items = new ArrayList<>();
            for (com.google.api.services.youtube.model.SearchResult sr : resp.getItems()) {
                SearchResultItem sri = new SearchResultItem();
                sri.setVideoId(sr.getId().getVideoId());
                sri.setTitle(sr.getSnippet().getTitle());                
                sri.setChannelTitle(sr.getSnippet().getChannelTitle());
                sri.setDescription(sr.getSnippet().getDescription());
                sri.setChannelId(sr.getSnippet().getChannelId());
                sri.setComments(YoutubeAPI.getVideoComments(sr.getId().getVideoId(), 10));
                //sri.setDuration(duration);
                sri.setDuration("long");
                int occurance = StringUtils.countMatches(sri.getTitle().toLowerCase(), term.toLowerCase());
                sri.setSimilarity(occurance);
                items.add(sri);
            }

            return items;
        }

        return null;
    }

}
