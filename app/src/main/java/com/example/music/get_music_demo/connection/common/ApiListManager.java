package com.example.music.get_music_demo.connection.common;

import com.example.music.get_music_demo.connection.api.ApiList;

public class ApiListManager {
    public static ApiList getApiListManager(String url){
        return new AppClientManager(url).getClient().create(ApiList.class);
    }
}
