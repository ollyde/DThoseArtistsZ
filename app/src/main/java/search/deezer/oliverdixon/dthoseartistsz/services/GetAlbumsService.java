package search.deezer.oliverdixon.dthoseartistsz.services;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import search.deezer.oliverdixon.dthoseartistsz.models.ListOfAlbumsModel;

public interface GetAlbumsService {
    @GET("artist/{artistId}/albums")
    Observable<ListOfAlbumsModel> getArtists(@Path("artistId") int artistId);
}