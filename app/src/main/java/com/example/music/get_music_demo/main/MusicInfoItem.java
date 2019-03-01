package com.example.music.get_music_demo.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.music.get_music_demo.R;

public class MusicInfoItem extends RecyclerView.ViewHolder {
    public ImageView coverImg;
    public TextView trackName;
    public TextView collectionName;
    public MusicInfoItem(@NonNull View itemView) {
        super(itemView);
        coverImg = itemView.findViewById(R.id.cover_img);
        trackName = itemView.findViewById(R.id.trace_name);
        collectionName = itemView.findViewById(R.id.collection_name);
    }
}
