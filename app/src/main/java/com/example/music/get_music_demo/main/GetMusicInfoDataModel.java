package com.example.music.get_music_demo.main;

import android.arch.lifecycle.MutableLiveData;

import com.example.music.get_music_demo.connection.ApiListManager;
import com.example.music.get_music_demo.connection.MusicInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.music.get_music_demo.connection.Config.BASE_URL;

public class GetMusicInfoDataModel {
    public MutableLiveData<MusicInfoResponse> getMusicInfo(String value){
        final MutableLiveData<MusicInfoResponse> balanceRes = new MutableLiveData<>();
        ApiListManager.getApiListManager(BASE_URL).getMusicInfo(value).enqueue(new Callback<MusicInfoResponse>() {
            @Override
            public void onResponse(Call<MusicInfoResponse> call, Response<MusicInfoResponse> response) {
                MusicInfoResponse res = response.body();
                if(res == null){
                    res = new MusicInfoResponse();
                }
                balanceRes.setValue(res);
            }

            @Override
            public void onFailure(Call<MusicInfoResponse> call, Throwable t) {
                MusicInfoResponse failRes = new MusicInfoResponse();
                failRes.setHttpSuccess(false);
                failRes.setErrorMsg(t.getMessage());
                balanceRes.setValue(failRes);
            }
        });
        return balanceRes;
    }
}
