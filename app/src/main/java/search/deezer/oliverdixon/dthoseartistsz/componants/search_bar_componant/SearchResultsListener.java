package search.deezer.oliverdixon.dthoseartistsz.componants.search_bar_componant;

public interface SearchResultsListener<T> {
    void gotResults(T dataModelResult);
    void failed();
}
