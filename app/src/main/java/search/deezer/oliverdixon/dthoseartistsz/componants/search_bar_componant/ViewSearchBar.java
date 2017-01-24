package search.deezer.oliverdixon.dthoseartistsz.componants.search_bar_componant;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import search.deezer.oliverdixon.dthoseartistsz.R;
import search.deezer.oliverdixon.dthoseartistsz.common.Logger;

public class ViewSearchBar extends RelativeLayout {

    /**
     * The search type is provided by the view xml. It relates to {@link R.styleable#ViewSearchBar_searchType}
     *
     * If this is -1 no search type has been set.
     */
    private Integer searchType = -1;

    public ViewSearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ViewSearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * @param attributeSet passed from the view initialisation.
     *
     * We need to call whenever we use the view with XML layouts so we can watch for custom attributes.
     */
    private void init(final AttributeSet attributeSet) {

        // Inflate the custom view resource
        inflate(getContext(), R.layout.view_componant_search_arists, this);

        // Extract the searchType enum from the view xml.
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.ViewSearchBar, 0, 0);
        if (typedArray.hasValue(R.styleable.ViewSearchBar_searchType)) {
            searchType = typedArray.getInt(R.styleable.ViewSearchBar_searchType, 0);
        } else {
            Logger.logWarning("ViewSearchBar has no se arch type set.");
        }
        typedArray.recycle();
    }

}
