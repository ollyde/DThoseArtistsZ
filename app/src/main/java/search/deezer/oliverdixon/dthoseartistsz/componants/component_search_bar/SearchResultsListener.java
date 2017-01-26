package search.deezer.oliverdixon.dthoseartistsz.componants.component_search_bar;

public interface SearchResultsListener<T> {
    void gotResults(T dataModelResult);
    void failed();
}
