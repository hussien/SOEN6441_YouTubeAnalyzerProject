package controllers;

import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import models.TitleAndCount;

import org.hamcrest.collection.ArrayAsIterableMatcher;
import org.junit.Test;

import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.ws.*;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import com.google.inject.Inject;

import static play.test.Helpers.contentAsString;
import play.twirl.api.Content;

public class SimilarityControllerTest extends WithApplication
{

	private WSClient ws;

	@Inject
	Http.Request request;
	@Test
	public void testSimilarity()
	{
		try {
		CompletionStage<WSResponse> stage = ws.url("/similarity/bad").get();
		WSResponse response;		
			response = stage.toCompletableFuture().get();
			assertEquals(OK, response.getStatus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}

	@Test
	public void renderTemplate()
	{
		List<TitleAndCount> lst=new ArrayList<TitleAndCount>();
		lst.add(new TitleAndCount("Welcome to similarity","1"));
	}
}
