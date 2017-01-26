package search.deezer.oliverdixon.dthoseartistsz.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

public class BaseRecycleViewAdaptor extends RecyclerView.Adapter<BaseRecycleViewHolder> {

    private ArrayList<RecycleViewDataModel> items = new ArrayList<>();

    private LayoutInflater layoutInflater;

    public BaseRecycleViewAdaptor(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newView = layoutInflater.inflate(viewType, parent, false);
        return new BaseRecycleViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {
        holder.loadModel(this.items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
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
}
