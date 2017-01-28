package search.deezer.oliverdixon.dthoseartistsz.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public class BaseRecycleViewHolder extends RecyclerView.ViewHolder {

    private RecycleViewDataModel recycleViewDataModel;
    public static final String VIEW_TAG = "BaseRecycleViewHolder_tag";

    public BaseRecycleViewHolder(View createdView) {
        super(createdView);
        ButterKnife.bind(this, createdView);
        createdView.setTag(VIEW_TAG);
    }

    public void loadModel(Object model) {
        recycleViewDataModel = (RecycleViewDataModel) model;
    }

    public RecycleViewDataModel getRecycleViewDataModel() {
        return recycleViewDataModel;
    }

    /**
     * Set the click listeners for the recycle view item.
     *
     * @param recycleViewOnClickListener the listener that is fired.
     * @param viewIds the view ids you want to listen too.
     */
    public void setClickListeners(RecycleViewOnClickListener recycleViewOnClickListener, int[] viewIds) {
        for (int eachViewId : viewIds) {
            final View view = itemView.findViewById(eachViewId);

            // Might not exist if we are using multiple recycle view types.
            if (view != null) {
                view.setOnClickListener(v -> recycleViewOnClickListener.viewClicked(RecycleViewOnClickListener.PressTime.SHORT_PRESS, BaseRecycleViewHolder.this));
                view.setLongClickable(true);
                view.setOnLongClickListener(v -> {
                    recycleViewOnClickListener.viewClicked(RecycleViewOnClickListener.PressTime.LONG_PRESS, BaseRecycleViewHolder.this);
                    return true;
                });
            }
        }
    }
}
