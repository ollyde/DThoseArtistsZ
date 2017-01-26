package search.deezer.oliverdixon.dthoseartistsz.common;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class BaseRecycleView extends RecyclerView {
    public BaseRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // Generic adaptor that can take any view holder.
        // Setting the adaptor on the ViewController results in much wasted code.
        setAdapter(new BaseRecycleViewAdaptor(this));
    }

    @Override
    public BaseRecycleViewAdaptor getAdapter() {
        return (BaseRecycleViewAdaptor) super.getAdapter();
    }
}
