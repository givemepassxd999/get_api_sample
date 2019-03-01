package com.example.music.get_music_demo.main;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.music.get_music_demo.R;
import com.example.music.get_music_demo.database.MusicInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MusicInfo> infoDatas;
    private Context context;
    public MusicInfoAdapter(Context context) {
        this.context = context;
        infoDatas = new ArrayList<>();
    }

    public void setData(List<MusicInfo> infoDatas){
        if(infoDatas != null) {
            this.infoDatas.clear();
            this.infoDatas.addAll(infoDatas);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.music_info_item, null);
        return new MusicInfoItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MusicInfoItem musicInfoItem = (MusicInfoItem) viewHolder;
        final MusicInfo musicInfo = infoDatas.get(position);
        String trackName = musicInfo.getTrackName();
        if(!TextUtils.isEmpty(trackName)) {
            musicInfoItem.trackName.setText(trackName);
        }
        String collectionName = musicInfo.getCollectionName();
        if(!TextUtils.isEmpty(collectionName)) {
            musicInfoItem.collectionName.setText(collectionName);
        }
        Glide.with(context).load(musicInfo.getArtworkUrl100()).into(musicInfoItem.coverImg);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = musicInfo.getPreviewUrl();
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return infoDatas.size();
    }
}
