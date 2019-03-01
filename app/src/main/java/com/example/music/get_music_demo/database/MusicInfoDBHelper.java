package com.example.music.get_music_demo.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

public class MusicInfoDBHelper {
    private MusicInfoDatabase musicInfoDatabase;
    private MusicInfoDao musicInfoDao;
    public void createDB(Context context){
        musicInfoDatabase = Room.inMemoryDatabaseBuilder(context, MusicInfoDatabase.class).build();
        musicInfoDao = musicInfoDatabase.getMusicInfoDao();
    }
    public void closeDB(){
        musicInfoDatabase.close();
    }

    public List<MusicInfo> queryData(String searchKey){
        return musicInfoDao.query(searchKey);
    }

    public void insertData(MusicInfo musicInfo){
        musicInfoDao.insert(musicInfo);
    }
}
