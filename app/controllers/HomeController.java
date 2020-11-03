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
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final Form<Search> form;
    private  static HashMap<String,List<SearchResult>> map_searchResults=new HashMap<>();
    private  static List<String> lst_UsersSessionsIds=new ArrayList<>();
    private  List<SearchResult> searchResults;
    private MessagesApi messagesApi;

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
     */
    public Result index(Http.Request request) {
        //searchResults = Lists.newArrayList();
    	String userSeesionId="";
    	if (!request.session().get("sid").isPresent())
    	{    		
    		userSeesionId=lst_UsersSessionsIds.size()+"";
    		//userSeesionId=request.session().get("sid").get();
    		//request.session().("userSessionId",userSeesionId);
    	    lst_UsersSessionsIds.add(userSeesionId);
    		map_searchResults.put(userSeesionId, new ArrayList<SearchResult>());
    		System.out.println("New user SessionId="+userSeesionId);
    	}
    	else
    	{
    		userSeesionId=request.session().get("sid").orElse(null);    	
    		System.out.println("user SessionId="+userSeesionId);
    	}
    	
    	searchResults=map_searchResults.get(userSeesionId);
    	if(searchResults ==null)
    	{
    		searchResults=new ArrayList<SearchResult>();
    		map_searchResults.put(userSeesionId, searchResults);
    	}
    	
        Form<Search> filledForm = form.bindFromRequest(request);

        if (StringUtils.isNotEmpty(filledForm.get().getTerm())) {
            String[] terms = StringUtils.split(filledForm.get().getTerm(), " ");
            for (String term : terms) {
                addSearchResults(term);
            }
        }
        
        
        HashMap<String, String> session_map=new HashMap<>();
        session_map.put("sid",userSeesionId);
        return ok(views.html.index.render(searchResults, form, request, messagesApi.preferred(request)))
        		.withSession(session_map);
    }
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

