package search.deezer.oliverdixon.dthoseartistsz.componants.component_search_bar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfSearchModels {

    @SerializedName("data")
    @Expose
    private SearchModelItem[] data;

    public SearchModelItem[] getData() {
        return data;
    }
}
