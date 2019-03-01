package com.example.music.get_music_demo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MusicInfo.class}, version = 1)
public abstract class MusicInfoDatabase extends RoomDatabase {
    public abstract MusicInfoDao getMusicInfoDao();
}
