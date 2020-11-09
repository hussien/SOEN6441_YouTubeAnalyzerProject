package models;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.fluentlenium.core.events.GetScreenshotAsListener;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSearchResultItem {
	private SearchResultItem sri;
	@Before
	public void init()
	{
		sri=new SearchResultItem();
	}
    @Test
	public void TestgetViewsCount() {
    	Assert.assertNotNull(sri);
    	sri.setViewsCount("10");
    	Assert.assertEquals(sri.getViewsCount(),"10");	
	}
	
	@Test
	public void TestsetViewsCount() {
		Assert.assertNotNull(sri);
    	sri.setViewsCount("10");
    	Assert.assertEquals(sri.getViewsCount(),"10");	
	}

	@Test
	public void TestgetPublishDate() {
		Assert.assertNotNull(sri);
    	sri.setPublishDate("3/3/2020");
    	Assert.assertEquals(sri.getPublishDate(),"3/3/2020");	
	}
	
	@Test
	public void TestsetPublishDate() {
		Assert.assertNotNull(sri);
    	sri.setPublishDate("3/3/2020");
    	Assert.assertEquals(sri.getPublishDate(),"3/3/2020");	
	}
	
    @Test
	public void TestgetEmoijIcons() {
    	Assert.assertNotNull(sri);
    	sri.getEmoijIcons();
	}

	@Test
	public void TestsetEmoijIcons() {
		Assert.assertNotNull(sri);
		sri.setEmoijIcons("");
	}
	
    @Test
	public void TestgetChannelId() {
    	Assert.assertNotNull(sri);
    	sri.getChannelId();
    }
    

    @Test
	public void TestsetChannelId() {
    	Assert.assertNotNull(sri);
    	sri.setChannelId("");
    }
	
    @Test
	public void TestgetChannelTitle() {
    	Assert.assertNotNull(sri);
    	sri.getChannelId();
    }
    
    
    @Test
	public void TestsetChannelTitle() {
    	Assert.assertNotNull(sri);
    	sri.setChannelId("");
    }

    
    @Test
	public void TestgetTitle() {
    	Assert.assertNotNull(sri);
    	sri.getTitle();
    }
    
    @Test
	public void TestsetTitle() {
    	Assert.assertNotNull(sri);
    	sri.setTitle("");
    }
    
    @Test
	public void TestgetDescription() {
    	Assert.assertNotNull(sri);
    	sri.getDescription();
    }
    
    @Test
	public void TestsetDescription() {
    	Assert.assertNotNull(sri);
    	sri.setDescription("");
    }
    
    @Test
	public void TestgetDuration() {
    	Assert.assertNotNull(sri);
    	sri.getDuration();
    }
    
    @Test
	public void TestsetDuration() {
    	Assert.assertNotNull(sri);
    	sri.setDuration("");
    }

    
    @Test
	public void TestgetSimilarity() {
    	Assert.assertNotNull(sri);
    	sri.getSimilarity();
    }
    
    @Test
	public void TestsetSimilarity() {
    	Assert.assertNotNull(sri);
    	sri.setSimilarity(0);
    }
    
	@Test
	public void TestgetOwner() {
		Assert.assertNotNull(sri);
		sri.getOwner();
	}
	
	@Test
	public void TestsetOwner() {
		Assert.assertNotNull(sri);
		sri.setOwner("");
	}
		
	@Test
	public void TestgetSentiment() {
		Assert.assertNotNull(sri);
		sri.getSentiment();
	}
		
	@Test
	public void TestsetSentiment() {
		Assert.assertNotNull(sri);
		sri.setSentiment(0);
	}
	
	@Test
	public void TestgetVideoId() {
		Assert.assertNotNull(sri);
		sri.getVideoId();
	}
		
	@Test
	public void TestsetVideoId() {
		Assert.assertNotNull(sri);
		sri.setVideoId("");
	}

	@Test
	public void TestgetComments() {
		Assert.assertNotNull(sri);
		Assert.assertEquals(sri.getComments().size(),0);
	}
	
	@Test
	public void TestsetComments () {
		sri.setComments(new ArrayList<String>());
		Assert.assertEquals(sri.getComments().size(),0);
	}
	@After
	public void reset()
	{
		sri=null;
	}
}
