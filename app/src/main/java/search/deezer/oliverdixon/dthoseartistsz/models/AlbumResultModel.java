package search.deezer.oliverdixon.dthoseartistsz.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import search.deezer.oliverdixon.dthoseartistsz.R;
import search.deezer.oliverdixon.dthoseartistsz.common.RecycleViewDataModel;
import search.deezer.oliverdixon.dthoseartistsz.ui.ShowAlbums.ViewHolderAlbum;

public class AlbumResultModel implements Serializable, RecycleViewDataModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("cover_small")
    @Expose
    private String coverSmall;
    @SerializedName("cover_medium")
    @Expose
    private String coverMedium;
    @SerializedName("cover_big")
    @Expose
    private String coverBig;
    @SerializedName("cover_xl")
    @Expose
    private String coverXl;
    @SerializedName("genre_id")
    @Expose
    private Integer genreId;
    @SerializedName("fans")
    @Expose
    private Integer fans;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("record_type")
    @Expose
    private String recordType;
    @SerializedName("tracklist")
    @Expose
    private String tracklist;
    @SerializedName("explicit_lyrics")
    @Expose
    private Boolean explicitLyrics;
    @SerializedName("type")
    @Expose
    private String type;

    @Nullable int artistId;
    @Nullable String artistName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    public String getCoverMedium() {
        return coverMedium;
    }

    public void setCoverMedium(String coverMedium) {
        this.coverMedium = coverMedium;
    }

    public String getCoverBig() {
        return coverBig;
    }

    public void setCoverBig(String coverBig) {
        this.coverBig = coverBig;
    }

    public String getCoverXl() {
        return coverXl;
    }

    public void setCoverXl(String coverXl) {
        this.coverXl = coverXl;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public Boolean getExplicitLyrics() {
        return explicitLyrics;
    }

    public void setExplicitLyrics(Boolean explicitLyrics) {
        this.explicitLyrics = explicitLyrics;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Nullable
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(@Nullable int artistId) {
        this.artistId = artistId;
    }

    @Nullable
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(@Nullable String artistName) {
        this.artistName = artistName;
    }

    @Override
    public int getViewId() {
        return R.layout.viewholder_album;
    }

    @Override
    public Class getViewClass() {
        return ViewHolderAlbum.class;
    }
}
