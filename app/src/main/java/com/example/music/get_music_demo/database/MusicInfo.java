package com.example.music.get_music_demo.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import static com.example.music.get_music_demo.database.MusicInfoDao.MUSIC_INFO_TABLE;

@Entity(tableName = MUSIC_INFO_TABLE)
public class MusicInfo {
    private String keyword;
    @PrimaryKey
    @SerializedName("trackId")
    private long traceId;
    @SerializedName("trackName")
    private String trackName;
    @SerializedName("collectionName")
    private String collectionName;
    @SerializedName("previewUrl")
    private String previewUrl;
    @SerializedName("artworkUrl100")
    private String artworkUrl100;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public long getTraceId() {
        return traceId;
    }

    public void setTraceId(long traceId) {
        this.traceId = traceId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }
}
