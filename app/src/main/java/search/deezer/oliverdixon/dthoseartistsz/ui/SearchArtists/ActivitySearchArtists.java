package search.deezer.oliverdixon.dthoseartistsz.ui.SearchArtists;

import android.os.Bundle;

import butterknife.BindView;
import search.deezer.oliverdixon.dthoseartistsz.R;
import search.deezer.oliverdixon.dthoseartistsz.common.Action;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseActivity;
import search.deezer.oliverdixon.dthoseartistsz.common.BaseRecycleView;
import search.deezer.oliverdixon.dthoseartistsz.common.Logger;
import search.deezer.oliverdixon.dthoseartistsz.componants.search_bar.ViewSearchBar;
import search.deezer.oliverdixon.dthoseartistsz.models.ArtistsResultModel;
import search.deezer.oliverdixon.dthoseartistsz.ui.ShowAlbums.ActivityAlbumInfo;

public class ActivitySearchArtists extends BaseActivity {

    @BindView(R.id.artist_results) BaseRecycleView artistsResultsRecycleView;
    @BindView(R.id.search_bar) ViewSearchBar searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_artists);

        // Set the click listeners for the view holders.
        artistsResultsRecycleView.getAdapter().setOnClickListeners(new int[]{R.id.artist_results_container}, (pressTime, baseRecycleViewHolder) -> {
            if (baseRecycleViewHolder instanceof ViewHolderSearchResult) {
                ViewHolderSearchResult viewHolderSearchResult = (ViewHolderSearchResult) baseRecycleViewHolder;
                ArtistsResultModel artistsResultModel = (ArtistsResultModel) viewHolderSearchResult.getRecycleViewDataModel();
                Logger.logInfo("Opening activity for artist " + artistsResultModel.getName());
                ActivityAlbumInfo.open(artistsResultModel.getId(), artistsResultModel.getName(), ActivitySearchArtists.this);
            }
        });

        // Listen on the search bar then populate the adaptor.
        searchBar.listenForSearchResults(new Action<ArtistsResultModel[]>() {
            @Override
            public void invoke(ArtistsResultModel[] item) {
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
