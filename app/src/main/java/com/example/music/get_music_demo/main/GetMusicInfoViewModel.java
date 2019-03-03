package com.example.music.get_music_demo.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.music.get_music_demo.connection.api.MusicInfoResponse;
import com.example.music.get_music_demo.database.MusicInfo;
import com.example.music.get_music_demo.database.MusicInfoDBHelper;
import com.example.music.get_music_demo.log.LogHelper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GetMusicInfoViewModel extends ViewModel {
    private GetMusicInfoDataModel dataModel;
    private ExecutorService executorService;
    public MutableLiveData<List<MusicInfo>> musicDatas = new MutableLiveData<>();
    private MusicInfoDBHelper musicInfoDBHelper;
    private Context context;
    public GetMusicInfoViewModel(GetMusicInfoDataModel dataModel) {
        this.dataModel = dataModel;
        musicInfoDBHelper = new MusicInfoDBHelper();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void setContext(Context context){
        this.context = context;
        musicInfoDBHelper.createDB(context);
    }

    public void callGetMusicInfoApi(final String query){
        dataModel.getMusicInfo(query).observe((MainActivity)context, new Observer<MusicInfoResponse>() {
            @Override
            public void onChanged(MusicInfoResponse musicInfoResponse) {
                if(musicInfoResponse.isHttpSuccess()){
                    if(musicInfoResponse.isSuccess()){
                        final List<MusicInfo> musicInfos = musicInfoResponse.getMusicInfos();
                        if(musicInfos != null) {
                            musicDatas.setValue(musicInfos);
                            executorService.submit(new Runnable() {
                                @Override
                                public void run() {
                                    for (MusicInfo musicInfo : musicInfos) {
                                        musicInfo.setKeyword(query);
                                        musicInfoDBHelper.insertData(musicInfo);
                                    }
                                }
                            });
                        }
                    } else{
                        queryDb(query);
                    }
                } else{
                    queryDb(query);
                }
            }
        });
    }

    private void queryDb(final String query){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                final List<MusicInfo> musicInfos = musicInfoDBHelper.queryData(query);
                if(musicInfos != null) {
                    musicDatas.postValue(musicInfos);
                }
            }
        });
    }
}
