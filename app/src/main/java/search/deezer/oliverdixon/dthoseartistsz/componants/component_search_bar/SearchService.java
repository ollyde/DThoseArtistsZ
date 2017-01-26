package search.deezer.oliverdixon.dthoseartistsz.componants.component_search_bar;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET("search/artist")
    Observable<ListOfSearchModels> getArtists(@Query("q") String artistName);
}
