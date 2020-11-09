package models;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represents a data model for youtube 
 * search results such as video title,duration,viewcount etc..
 * @author Hussein
 */
public class SearchResultItem {
	/**
	 * represents video channel Id
	 */
    private String channelId;
    /**
     * represents video channel title
     */
    private String channelTitle;
    /**
     * represents video title
     */
    private String title;
    /**
     * represents video description
     */
    private String description;
    /**
     * represents video duration in HH:MM:SS
     */
    private String duration;
    /**
     * represents video owner name
     */
    private String owner;
    /**
     * represents video sentiment value(1 happy,-1 sad,0 natural)
     */
    private int sentiment;
    /**
     * represents youtube video Id
     */
    private String videoId;
    /**
     * represents video online publish data
     */
    private String publishDate;
    /**
     * represents youtube video view counts
     */
    private String viewsCount;
    /**
     * list of video owner other videos
     */
    private List<String> ownerVideos=new ArrayList<String>();
    /**
     * list of video comments
     */
    private List<String> comments=new ArrayList<String>();
    /**
     * list of video EmoijIcons
     */
    private String emoijIcons="";
    /**
     * represents similarity between video title and search keywords
     */
    private int similarity;
    
    /**
     * video views count getter
     * @return views count
     * @author Hussein
     */
    public String getViewsCount() {
		return viewsCount;
	}
    /**
     * video views count setter
     * @param viewsCount video views count
     * @author Hussein
     */
	public void setViewsCount(String viewsCount) {
		this.viewsCount = viewsCount;
	}
	
	/**
	 * video publish date setter
	 * @return publish date
	 * @author Hussein
	 */
	public String getPublishDate() {
		return publishDate;
	}
	
	/**
	 * video publish date setter
	 * @param publishDate video publish date
	 * @author Hussein
	 */
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	/**
	 * getter for video comments EmoijIcons list
	 * @return EmoijIcons list
	 * @author Hussein
	 */
    public String getEmoijIcons() {
		return emoijIcons;
	}

    /**
     * setter for video comments EmoijIcons list
     * @param emoijIcons string represents comments emoijIcons list
     * @author Hussein
     */
	public void setEmoijIcons(String emoijIcons) {
		this.emoijIcons = emoijIcons;
	}
	
	 /**
	  * getter for video channel Id
	  * @return video channel Id
	  * @author Hussein
	  */
    public String getChannelId() {
        return channelId;
    }
    /**
     * setter for video channel Id
     * @param channelId video Channel Id
     * @author Hussein
     */

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    /**
     * getter fro video channel title
     * @return video channel title
     * @author Hussein
     */
    public String getChannelTitle() {
        return channelTitle;
    }
    
    /**
     * setter for video channel title
     * @param channelTitle
     * @author Hussein
     */
    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    /**
     * getter for video title
     * @return video title
     * @author Hussein
     */
    public String getTitle() {
        return title;
    }
    /**
     * setter for video title
     * @param title
     * @author Hussein
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * getter for video description
     * @return video description
     * @author Hussein
     */
    public String getDescription() {
        return description;
    }
    /**
     * setter for video description
     * @param description
     * @author Hussein
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * getter for video duration
     * @return video length
     * @author Hussein
     */
    public String getDuration() {
        return duration;
    }
    /**
     * setter for video duration
     * @param duration video length
     * @author Hussein
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * getter for video title and search keyword similarity
     * @return similarity value
     * @author Hussein
     */
    public int getSimilarity() {
        return similarity;
    }
    /**
     * setter for video title and search keyword similarity
     * @param similarity 
     * @author Hussein
     */
    public void setSimilarity(int similarity) {
        this.similarity = similarity;
    }
    /**
     * getter for video owner name
     * @return owner name
     * @author Hussein
     */
	public String getOwner() {
		return owner;
	}
	/**
	 * setter for video owner name
	 * @param owner video owner name
	 * @author Hussein
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	/**
	 * getter for video sentiment value (1 happy,0 natural, -1 sad)
	 * @return sentiment value
	 * @author Hussein
	 */
	public int getSentiment() {
		return sentiment;
	}
	
	/**
	 * setter for video sentiment value (1 happy,0 natural, -1 sad)
	 * @param sentiment 
	 * @author Hussein
	 */
	public void setSentiment(int sentiment) {
		this.sentiment = sentiment;
	}
	
	/**
	 * getter for video Id
	 * @return Youtube Video Id
	 */
	public String getVideoId() {
		return videoId;
	}
	
	/**
	 * setter for video Id
	 * @param videoId 
	 * @author Hussein
	 */
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	/**
	 * getter for video list of comments
	 * @return list of comments
	 * @author Hussein
	 */
	public List<String> getComments() {
		return comments;
	}
	/**
	 * setter for video comments list
	 * @param comments list of comments
	 * @author Hussein
	 */
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
}
