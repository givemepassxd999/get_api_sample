package com.example.music.get_music_demo.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.music.get_music_demo.connection.MusicInfoResponse;

public class GetMusicInfoViewModel extends ViewModel {
    private GetMusicInfoDataModel dataModel;

    public GetMusicInfoViewModel(GetMusicInfoDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public MutableLiveData<MusicInfoResponse> getMusicInfo(String value){
        return dataModel.getMusicInfo(value);
    }
}
