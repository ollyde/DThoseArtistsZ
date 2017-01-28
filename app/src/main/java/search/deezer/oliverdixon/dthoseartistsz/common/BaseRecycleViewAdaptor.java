package search.deezer.oliverdixon.dthoseartistsz.common;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

public class BaseRecycleViewAdaptor extends RecyclerView.Adapter<BaseRecycleViewHolder> {

    private ArrayList<RecycleViewDataModel> items = new ArrayList<>();
    private SparseArray<Class> viewClassReference = new SparseArray<>();

    // State for inserting click listeners into the recycle view.
    private @Nullable RecycleViewOnClickListener recycleViewOnClickListener;
    private @Nullable int[] viewIds;

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Class recycleViewClass = viewClassReference.get(viewType);

        View newViewGroup = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        try {
            BaseRecycleViewHolder baseRecycleViewHolder = (BaseRecycleViewHolder) recycleViewClass.getDeclaredConstructor(new Class<?>[]{View.class}).newInstance(newViewGroup);
            if (recycleViewOnClickListener != null && viewIds != null) {
                baseRecycleViewHolder.setClickListeners(recycleViewOnClickListener, viewIds);
            }
            return baseRecycleViewHolder;

        } catch (Exception e) {
            Logger.logError("onCreateViewHolder unable to create new view holder. ViewType.no:" + viewType + ", classType:" + recycleViewClass.getSimpleName() + ", error message: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {
        holder.loadModel(this.items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        viewClassReference.put(this.items.get(position).getViewId(), this.items.get(position).getViewClass());
        return this.items.get(position).getViewId();
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public void setItems(RecycleViewDataModel[] newItems) {
        this.items.clear();
        Collections.addAll(this.items, newItems);
        notifyDataSetChanged();
    }

    public void clearItems() {
        this.items.clear();
        notifyDataSetChanged();
    }

    /**
     * You must set this before setting the items.
     *
     * @param recycleViewOnClickListener the listener for clicks.
     * @param viewIds the view ids that were clicked.
     *
     * Setting this will reset viewIds and any other click listeners.
     */
    public void setOnClickListener(@Nullable int[] viewIds, @Nullable RecycleViewOnClickListener recycleViewOnClickListener) {
        this.recycleViewOnClickListener = recycleViewOnClickListener;
        this.viewIds = viewIds;
    }
}
