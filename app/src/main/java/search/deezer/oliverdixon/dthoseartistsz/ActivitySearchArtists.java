package search.deezer.oliverdixon.dthoseartistsz;

import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import search.deezer.oliverdixon.dthoseartistsz.common.Action;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseActivity;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseRecycleView;
import search.deezer.oliverdixon.dthoseartistsz.common.Logger;
import search.deezer.oliverdixon.dthoseartistsz.common.RecycleViewOnClickListener;
import search.deezer.oliverdixon.dthoseartistsz.componants.component_search_bar.SearchModelItem;
import search.deezer.oliverdixon.dthoseartistsz.componants.component_search_bar.ViewSearchBar;

public class ActivitySearchArtists extends BaseActivity {

    @BindView(R.id.artist_results) BaseRecycleView artistsResultsRecycleView;
    @BindView(R.id.search_bar) ViewSearchBar searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_artists);

        // Set the click listeners for the views.
        artistsResultsRecycleView.getAdapter().setOnClickListener(new int[]{R.id.artist_results_text, R.id.artist_results_image}, new RecycleViewOnClickListener() {
            @Override
            public void viewClicked(PressTime pressTime, View view) {
                Logger.logError("Working!!!");
            }
        });

        // Listen on the search bar then populate the adaptor.
        searchBar.listenForSearchResults(new Action<SearchModelItem[]>() {
            @Override
            public void invoke(SearchModelItem[] item) {
                super.invoke(item);
                artistsResultsRecycleView.getAdapter().setItems(item);
            }
        });

        // Listen for cleared text so we can clear the list.
        searchBar.listenForSearchCleared(new Action<Boolean>() {
            @Override
            public void invoke(Boolean item) {
                super.invoke(item);
                artistsResultsRecycleView.getAdapter().clearItems();
            }
        });
    }
}
