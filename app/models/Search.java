package models;

/**
 * this class represents a data model for http search form data
 * @author Hussein
 */
public class Search {

	/**
	 * represents the entered user search query keywords
	 */
    private String term;

    /**
     * represents a getter for from search term
     * @return search term
     * @author Hussein
     */
    public String getTerm() {
        return term;
    }
    /**
     * represents a setter for from search term
     * @author Hussein
     */
    public void setTerm(String term) 
    {
        this.term = term;
    }
}
