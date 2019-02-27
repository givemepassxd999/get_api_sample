package com.example.music.get_music_demo.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class GetMusicInfoFactory implements ViewModelProvider.Factory {
    private GetMusicInfoDataModel getMusicInfoDataModel;

    public GetMusicInfoFactory() {
        getMusicInfoDataModel = new GetMusicInfoDataModel();
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GetMusicInfoViewModel.class)) {
            return (T) new GetMusicInfoViewModel(getMusicInfoDataModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
