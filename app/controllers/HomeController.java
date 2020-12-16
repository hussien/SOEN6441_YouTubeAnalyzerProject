package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.actors.SearchHistory.UserHistory;
import com.actors.SearchHistoryActor;
import com.actors.Supervisor;
import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import models.Search;
import models.SearchResult;
import models.SearchResultItem;
import play.api.i18n.MessagesApi;
import play.data.Form;
import play.data.FormFactory;
import play.libs.F;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.WebSocket;
import scala.compat.java8.FutureConverters;
import service.YouTubeService;

import com.actors.ChildActor;
import com.actors.ChannelInfoActor;
import com.actors.SimilarityAPIActor;
import com.actors.SimilarityAPIActor.Similarity;
import com.actors.SentimentAPIActor;
import com.actors.SentimentAPIActor.Sentiment;
import static akka.pattern.Patterns.ask;





import com.actors.ChannelResponse;

import service.YouTubeService;
/**
 * This controller represents a handler for HTTP routing requests
 * and resources such as index page
 * 
 * @author Hussein
 */
public class HomeController extends Controller
{

	/**
	 * represents http form data model
	 */
	private final Form<Search> form;
	/**
	 * represents the application cashed previous search data
	 * (application session) in a map
	 */
	private static HashMap<String, List<SearchResult>> map_searchResults = new HashMap<>();
	/**
	 * represents list of user cookies (user session)
	 */
	private static List<String> lst_UsersSessionsIds = new ArrayList<>();
	/**
	 * represents user search query results
	 */
	private List<SearchResult> searchResults;
	/**
	 * represents message API for play framework actors
	 */
	private MessagesApi messagesApi;

	/**
	 * this method act as http form handler that retrieve form data
	 * that posted into search data model class
	 * 
	 * @author Hussein
	 * @param formFactory
	 * @param messagesApi
	 */

	YouTubeService youTubeService;
	private final ActorSystem actorSystem;
	private final Materializer materializer;
	
	/**
	 * COntroller constructor that initialize AKKA Actor system and youtube API service
	 * @param formFactory
	 * @param messagesApi
	 * @param youTubeService
	 * @param ec
	 * @param actorSystem
	 * @param materializer
	 * @author Hussein
	 */
	@Inject
	public HomeController(FormFactory formFactory, MessagesApi messagesApi,
		YouTubeService youTubeService, HttpExecutionContext ec, ActorSystem actorSystem, Materializer materializer)
	{
		this.form = formFactory.form(Search.class);
		this.messagesApi = messagesApi;
		this.youTubeService = youTubeService;

		this.httpExecutionContext = ec;

		this.actorSystem = actorSystem;
		this.materializer = materializer;
	}

	/**
	 * An action that renders an HTML page with a welcome message.
	 * The configuration in the <code>routes</code> file means that
	 * this method will be called when the application receives a
	 * <code>GET</code> request with a path of <code>/</code>.
	 * 
	 * @author Hussein
	 */

	ActorRef searchHistoryActor;
	ActorRef SentimentActor;
	ActorRef similarityActor;

	ActorSystem system = ActorSystem.create();
	final ActorRef supervisor = system.actorOf(Supervisor.getProps(), "supervisor");
	final ActorRef channelInfoActor = system.actorOf(ChannelInfoActor.getProps());
	
	@Inject
	public void Application(ActorSystem system)
	{
		searchHistoryActor = system.actorOf(SearchHistoryActor.getProps());
		SentimentActor=system.actorOf(SentimentAPIActor.getProps());		
	}
	
	/**
	 * return the saved user search info from searchHistoryActor
	 * @author Hussein
	 */
	public CompletionStage<Result> saveUserInfo(String name, List<SearchResult> searchResult)
	{
		
		return FutureConverters.toJava(ask(searchHistoryActor, new UserHistory(name, searchResult), 1000))
			.thenApply(response -> ok(Json.toJson(response)));
	}

	@Inject
	private HttpExecutionContext httpExecutionContext;
	
	/**
	 * return youtube search result
	 * @param term
	 * @return
	 * @author Hussein
	 */
	private CompletionStage<List<SearchResultItem>> calculateResponse(String term)
	{
			   //CompletableFuture.completedFuture(youTubeService.getSearchResult(term,20L));
		return CompletableFuture.completedFuture(youTubeService.getSearchResult(term));
				
	}

	/**
	 * search for youtube term and update search results list
	 * @author Hussein
	 */
	public CompletionStage<Result> getResultWithTerm(String term, Http.Request request)
	{
		return calculateResponse(term)
			.thenApplyAsync(
				answer -> {
					addSearchResultsForAdd(answer, term);
					return ok(Json.toJson(searchResults));
				},
				httpExecutionContext.current());
	}

	/*
	 * already bind to "ws://localhost:9000/ws/{termName}"
	 * */
	/**
	 * search for youtube using Websocket and Actor flow
	 * @author Hussein
	 */
	public WebSocket socket(String termName)
	{
		return WebSocket.Json
			.acceptOrResult(this::createActorFlow);
	}

	/**
	 * execute Actor flow 
	 * @author Hussein
	 */
	private CompletionStage<F.Either<Result, Flow<JsonNode, JsonNode, ?>>> createActorFlow(Http.RequestHeader request)
	{
		return CompletableFuture.completedFuture(
			F.Either.Right(createFlowForActor()));
	}

	/**
	 * create Actor flow 
	 * @author Hussein
	 */
	private Flow<JsonNode, JsonNode, ?> createFlowForActor()
	{
		return ActorFlow.actorRef(Supervisor::getProps1, actorSystem, materializer);
	}
	/**
	 * home page router method
	 * @author Hussein
	 */
	public CompletionStage<Result> index(Http.Request request)
	{

		// searchResults = Lists.newArrayList();
		String userSeesionId = "";
		// check if user session (cookie) not found before
		// and create new session
		if (!request.session().get("sid").isPresent())
		{
			//
			userSeesionId = lst_UsersSessionsIds.size() + "";
			// userSeesionId=request.session().get("sid").get();
			// request.session().("userSessionId",userSeesionId);
			lst_UsersSessionsIds.add(userSeesionId);
			map_searchResults.put(userSeesionId, new ArrayList<SearchResult>());

			// CompletionStage<Result> userInfo
			saveUserInfo(userSeesionId, new ArrayList<SearchResult>());

			// CompletableFuture<Result> user = userInfo.toCompletableFuture();

			System.out.println("New user SessionId=" + userSeesionId);
		}
		else
		{
			userSeesionId = request.session().get("sid").orElse(null);

			System.out.println("user SessionId=" + userSeesionId);
		}

		// find user previous search results
		searchResults = map_searchResults.get(userSeesionId);
		if (searchResults == null)
		{
			searchResults = new ArrayList<SearchResult>();
			map_searchResults.put(userSeesionId, searchResults);

			// CompletionStage<Result> userInfo;
			saveUserInfo(userSeesionId, searchResults);

		}

		// get http form posted data
		Form<Search> filledForm = form.bindFromRequest(request);

		HashMap<String, String> session_map = new HashMap<>();
		session_map.put("sid", userSeesionId);

		final String uId = userSeesionId;

		supervisor.tell(new ChildActor.Command(filledForm.get().getTerm()), ActorRef.noSender());

		return calculateResponse(filledForm.get().getTerm())
			.thenApplyAsync(
				answer -> {
					if (filledForm.get().getTerm() != null)
						addSearchResults(answer, filledForm.get().getTerm(), uId);

					return ok(views.html.index.render(searchResults, form, request, messagesApi.preferred(request)))
						.withSession(session_map);
				},
				httpExecutionContext.current());
	}

	/**
	 * this method act as cashed data service that find search
	 * for previous cashed results instead of calling API a second time
	 * 
	 * @param term
	 *            represents the search keywords
	 * @return a list of previous search items results from the cashed data
	 * @author Hussein
	 */
	public List<SearchResultItem> getPreviouseSearchResult(String term/*, String duration*/)
	{
		Iterator<List<SearchResult>> it_sr = map_searchResults.values().iterator();
		while (it_sr.hasNext())
		{
			for (SearchResult item : it_sr.next())
			{
				if (item.getTerm().toLowerCase().trim().equals(term.toLowerCase().trim())
					&& item.getItems().size() > 0)
				{
					System.out.println("Previouse search term found=" + term);
					return item.getItems();
				}
			}
		}
		return null;
	}

	/**
	 * this method add the new search query results into the previous cashed
	 * search queries to be able search it in next requests
	 * 
	 * @param term
	 *            represents the search keywords
	 * @author Hussein
	 */

	public void addSearchResults(List<SearchResultItem> termResult, String term, String userSeesionId)
	{
		List<SearchResultItem> prev_items = getPreviouseSearchResult(term);
		if (prev_items != null)
		{
			prev_items.addAll(termResult);
			prev_items=prev_items.stream().distinct().collect(Collectors.toList());
			prev_items.sort(Comparator.comparing(SearchResultItem::getSimilarity).reversed());
		}
		else
		{
			SearchResult sResult = new SearchResult();
			List<SearchResultItem> items = new ArrayList<>();
			items.addAll(termResult);
			items.sort(Comparator.comparing(SearchResultItem::getSimilarity).reversed());
			sResult.setItems(items);
			sResult.setTerm(term);
			if (searchResults == null)
			{
				searchResults = new ArrayList<SearchResult>();
			}
			searchResults.add(sResult);
		}

	}
	/**
	 * add Search Results 
	 * @author Jagan
	 */
	public void addSearchResultsForAdd(List<SearchResultItem> termResult, String term)
	{
		termResult.stream()
				.forEach(x -> {
						FutureConverters.toJava(ask(SentimentActor, new SentimentAPIActor.Sentiment(x.getComments(),null), 100))
						.thenApply( y ->{
										x.setSentiment(((Integer)y).intValue());
										System.out.println("SentimentAPIActor returns=" + y); 
										return y;
										});
				 }
				);
		
		List<SearchResultItem> prev_items = getPreviouseSearchResult(term);
		if (prev_items != null)
		{
			prev_items.addAll(termResult);
			prev_items=prev_items.stream().distinct().collect(Collectors.toList());
			prev_items.sort(Comparator.comparing(SearchResultItem::getSimilarity).reversed());
		}
		else
		{
			SearchResult sResult = new SearchResult();
			List<SearchResultItem> items = new ArrayList<>();
			items.addAll(termResult);
			items.sort(Comparator.comparing(SearchResultItem::getSimilarity).reversed());
			sResult.setItems(items);
			sResult.setTerm(term);
			if (searchResults == null)
			{
				searchResults = new ArrayList<SearchResult>();
			}
			searchResults.add(sResult);
		}
	}
	/**
	 * getChannellInfo
	 * @author Jagan
	 */
	public CompletionStage<Result> getChannelInfo(String channelID){
//		youTubeService.getChannelResult(channelID);
//		return ok("Your new application is ready.");
		return FutureConverters.toJava(ask(channelInfoActor, new ChannelInfoActor.ChannelInfo(channelID, youTubeService), 10000))
				.thenApply(response -> ok((String) response));

	}
	/**
	 * getChannellInfo
	 * @author Jagan
	 */
	public Result getChannellInfo(String channelID){
		ChannelResponse channelResponse = youTubeService.getChannelResult(channelID);
		System.out.println(channelResponse.getCountry());
		System.out.println(channelResponse.getDescription());
		return ok(views.html.channel.render(channelResponse.getTitle(), channelResponse.getDescription(), channelResponse.getCountry(),
				channelResponse.getViewCount(), channelResponse.getSubscriberCount()));
	}

}
