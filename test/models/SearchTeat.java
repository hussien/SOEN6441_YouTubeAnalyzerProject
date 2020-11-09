package models;

import java.util.Arrays;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SearchTeat {
	Search search;
	 @Before
	public void init() {
		 search=new Search();
		 search.setTerm("test");
	}
	 @Test
	 public void TestgetTerm()
	 {
		 Assert.assertEquals("test", search.getTerm());
	 }
	 @Test
	 public void TestsetTerm()
	 {
		 search.setTerm("new");
		 Assert.assertEquals("new", search.getTerm());
	 }	 
	 @After
	 public void End() {
		 search=null;
	}
	 

}
