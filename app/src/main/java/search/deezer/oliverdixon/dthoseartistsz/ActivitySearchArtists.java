package search.deezer.oliverdixon.dthoseartistsz;

import android.content.Intent;
import android.os.Bundle;

import butterknife.BindView;
import search.deezer.oliverdixon.dthoseartistsz.common.Action;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseActivity;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseRecycleView;
import search.deezer.oliverdixon.dthoseartistsz.common.Logger;
import search.deezer.oliverdixon.dthoseartistsz.componants.component_search_bar.ViewSearchBar;
import search.deezer.oliverdixon.dthoseartistsz.componants.component_search_results_artists.ViewHolderSearchResult;
import search.deezer.oliverdixon.dthoseartistsz.models.SearchModelItem;

public class ActivitySearchArtists extends BaseActivity {

    @BindView(R.id.artist_results) BaseRecycleView artistsResultsRecycleView;
    @BindView(R.id.search_bar) ViewSearchBar searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_artists);

        // Set the click listeners for the view holders.
        artistsResultsRecycleView.getAdapter().setOnClickListeners(new int[]{R.id.artist_results_ll}, (pressTime, baseRecycleViewHolder) -> {
            if (baseRecycleViewHolder instanceof ViewHolderSearchResult) {
                ViewHolderSearchResult viewHolderSearchResult = (ViewHolderSearchResult) baseRecycleViewHolder;
                SearchModelItem searchModelItem = (SearchModelItem) viewHolderSearchResult.getRecycleViewDataModel();
                Logger.logInfo("Opening activity for artist " + searchModelItem.getName());
                ActivityAlbumInfo.open(searchModelItem.getId(), ActivitySearchArtists.this);
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
