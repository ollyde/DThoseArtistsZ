package search.deezer.oliverdixon.dthoseartistsz.services;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import search.deezer.oliverdixon.dthoseartistsz.models.ListOfAlbumsModel;
import search.deezer.oliverdixon.dthoseartistsz.models.ListOfArtistsModel;
import search.deezer.oliverdixon.dthoseartistsz.models.ListOfTracksModel;

public interface ArtistsService {
    @GET("search/artist")
    Observable<ListOfArtistsModel> getArtists(@Query("q") String artistName);

    @GET("album/{albumId}/tracks")
    Observable<ListOfTracksModel> getTracks(@Path("albumId") String albumId);

    @GET("artist/{artistId}/albums")
    Observable<ListOfAlbumsModel> getArtists(@Path("artistId") int artistId);
}
