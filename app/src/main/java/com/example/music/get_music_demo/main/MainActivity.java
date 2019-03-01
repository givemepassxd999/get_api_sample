package com.example.music.get_music_demo.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.music.get_music_demo.R;
import com.example.music.get_music_demo.database.MusicInfo;
import com.example.music.get_music_demo.databinding.ActivityMainBinding;
import com.roger.catloadinglibrary.CatLoadingView;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    private GetMusicInfoViewModel getBalanceViewModel;
    private RecyclerView recyclerView;
    private MusicInfoAdapter adapter;
    private ActivityMainBinding binding;
    private CatLoadingView loadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        GetMusicInfoFactory getMusicInfoFactory = new GetMusicInfoFactory();
        getBalanceViewModel = ViewModelProviders.of(this, getMusicInfoFactory).get(GetMusicInfoViewModel.class);
        getBalanceViewModel.setContext(this);
    }

    private void initView() {
        loadingView = new CatLoadingView();
        binding.toolbar.setTitle("");
        binding.toolbar.setNavigationIcon(R.drawable.ic_adb_black_24dp);
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = binding.searchEdit.getText().toString();
                if(TextUtils.isEmpty(s)) {
                    Toasty.warning(getApplicationContext(), R.string.input_search_text).show();
                } else {
                    getBalanceViewModel.callGetMusicInfoApi(s);
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        adapter = new MusicInfoAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        getBalanceViewModel.musicDatas.observe(this, new Observer<List<MusicInfo>>() {
            @Override
            public void onChanged(@Nullable List<MusicInfo> musicInfos) {
                if(musicInfos != null) {
                    adapter.setData(musicInfos);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}