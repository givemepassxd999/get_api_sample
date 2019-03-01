package com.example.music.get_music_demo.connection.api;

import com.example.music.get_music_demo.connection.common.Response;
import com.example.music.get_music_demo.database.MusicInfo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class MusicInfoResponse extends Response {
    @SerializedName("resultCount")
    private int resultCount;
    @SerializedName("results")
    private ArrayList<MusicInfo> musicInfos;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public ArrayList<MusicInfo> getMusicInfos() {
        return musicInfos;
    }

    public void setMusicInfos(ArrayList<MusicInfo> musicInfos) {
        this.musicInfos = musicInfos;
    }
}


