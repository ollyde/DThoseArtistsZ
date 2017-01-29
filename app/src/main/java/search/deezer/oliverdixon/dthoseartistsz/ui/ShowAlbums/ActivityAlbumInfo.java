package search.deezer.oliverdixon.dthoseartistsz.ui.ShowAlbums;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import search.deezer.oliverdixon.dthoseartistsz.R;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseActivity;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseRecycleView;
import search.deezer.oliverdixon.dthoseartistsz.common.Logger;
import search.deezer.oliverdixon.dthoseartistsz.common.RetrofitSingleton;
import search.deezer.oliverdixon.dthoseartistsz.models.AlbumResultModel;
import search.deezer.oliverdixon.dthoseartistsz.models.ListOfAlbumsModel;
import search.deezer.oliverdixon.dthoseartistsz.services.GetAlbumsService;

public class ActivityAlbumInfo extends BaseActivity {

    private static final String KEY_ALBUM_ID = "album_id_key";
    private static final String KEY_ARTIST_NAME = "artist_name_key";

    @BindView(R.id.artist_name) TextView artistsName;
    @BindView(R.id.album_results_recycle_view) BaseRecycleView albumResultsRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);

        if (getIntent() == null || !getIntent().getExtras().containsKey(KEY_ALBUM_ID) || !getIntent().getExtras().containsKey(KEY_ARTIST_NAME)) {
            Logger.logError("You didn't supply an artist id and artist name, closing ActivityAlbumInfo. Please consider opening this activity with the static open function.");
            return;
        }

        artistsName.setText(getIntent().getStringExtra(KEY_ARTIST_NAME));

        setupRecycleView();
        getAlbumInfo(getIntent().getExtras().getInt(KEY_ALBUM_ID), getIntent().getExtras().getString(KEY_ARTIST_NAME));
    }

    private void getAlbumInfo(final int artistId, final String artistName) {

        // Service for getting artists.
        GetAlbumsService getAlbumsService = RetrofitSingleton.getInstance().getRetrofit().create(GetAlbumsService.class);

        // Get the artist album info.
        Observable<ListOfAlbumsModel> getAlbums = getAlbumsService.getArtists(artistId);

        getAlbums.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(error -> {
                Logger.logError("Error occurred while trying to get albums for artists id." + artistId + ", error message: " + error.getMessage());
                error.printStackTrace();
            })
            .subscribe(albumsData -> {
                Logger.logInfo("Got albums data, " + albumsData.getData().size() + " albums.");

                // Make the artist name and id to the results as they are not included.
                for (AlbumResultModel albumResultModel : albumsData.getData()) {
                    albumResultModel.setArtistId(artistId);
                    albumResultModel.setArtistName(artistName);
                }

                // Populate the recycle view with our models.
                AlbumResultModel[] albumResultModels = albumsData.getData().toArray(new AlbumResultModel[albumsData.getData().size()]);
                populateRecycleView(albumResultModels);
            });
    }

    private void setupRecycleView() {
        albumResultsRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void populateRecycleView(AlbumResultModel[] albumResultModels) {
        albumResultsRecycleView.getAdapter().setItems(albumResultModels);
    }

    @OnClick(R.id.search_icon_button)
    public void goBackToSearchActivity(View view) {
        finish();
    }

    public static void open(int artistId, String artistName, Context context) {
        Intent openThis = new Intent(context, ActivityAlbumInfo.class);
        openThis.putExtra(KEY_ALBUM_ID, artistId);
        openThis.putExtra(KEY_ARTIST_NAME, artistName);
        context.startActivity(openThis);
    }

}
