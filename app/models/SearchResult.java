package models;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represents a list for search result data
 * @author Hussein
 */
public class SearchResult {
	/**
	 * represents list of search result records
	 */
	private List<SearchResultItem> items = new ArrayList<>();

	/**
	 * represnts the search keyword associated with search result
	 */
    private String term;

    /**
     * represents a getter for search result records
     * @return list of search result records
     * @author Hussein
     */
    public List<SearchResultItem> getItems() {
        return items;
    }
    
    /**
     * represents a setter for search result records
     * @param items search result
     * @author Hussein
     */
    public void setItems(List<SearchResultItem> items) {
        this.items = items;
    }
    /**
     * represents a getter for search keywords
     * @return search keywords
     * @author Hussein
     */
    public String getTerm() {
        return term;
    }
    /**
     * represents a setter for search keywords
     * @param term search keywords
     * @author Hussein
     */
    public void setTerm(String term) {
        this.term = term;
    }
}
