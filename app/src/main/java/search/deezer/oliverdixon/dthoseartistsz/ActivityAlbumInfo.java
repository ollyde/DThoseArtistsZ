package search.deezer.oliverdixon.dthoseartistsz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseActivity;
import search.deezer.oliverdixon.dthoseartistsz.common.Logger;

public class ActivityAlbumInfo extends BaseActivity {

    private static final String KEY_ALBUM_ID = "album_id_key";
    private static final String KEY_ARTIST_NAME = "artist_name_key";

    @BindView(R.id.artist_name) TextView artistsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);

        if (getIntent() == null || !getIntent().getExtras().containsKey(KEY_ALBUM_ID) || !getIntent().getExtras().containsKey(KEY_ARTIST_NAME)) {
            Logger.logError("You didn't supply an artist id and artist name, closing ActivityAlbumInfo. Please consider opening this activity with the static open function.");
            return;
        }

        artistsName.setText(getIntent().getStringExtra(KEY_ARTIST_NAME));
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
