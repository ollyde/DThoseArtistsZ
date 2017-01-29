package search.deezer.oliverdixon.dthoseartistsz.ui.ListenToAlbum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import search.deezer.oliverdixon.dthoseartistsz.R;
import search.deezer.oliverdixon.dthoseartistsz.common.Action;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseActivity;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseRecycleView;
import search.deezer.oliverdixon.dthoseartistsz.common.Logger;
import search.deezer.oliverdixon.dthoseartistsz.common.MusicPlayerSingleton;
import search.deezer.oliverdixon.dthoseartistsz.common.RetrofitSingleton;
import search.deezer.oliverdixon.dthoseartistsz.common.SquareImageView;
import search.deezer.oliverdixon.dthoseartistsz.models.AlbumResultModel;
import search.deezer.oliverdixon.dthoseartistsz.models.ListOfTracksModel;
import search.deezer.oliverdixon.dthoseartistsz.models.TrackModel;
import search.deezer.oliverdixon.dthoseartistsz.services.ArtistsService;
import search.deezer.oliverdixon.dthoseartistsz.ui.SearchArtists.ActivitySearchArtists;

public class ActivityListenToAlbum extends BaseActivity {

    private static final String KEY_ALBUM_DATA = "key_album_data";

    @BindView(R.id.title) TextView title;
    @BindView(R.id.sub_header) TextView subHeader;
    @BindView(R.id.album_art) SquareImageView albumArt;
    @BindView(R.id.album_name) TextView albumName;
    @BindView(R.id.track_list) BaseRecycleView trackList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check we have album data.
        if (!getIntent().getExtras().containsKey(KEY_ALBUM_DATA)) {
            Logger.logError("Album data was not in the intent, closing activity.");
            finish();
            return;
        }

        setContentView(R.layout.activity_listen_to_album);
        setupTrackList();

        AlbumResultModel albumResultModel = (AlbumResultModel) getIntent().getExtras().getSerializable(KEY_ALBUM_DATA);
        loadAlbumData(albumResultModel);
        getTrackData(albumResultModel);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicPlayerSingleton.getInstance().stop();
    }

    private void loadAlbumData(final AlbumResultModel albumResultModel) {
        title.setText(albumResultModel.getTitle());
        subHeader.setText(albumResultModel.getArtistName());
        ImageLoader.getInstance().displayImage(albumResultModel.getCoverBig(), albumArt);
        albumName.setText(albumResultModel.getTitle());
    }

    private void getTrackData(final AlbumResultModel albumResultModel) {

        // Service for getting artists.
        ArtistsService artistsService = RetrofitSingleton.getInstance().getRetrofit().create(ArtistsService.class);

        // Get the artist album info.
        Observable<ListOfTracksModel> getAlbums = artistsService.getTracks(albumResultModel.getId());

        getAlbums.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(error -> {
                Logger.logError("Unable to get tracks list. Message: " + error.getMessage());
                error.printStackTrace();
            })
            .subscribe(trackData -> {
                Logger.logInfo("Got " + trackData.getData().size() + " tracks.");
                TrackModel[] trackModels = trackData.getData().toArray(new TrackModel[trackData.getData().size()]);
                populateTracksList(trackModels);
            });
    }

    private void setupTrackList() {
        trackList.getAdapter().setOnClickListeners(new int[]{R.id.track_name_and_sub_header, R.id.track_no, R.id.play_pause_icon, R.id.track_time}, (pressTime, baseRecycleViewHolder) -> {

            final ViewHolderTrack viewHolderTrack = (ViewHolderTrack) baseRecycleViewHolder;
            final TrackModel trackModel = (TrackModel) viewHolderTrack.getRecycleViewDataModel();

            // Was tapped so we started loading the track.
            viewHolderTrack.setPlayMode(ViewHolderTrack.PlayMode.LOADING);

            // List for track changes and set the icon on or off.
            MusicPlayerSingleton.getInstance().listenForTrackChange(new Action<TrackModel>() {
                @Override
                public void invoke(TrackModel trackModelPlaying) {
                    super.invoke(trackModelPlaying);

                    if (trackModelPlaying.getId().equals(trackModel.getId())) {
                        if (MusicPlayerSingleton.getInstance().isThisTrackPlaying(trackModelPlaying)) {
                            viewHolderTrack.setPlayMode(ViewHolderTrack.PlayMode.PLAYING);
                        } else {
                            viewHolderTrack.setPlayMode(ViewHolderTrack.PlayMode.PAUSED);
                        }
                    } else {
                        viewHolderTrack.setPlayMode(ViewHolderTrack.PlayMode.NOT_BEING_USED);
                    }
                }
            });

            MusicPlayerSingleton.getInstance().playTrack(trackModel, new Action<Boolean>() {
                @Override
                public void invoke(Boolean isPlaying) {
                    super.invoke(isPlaying);

                    if (isPlaying) {
                        viewHolderTrack.setPlayMode(ViewHolderTrack.PlayMode.PLAYING);
                    } else {
                        viewHolderTrack.setPlayMode(ViewHolderTrack.PlayMode.PAUSED);
                    }
                }
            });
        });
    }

    private void populateTracksList(TrackModel[] trackModels) {
        MusicPlayerSingleton.getInstance().clearTracksListening();
        trackList.getAdapter().setItems(trackModels);
    }

    @OnClick(R.id.search_icon_button)
    public void goBackToSearchActivity(View view) {
        Intent goBackToSearch = new Intent(ActivityListenToAlbum.this, ActivitySearchArtists.class);
        goBackToSearch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(goBackToSearch);
    }

    public static void open(Context context, AlbumResultModel albumResultModel) {
        Intent openThis = new Intent(context, ActivityListenToAlbum.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_ALBUM_DATA, albumResultModel);
        openThis.putExtras(bundle);
        context.startActivity(openThis);
    }
}
