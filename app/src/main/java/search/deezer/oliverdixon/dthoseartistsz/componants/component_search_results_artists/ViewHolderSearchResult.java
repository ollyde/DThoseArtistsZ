package search.deezer.oliverdixon.dthoseartistsz.componants.component_search_results_artists;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import search.deezer.oliverdixon.dthoseartistsz.R;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseRecycleViewHolder;
import search.deezer.oliverdixon.dthoseartistsz.models.SearchModelItem;

public class ViewHolderSearchResult extends BaseRecycleViewHolder {

    @BindView(R.id.artist_results_image) ImageView artistImageView;
    @BindView(R.id.artist_results_text) TextView artistsTextView;

    public ViewHolderSearchResult(View itemView) {
        super(itemView);
    }

    @Override
    public void loadModel(Object model) {
        super.loadModel(model);

        SearchModelItem searchModelItem = (SearchModelItem) model;

        // Reset the display image because it takes sometime to fetch it and load it.
        // We could do some checks here.
        artistImageView.setImageResource(0);

        // Get the image and set the text.
        ImageLoader.getInstance().displayImage(searchModelItem.getPictureSmall(), artistImageView);
        artistsTextView.setText(searchModelItem.getName());
    }
}
