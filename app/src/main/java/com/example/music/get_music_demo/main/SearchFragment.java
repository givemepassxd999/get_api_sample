package com.example.music.get_music_demo.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.music.get_music_demo.R;
import com.example.music.get_music_demo.database.MusicInfo;
import com.example.music.get_music_demo.databinding.SearchFragmentBinding;
import com.roger.catloadinglibrary.CatLoadingView;

import java.util.List;
import java.util.Objects;

public class SearchFragment extends Fragment {
    public static final String TAG = "Search";
    private GetMusicInfoFactory getMusicInfoFactory = new GetMusicInfoFactory();
    private GetMusicInfoViewModel getBalanceViewModel;
    private SearchFragmentBinding binding;
    private MusicInfoAdapter adapter;
    private CatLoadingView loadingView;
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SearchFragmentBinding.inflate(inflater, container);
        initView();
        return binding.getRoot();
    }

    private void initView(){
        loadingView = new CatLoadingView();
        binding.toolbar.setTitle("音樂試聽");
        binding.toolbar.setNavigationIcon(R.drawable.ic_adb_black_24dp);
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSearch();
            }
        });
        binding.searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    binding.clear.setVisibility(View.INVISIBLE);
                    return;
                }
                binding.clear.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.searchEdit.getText().clear();
                dismissKeyboard();
            }
        });
        adapter = new MusicInfoAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getBalanceViewModel = ViewModelProviders.of(this, getMusicInfoFactory).get(GetMusicInfoViewModel.class);
        getBalanceViewModel.setContext(getContext());
        binding.setViewModel(getBalanceViewModel);

        getBalanceViewModel.musicDatas.observe(this, new Observer<List<MusicInfo>>() {
            @Override
            public void onChanged(@Nullable final List<MusicInfo> musicInfos) {
                loadingView.dismiss();
                if(musicInfos != null) {
                    Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            adapter.setData(musicInfos);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    private void doSearch() {
        loadingView.show(getFragmentManager(), "");
        String s = binding.searchEdit.getText().toString();
        getBalanceViewModel.callGetMusicInfoApi(s);
        dismissKeyboard();
    }

    private void dismissKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
