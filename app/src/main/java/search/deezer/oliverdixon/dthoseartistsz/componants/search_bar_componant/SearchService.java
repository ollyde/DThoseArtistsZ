package search.deezer.oliverdixon.dthoseartistsz.componants.search_bar_componant;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET("search/artist")
    Observable<ListOfSearchModels> getArtists(@Query("q") String artistName);
}
