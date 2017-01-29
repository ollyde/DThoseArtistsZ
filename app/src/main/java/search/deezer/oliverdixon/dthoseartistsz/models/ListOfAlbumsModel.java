package search.deezer.oliverdixon.dthoseartistsz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import search.deezer.oliverdixon.dthoseartistsz.common.RecycleViewDataModel;

public class ListOfAlbumsModel implements RecycleViewDataModel, Serializable {

    @SerializedName("data")
    @Expose
    private List<AlbumResultModel> data = null;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("next")
    @Expose
    private String next;

    public List<AlbumResultModel> getData() {
        return data;
    }

    public void setData(List<AlbumResultModel> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public int getViewId() {
        // TODO
        return 0;
    }

    @Override
    public Class getViewClass() {
        // TODO
        return null;
    }
}
