package search.deezer.oliverdixon.dthoseartistsz.ui.ShowAlbums;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import search.deezer.oliverdixon.dthoseartistsz.R;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseRecycleViewHolder;
import search.deezer.oliverdixon.dthoseartistsz.models.AlbumResultModel;

public class ViewHolderAlbum extends BaseRecycleViewHolder {

    @BindView(R.id.album_art) ImageView artistAlbumArt;
    @BindView(R.id.artist_name) TextView artistName;
    @BindView(R.id.sub_header) TextView artistSubHeader;

    public ViewHolderAlbum(View itemView) {
        super(itemView);
    }

    @Override
    public void loadModel(Object model) {
        super.loadModel(model);

        AlbumResultModel albumResultModel = (AlbumResultModel) model;

        artistName.setText(albumResultModel.getTitle());
        artistSubHeader.setText(albumResultModel.getArtistName());

        artistAlbumArt.setImageResource(0);
        ImageLoader.getInstance().displayImage(albumResultModel.getCoverMedium(), artistAlbumArt);
    }
}
