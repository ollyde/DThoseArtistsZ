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
import search.deezer.oliverdixon.dthoseartistsz.R;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseActivity;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseRecycleView;
import search.deezer.oliverdixon.dthoseartistsz.common.Logger;
import search.deezer.oliverdixon.dthoseartistsz.common.SquareImageView;
import search.deezer.oliverdixon.dthoseartistsz.models.AlbumResultModel;
import search.deezer.oliverdixon.dthoseartistsz.ui.SearchArtists.ActivitySearchArtists;

public class ActivityListenToAlbum extends BaseActivity {

    private static final String KEY_ALBUM_DATA = "key_album_data";

    @BindView(R.id.title) TextView title;
    @BindView(R.id.sub_header) TextView subHeader;
    @BindView(R.id.album_art) SquareImageView albumArt;
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
        loadAlbumData((AlbumResultModel) getIntent().getExtras().getSerializable(KEY_ALBUM_DATA));
    }

    private void loadAlbumData(AlbumResultModel albumResultModel) {
        title.setText(albumResultModel.getTitle());
        subHeader.setText(albumResultModel.getArtistName());
        ImageLoader.getInstance().displayImage(albumResultModel.getCoverBig(), albumArt);
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
