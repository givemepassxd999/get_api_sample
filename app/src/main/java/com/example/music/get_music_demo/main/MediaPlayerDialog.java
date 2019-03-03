package com.example.music.get_music_demo.main;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.music.get_music_demo.R;
import com.example.music.get_music_demo.database.MusicInfo;

import java.io.IOException;

public class MediaPlayerDialog extends Dialog {
    private MediaPlayer mediaPlayer;
    private Context context;
    private MusicInfo musicInfo;
    MediaPlayerDialog(Context context, MusicInfo musicInfo) {
        super(context, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        if(getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        this.context = context;
        this.musicInfo = musicInfo;
        final View mainView = LayoutInflater.from(context).inflate(R.layout.media_player_dialog, null);
        initView(mainView);
    }

    private void initView(View mainView){
        ImageView coverImgView = mainView.findViewById(R.id.cover_img);
        TextView traceNameView = mainView.findViewById(R.id.trace_name);
        TextView collectionNameView = mainView.findViewById(R.id.collection_name);
        View outsideLayout = mainView.findViewById(R.id.out_side_layout);
        View play = mainView.findViewById(R.id.play);
        outsideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        String trackName = musicInfo.getTrackName();
        if(!TextUtils.isEmpty(trackName)) {
            traceNameView.setText(trackName);
        }
        String collectionName = musicInfo.getCollectionName();
        if(!TextUtils.isEmpty(collectionName)) {
            collectionNameView.setText(collectionName);
        }
        Glide.with(context).load(musicInfo.getArtworkUrl100()).into(coverImgView);
        setContentView(mainView);
        initMediaPlayer();
        play.setSelected(true);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(mediaPlayer.isPlaying());
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }
        });
    }

    private void initMediaPlayer() {
        String url = musicInfo.getPreviewUrl();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
