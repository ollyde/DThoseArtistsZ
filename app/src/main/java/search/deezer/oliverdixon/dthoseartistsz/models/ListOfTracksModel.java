package search.deezer.oliverdixon.dthoseartistsz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class ListOfTracksModel implements Serializable {

    @SerializedName("data")
    @Expose
    private List<TrackModel> data = null;

    public List<TrackModel> getData() {
        return data;
    }
}
