package controllers;

import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

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
	public void testSentiment()
	{
		
		/*CompletionStage<WSResponse> stage = ws.url("/similarity/bad").get();
		WSResponse response;
		try {
			response = stage.toCompletableFuture().get();
			assertEquals(OK, response.getStatus());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	@Test
	public void renderTemplate()
	{
		/*Content html = views.html.similarity.render("Welcome to similarity");
		assertEquals("text/html", html.contentType());
		assertTrue(contentAsString(html).contains("Welcome to similarity"));*/
	}
}
