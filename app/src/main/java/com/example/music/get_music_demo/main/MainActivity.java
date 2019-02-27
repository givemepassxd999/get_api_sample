package com.example.music.get_music_demo.main.main;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.music.get_music_demo.R;
import com.example.music.get_music_demo.connection.MusicInfoResponse;
import com.example.music.get_music_demo.log.LogHelper;
import com.example.music.get_music_demo.main.GetMusicInfoFactory;
import com.example.music.get_music_demo.main.GetMusicInfoViewModel;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.main);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_adb_black_24dp);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        GetMusicInfoFactory getMusicInfoFactory = new GetMusicInfoFactory();
        GetMusicInfoViewModel getBalanceViewModel = ViewModelProviders.of(this, getMusicInfoFactory).get(GetMusicInfoViewModel.class);
        getBalanceViewModel.getMusicInfo("john").observe(this, new Observer<MusicInfoResponse>() {
            @Override
            public void onChanged(@Nullable MusicInfoResponse musicInfoResponse) {
                if(musicInfoResponse.isHttpSuccess()){
                    if(musicInfoResponse.isSuccess()){
                        LogHelper.print("!!<res:"+new Gson().toJson(musicInfoResponse));
                    } else{

                    }
                } else{

                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        MenuItem menuSearchItem = menu.findItem(R.id.search);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menuSearchItem.getActionView();

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        // 這邊讓icon可以還原到搜尋的icon
        searchView.setIconifiedByDefault(true);
        return true;
    }

}