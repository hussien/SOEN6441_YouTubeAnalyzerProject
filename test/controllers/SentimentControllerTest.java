package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.contentAsString;

import org.junit.Test;

import com.google.inject.Inject;

import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import play.twirl.api.Content;

public class SentimentControllerTest extends WithApplication
{

	private WSClient ws;

	@Inject
	Http.Request request;

	@Test
	public void testSentiment()
	{
		Http.RequestBuilder request = new Http.RequestBuilder()
			.method(GET)
			.uri("/sentiment/term");

		Result result = new SimilarityController().sentiment("", request);
		assertEquals(OK, result.status());
		assertEquals("text/html", result.contentType().get());
		assertEquals("utf-8", result.charset().get());
		assertTrue(contentAsString(result).contains("Welcome"));
	}

	@Test
	public void renderTemplate()
	{
		Content html = views.html.similarity.render("Welcome to Sentiment");
		assertEquals("text/html", html.contentType());
		assertTrue(contentAsString(html).contains("Welcome to Sentiment"));
	}
}
