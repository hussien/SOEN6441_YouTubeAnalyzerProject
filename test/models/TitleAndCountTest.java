package models;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TitleAndCountTest
{

	TitleAndCount titleAndCount;

	@Before
	public void init()
	{
		titleAndCount = new TitleAndCount();
		titleAndCount.setCount("Some Count");
		titleAndCount.setTitle("Video Title");
	}

	@Test
	public void TestCount()
	{
		Assert.assertEquals("Some Count", titleAndCount.getCount());
	}

	@Test
	public void TestTitle()
	{
		Assert.assertEquals("Video Title", titleAndCount.getTitle());
	}

	@After
	public void End()
	{
		titleAndCount = null;
	}
}
