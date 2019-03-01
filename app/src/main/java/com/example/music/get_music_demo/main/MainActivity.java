package com.example.music.get_music_demo.main;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.example.music.get_music_demo.R;
import com.example.music.get_music_demo.connection.api.MusicInfoResponse;
import com.example.music.get_music_demo.database.MusicInfo;
import com.example.music.get_music_demo.database.MusicInfoDBHelper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private GetMusicInfoViewModel getBalanceViewModel;
    private RecyclerView recyclerView;
    private MusicInfoAdapter adapter;
    private MusicInfoDBHelper musicInfoDBHelper;
    private ExecutorService executorService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        GetMusicInfoFactory getMusicInfoFactory = new GetMusicInfoFactory();
        getBalanceViewModel = ViewModelProviders.of(this, getMusicInfoFactory).get(GetMusicInfoViewModel.class);
        musicInfoDBHelper = new MusicInfoDBHelper();
        musicInfoDBHelper.createDB(this);
        executorService = Executors.newSingleThreadExecutor();
    }

    private void initView() {
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
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MusicInfoAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void callGetMusicInfoApi(final String query){
        getBalanceViewModel.getMusicInfo(query).observe(this, new Observer<MusicInfoResponse>() {
            @Override
            public void onChanged(@Nullable MusicInfoResponse musicInfoResponse) {
                if(musicInfoResponse.isHttpSuccess()){
                    if(musicInfoResponse.isSuccess()){
                        final List<MusicInfo> musicInfos = musicInfoResponse.getMusicInfos();
                        if(musicInfos != null) {
                            adapter.setData(musicInfos);
                            adapter.notifyDataSetChanged();
                            executorService.submit(new Runnable() {
                                @Override
                                public void run() {
                                    for(MusicInfo musicInfo : musicInfos){
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
                    adapter.setData(musicInfos);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        MenuItem menuSearchItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menuSearchItem.getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                callGetMusicInfoApi(s);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                }
                searchView.clearFocus();
                return true;

            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

}