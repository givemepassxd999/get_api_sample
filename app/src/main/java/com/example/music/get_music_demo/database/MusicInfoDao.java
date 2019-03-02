package com.example.music.get_music_demo.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MusicInfoDao {
    String MUSIC_INFO_TABLE = "music_info";

    @Query("select * from " + MUSIC_INFO_TABLE + " where keyword = :value")
    List<MusicInfo> query(String value);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MusicInfo musicInfo);
}
