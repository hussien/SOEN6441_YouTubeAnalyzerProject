package models;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {

    private List<SearchResultItem> items = new ArrayList<>();

    private String term;

    public List<SearchResultItem> getItems() {
        return items;
    }

    public void setItems(List<SearchResultItem> items) {
        this.items = items;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
