package search.deezer.oliverdixon.dthoseartistsz;

import android.os.Bundle;

import butterknife.BindView;
import search.deezer.oliverdixon.dthoseartistsz.common.Action;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseActivity;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseRecycleView;
import search.deezer.oliverdixon.dthoseartistsz.componants.component_search_bar.SearchModelItem;
import search.deezer.oliverdixon.dthoseartistsz.componants.component_search_bar.ViewSearchBar;

public class ActivitySearchArtists extends BaseActivity {

    @BindView(R.id.artist_results) BaseRecycleView artistsResultsRecycleView;
    @BindView(R.id.search_bar) ViewSearchBar searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_artists);

        // Listen on the search bar then populate the adaptor.
        searchBar.listenForSearchResults(new Action<SearchModelItem[]>() {
            @Override
            public void invoke(SearchModelItem[] item) {
                super.invoke(item);
                artistsResultsRecycleView.getAdapter().setItems(item);
            }
        });
    }
}
