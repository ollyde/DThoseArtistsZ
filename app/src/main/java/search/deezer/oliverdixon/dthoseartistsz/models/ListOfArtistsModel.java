package search.deezer.oliverdixon.dthoseartistsz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListOfArtistsModel implements Serializable {

    @SerializedName("data")
    @Expose
    private ArtistsResultModel[] data;

    public ArtistsResultModel[] getData() {
        return data;
    }
}
