package com.example.music.get_music_demo.connection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiList {
    @GET("/search")
    Call<MusicInfoResponse> getMusicInfo(@Query("term") String value);
}
