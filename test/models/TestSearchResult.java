package models;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSearchResult
{

	SearchResult sr;

	@Before
	public void init()
	{
		sr = new SearchResult();
		sr.getItems().add(new SearchResultItem());
	}

	@Test
	public void TestgetItems()
	{
		Assert.assertNotNull(sr);
		Assert.assertEquals(sr.getItems().size(), 1);
	}

	@Test
	public void TestsetItems()
	{
		Assert.assertNotNull(sr);
		sr.setItems(null);
		Assert.assertEquals(sr.getItems(), null);
	}

	@Test
	public void TestgetTerm()
	{
		Assert.assertNotNull(sr);
		sr.setTerm("word");
		Assert.assertEquals(sr.getTerm(), "word");
	}

	@Test
	public void TestsetTerm()
	{
		Assert.assertNotNull(sr);
		sr.setTerm("word");
		Assert.assertEquals(sr.getTerm(), "word");
	}

	@After
	public void reset()
	{
		sr = null;
	}

}
