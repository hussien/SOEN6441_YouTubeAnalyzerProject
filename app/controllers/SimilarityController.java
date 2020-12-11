package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

import javax.inject.Inject;

import com.google.api.services.youtube.model.SearchListResponse;

import akka.actor.ActorSystem;
import akka.stream.Materializer;
import models.Search;
import models.SearchResult;
import models.SearchResultItem;
import models.TitleAndCount;
import play.api.i18n.MessagesApi;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.YouTubeService;
import util.YoutubeAPI;

public class SimilarityController extends Controller
{

	private final Form<Search> form;
	private static HashMap<String, List<SearchResult>> map_searchResults = new HashMap<>();
	private static List<String> lst_UsersSessionsIds = new ArrayList<>();
	private List<SearchResult> searchResults;
	private MessagesApi messagesApi;

	YouTubeService youTubeService;
	private final ActorSystem actorSystem;
	private final Materializer materializer;

	@Inject
	private HttpExecutionContext httpExecutionContext;

	@Inject
	YoutubeAPI api;

	//
	@Inject
	public SimilarityController(FormFactory formFactory, MessagesApi messagesApi,
		YouTubeService youTubeService, HttpExecutionContext ec, ActorSystem actorSystem, Materializer materializer, YoutubeAPI api)
	{
		this.form = formFactory.form(Search.class);
		this.messagesApi = messagesApi;
		this.youTubeService = youTubeService;
		this.httpExecutionContext = ec;
		this.actorSystem = actorSystem;
		this.materializer = materializer;
		this.api = api;
	}

	private CompletionStage<List<SearchResultItem>> calculateResponse(String term)
	{
		return CompletableFuture.completedFuture(youTubeService.getSearchResult(term));
	}

	public CompletionStage<Result> similarity(String term, Http.Request request)
	{
		String userSeesionId = "";
		Form<Search> filledForm = form.bindFromRequest(request);

		HashMap<String, String> session_map = new HashMap<>();
		session_map.put("sid", userSeesionId);

		List<TitleAndCount> searchResults = new ArrayList();

		SearchListResponse resp = api.search(term, "", 100L);

		if (resp != null && resp.getItems() != null && resp.getItems().size() > 0)
		{
			resp.getItems().stream().forEach(n -> {
				String title = n.getSnippet().getTitle();

				TitleAndCount c = new TitleAndCount();

				Map<String, Integer> map = new HashMap<>();

				Arrays.stream(title.split(" ")).forEach(x -> {
					String uniqueString = uniqueCharacters(x);
					map.put(uniqueString, uniqueString.length());
				});

				Consumer<Map.Entry<String, Integer>> display = a -> {
					String val = c.getCount();
					if (val != null)
					{
						val += a.getKey() + "::" + a.getValue() + ",";
						c.setCount(val);
					}
					else
					{
						c.setCount("");
					}
				};

				map.entrySet().stream()
					.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
					.forEachOrdered(display);

				c.setTitle(title);
				searchResults.add(c);

			});
		}

		return calculateResponse(filledForm.get().getTerm())
			.thenApplyAsync(
				answer -> {
					return ok(views.html.similarity.render(searchResults, form, request, messagesApi.preferred(request)))
						.withSession(session_map);
				},
				httpExecutionContext.current());
	}

	String uniqueCharacters(String test)
	{
		String temp = "";
		for (int i = 0; i < test.length(); i++)
		{
			char current = test.charAt(i);
			if (temp.indexOf(current) < 0)
			{
				temp = temp + current;
			}
			else
			{
				temp = temp.replace(String.valueOf(current), "");
			}
		}

		return temp;

	}
}
