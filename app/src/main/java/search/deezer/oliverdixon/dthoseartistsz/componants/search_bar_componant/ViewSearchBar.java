package search.deezer.oliverdixon.dthoseartistsz.componants.search_bar_componant;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import search.deezer.oliverdixon.dthoseartistsz.R;

public class ViewSearchBar extends RelativeLayout {

    /**
     * The search type is provided by the view xml. It relates to {@link R.styleable#ViewSearchBar_searchType}
     *
     * If this is -1 no search type has been set.
     */
    private Integer searchType = -1;

    public ViewSearchBar(Context context) {
        super(context);
    }

    public ViewSearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ViewSearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(final AttributeSet attrs) {

        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ViewSearchBar, 0, 0);

        if (typedArray.hasValue(R.styleable.ViewSearchBar_searchType)) {
            int value = typedArray.getInt(R.styleable.ViewSearchBar_searchType, 0);
        }

        typedArray.recycle();

        /*
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.IconView,
                0, 0);

            // Gets you the 'value' number - 0 or 666 in your example
            if (a.hasValue(R.styleable.IconView_icon)) {
                int value = a.getInt(R.styleable.IconView_icon, 0));
            }

            a.recycle();
        }
         */


    }

}
