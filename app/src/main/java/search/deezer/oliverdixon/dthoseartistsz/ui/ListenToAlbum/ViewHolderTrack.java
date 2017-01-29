package search.deezer.oliverdixon.dthoseartistsz.ui.ListenToAlbum;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import search.deezer.oliverdixon.dthoseartistsz.R;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseRecycleViewHolder;
import search.deezer.oliverdixon.dthoseartistsz.common.TextUtils;
import search.deezer.oliverdixon.dthoseartistsz.models.TrackModel;

import static android.view.View.GONE;

public class ViewHolderTrack extends BaseRecycleViewHolder {

    @BindView(R.id.track_no) TextView trackNo;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.sub_header) TextView subHeader;
    @BindView(R.id.play_icon) ImageView playIcon;
    @BindView(R.id.track_time) TextView trackTime;

    public ViewHolderTrack(View itemView) {
        super(itemView);
    }

    @Override
    public void loadModel(Object model) {
        super.loadModel(model);

        TrackModel trackModel = (TrackModel) model;

        String trackId = String.valueOf(trackModel.getTrackPosition()) + "."; // << I never usually do string concatenation this way. Usually with placeholders.
        trackNo.setText(trackId);
        title.setText(trackModel.getTitle());

        // Show or hide the sub header.
        if (TextUtils.isNullOrEmpty(trackModel.getTitleVersion())) {
            subHeader.setText("");
            subHeader.setVisibility(GONE);
        } else {
            subHeader.setText(trackModel.getTitleVersion());
            subHeader.setVisibility(View.VISIBLE);
        }

        trackTime.setText(DateUtils.formatElapsedTime(trackModel.getDuration()));
    }
}
