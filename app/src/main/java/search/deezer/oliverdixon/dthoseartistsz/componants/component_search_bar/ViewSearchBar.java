package search.deezer.oliverdixon.dthoseartistsz.componants.component_search_bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import search.deezer.oliverdixon.dthoseartistsz.R;
import search.deezer.oliverdixon.dthoseartistsz.common.Action;
import search.deezer.oliverdixon.dthoseartistsz.common.EditTextCancel;
import search.deezer.oliverdixon.dthoseartistsz.common.Logger;
import search.deezer.oliverdixon.dthoseartistsz.common.ReferenceObserver;
import search.deezer.oliverdixon.dthoseartistsz.common.RetrofitSingleton;

public class ViewSearchBar extends RelativeLayout {

    /**
     * The search type is provided by the view xml. It relates to {@link R.styleable#ViewSearchBar_searchType}
     *
     * If this is -1 no search type has been set.
     *
     * We are not using this now, just an example.
     */
    private Integer searchType = -1;

    @BindView(R.id.search_component_edit_text) EditTextCancel editTextCancelSearchBox;

    private ReferenceObserver<SearchModelItem[]> searchModelResultListeners = new ReferenceObserver<>();

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
        ButterKnife.bind(this);

        // Extract the searchType enum from the view xml.
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.ViewSearchBar, 0, 0);
        if (typedArray.hasValue(R.styleable.ViewSearchBar_searchType)) {
            searchType = typedArray.getInt(R.styleable.ViewSearchBar_searchType, 0);
        } else {
            Logger.logWarning("ViewSearchBar has no se arch type set.");
        }
        typedArray.recycle();

        // Service for getting artists.
        SearchService searchService = RetrofitSingleton.getInstance().getRetrofit().create(SearchService.class);

        // List for edit text changes
        editTextCancelSearchBox.listenForTextChanges(new Action<String>() {
            @Override
            public void invoke(String item) {
                super.invoke(item);

                if (item.length() > 2) {

                    // List for results from the api.
                    Observable<ListOfSearchModels> searchArtists = searchService.getArtists(item);
                    searchArtists.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(error -> {
                            Logger.logError("Error occurred while trying to search for artists." + error.getMessage());
                            error.printStackTrace();
                        })
                        .subscribe(artistData -> {
                            // Send the results to the listeners.
                            searchModelResultListeners.emit(artistData.getData());
                        });
                }
            }
        });
    }

    public void listenForSearchResults(Action<SearchModelItem[]> action) {
        searchModelResultListeners.subscribe(action);
    }

    public void listenForSearchCleared(Action<Boolean> action) {
        editTextCancelSearchBox.listenForTextCleared(action);
    }
}
