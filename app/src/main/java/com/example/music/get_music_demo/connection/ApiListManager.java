package com.example.music.get_music_demo.connection;

public class ApiListManager {
    public static ApiList getApiListManager(String url){
        return new AppClientManager(url).getClient().create(ApiList.class);
    }
    public static ApiList getApiListManager(){
        return new AppClientManager().getClient().create(ApiList.class);
    }
}
