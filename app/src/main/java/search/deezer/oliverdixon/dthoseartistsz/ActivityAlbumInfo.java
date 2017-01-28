package search.deezer.oliverdixon.dthoseartistsz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import search.deezer.oliverdixon.dthoseartistsz.common.BaseActivity;

public class ActivityAlbumInfo extends BaseActivity {

    private static final String KEY_ALBUM_ID = "album_id_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);


    }

    public static void open(int artistId, Context context) {
        Intent openThis = new Intent(context, ActivityAlbumInfo.class);
        openThis.putExtra(KEY_ALBUM_ID, artistId);
        context.startActivity(openThis);
    }

}
