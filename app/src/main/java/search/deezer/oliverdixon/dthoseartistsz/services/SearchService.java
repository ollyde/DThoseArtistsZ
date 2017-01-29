package search.deezer.oliverdixon.dthoseartistsz.services;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import search.deezer.oliverdixon.dthoseartistsz.models.ListOfArtistsModel;

public interface SearchService {
    @GET("search/artist")
    Observable<ListOfArtistsModel> getArtists(@Query("q") String artistName);
}
