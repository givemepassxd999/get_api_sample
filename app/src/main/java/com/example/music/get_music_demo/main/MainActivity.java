package com.example.music.get_music_demo.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.music.get_music_demo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        String tag = SearchFragment.TAG;
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            SearchFragment fragment = SearchFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment, tag)
                .commit();
        }
    }
}