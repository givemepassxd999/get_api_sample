package com.example.music.get_music_demo.connection;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MusicInfoResponse extends Response {
    @SerializedName("resultCount")
    private int resultCount;
    @SerializedName("results")
    private ArrayList<Result> results;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public static class Result{
        @SerializedName("trackName")
        private String trackName;
        @SerializedName("collectionName")
        private String collectionName;
        @SerializedName("previewUrl")
        private String previewUrl;
        @SerializedName("artworkUrl100")
        private String artworkUrl100;

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
}


