package search.deezer.oliverdixon.dthoseartistsz.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public class BaseRecycleViewHolder extends RecyclerView.ViewHolder {

    public BaseRecycleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
    }

    public void loadModel(Object model) {
    }
}
