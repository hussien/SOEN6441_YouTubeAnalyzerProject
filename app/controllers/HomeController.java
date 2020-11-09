package controllers;

import com.google.api.services.youtube.model.CommentListResponse;
import com.google.api.services.youtube.model.CommentSnippet;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.common.collect.Lists;
import models.Search;
import models.SearchResult;
import models.SearchResultItem;
import org.apache.commons.lang3.StringUtils;
import play.api.i18n.MessagesApi;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import play.*;
import play.mvc.Http;
import play.mvc.Result;
import util.YoutubeAPI;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * This controller represents a handler for HTTP routing requests
 * and resources such as index page
 * @author Hussein
 */
public class HomeController extends Controller {
	/**
	 * represents http form data model
	 */
    private final Form<Search> form;
    /**
     * represents the application cashed previous search data 
     * (application session) in a map
     */
    private  static HashMap<String,List<SearchResult>> map_searchResults=new HashMap<>();
    /**
     * represents list of user cookies (user session)
     */
    private  static List<String> lst_UsersSessionsIds=new ArrayList<>();
    /**
     * represents user search query results
     */
    private  List<SearchResult> searchResults;
    /**
     * represents message API for play framework actors
     */
    private MessagesApi messagesApi;

    /**
     * this method act as http form handler that retrieve form data
     * that posted into search data model class
     * @author Hussein
     * @param formFactory
     * @param messagesApi
     */
    @Inject
    public HomeController(FormFactory formFactory, MessagesApi messagesApi) {
        this.form = formFactory.form(Search.class);
        this.messagesApi = messagesApi;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     * @author Hussein
     */
    public Result index(Http.Request request) {
        //searchResults = Lists.newArrayList();
    	String userSeesionId="";
    	//check if user session (cookie) not found before 
    	// and create new session 
    	if (!request.session().get("sid").isPresent())
    	{    		
    		userSeesionId=lst_UsersSessionsIds.size()+"";
    		//userSeesionId=request.session().get("sid").get();
    		//request.session().("userSessionId",userSeesionId);
    	    lst_UsersSessionsIds.add(userSeesionId);
    		map_searchResults.put(userSeesionId, new ArrayList<SearchResult>());
    		System.out.println("New user SessionId="+userSeesionId);
    	}
    	//get user session
    	else
    	{
    		userSeesionId=request.session().get("sid").orElse(null);    	
    		System.out.println("user SessionId="+userSeesionId);
    	}
    	
    	//find user previous search results
    	searchResults=map_searchResults.get(userSeesionId);
    	if(searchResults ==null)
    	{
    		searchResults=new ArrayList<SearchResult>();
    		map_searchResults.put(userSeesionId, searchResults);
    	}
    	
    	//get http form posted data
        Form<Search> filledForm = form.bindFromRequest(request);
        //apply search query
        if (StringUtils.isNotEmpty(filledForm.get().getTerm())) {
            /*String[] terms = StringUtils.split(filledForm.get().getTerm(), " ");
            for (String term : terms) {
                addSearchResults(term);
            }*/
        	 addSearchResults(filledForm.get().getTerm());
        }
        
        //save use cookie id
        HashMap<String, String> session_map=new HashMap<>();
        session_map.put("sid",userSeesionId);
        return ok(views.html.index.render(searchResults, form, request, messagesApi.preferred(request)))
        		.withSession(session_map);
    }
    /**
     * this method act as cashed data service that find search 
     * for previous cashed results instead of calling API a second time 
     * @param term represents the search keywords
     * @return a list of previous search items results from the cashed data
     * @author Hussein
     */
    private List<SearchResultItem> getPreviouseSearchResult(String term/*, String duration*/) {
    	Iterator<List<SearchResult>> it_sr=map_searchResults.values().iterator();
        while(it_sr.hasNext())
        {        	
        	for(SearchResult item : it_sr.next())
        	{
        		if(item.getTerm().toLowerCase().trim().equals(term.toLowerCase().trim())
        				&&item.getItems().size()>0)
        		{
        			System.out.println("Previouse search term found="+term);
        			return item.getItems();
        		}
        	}
        }
        return null;
    }
    
    /**
     * this method add the new search query results into the previous cashed
     * search queries to be able search it in next requests
     * @param term represents the search keywords
     * @author Hussein 
     */
    private void addSearchResults(String term) {
        SearchResult sResult = new SearchResult();
        List<SearchResultItem> items = new ArrayList<>();
        List<SearchResultItem> prev_items=getPreviouseSearchResult(term);
        if(prev_items!=null)
        {
        	items.addAll(prev_items);	
        }
        else
        {
	        items.addAll(YoutubeAPI.getSearchResult(term/*, "long"*/));
	        //items.addAll(getSearchResult(term, "medium"));
	        //items.addAll(getSearchResult(term, "short"));        
            items.sort(Comparator.comparing(SearchResultItem::getSimilarity).reversed());
        }
        sResult.setItems(items);
        sResult.setTerm(term);       
        searchResults.add(sResult);
    }
    

}

