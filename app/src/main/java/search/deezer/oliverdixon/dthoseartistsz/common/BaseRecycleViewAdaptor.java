package search.deezer.oliverdixon.dthoseartistsz.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

public class BaseRecycleViewAdaptor extends RecyclerView.Adapter<BaseRecycleViewHolder> {

    private ArrayList<RecycleViewDataModel> items = new ArrayList<>();
    private LayoutInflater layoutInflater;

    private SparseArray<Class> viewClassReference = new SparseArray<>();

    public BaseRecycleViewAdaptor(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Class recycleViewClass = viewClassReference.get(viewType);

        View newViewGroup = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        try {
            return (BaseRecycleViewHolder) recycleViewClass.getDeclaredConstructor(new Class<?>[]{View.class}).newInstance(newViewGroup);

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
}
