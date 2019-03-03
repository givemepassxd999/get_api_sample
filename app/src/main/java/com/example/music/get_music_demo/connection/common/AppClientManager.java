package com.example.music.get_music_demo.connection.common;

import android.support.annotation.NonNull;

import com.example.music.get_music_demo.log.LogHelper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class AppClientManager {
    private static final int requestApiTimeOut = 10;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    AppClientManager(){
        okHttpClient = createOkHttpClient();
        retrofit = createClient();
    }

    AppClientManager(String url){
        okHttpClient = createOkHttpClient();
        retrofit = createClient(url);
    }

    private OkHttpClient createOkHttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                LogHelper.print("!!<http:" + message);

            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(requestApiTimeOut, TimeUnit.SECONDS)
                .writeTimeout(requestApiTimeOut, TimeUnit.SECONDS)
                .readTimeout(requestApiTimeOut, TimeUnit.SECONDS)
                .build();
    }

    private Retrofit createClient(){
        return new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private Retrofit createClient(String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    Retrofit getClient(){
        return retrofit;
    }

}
