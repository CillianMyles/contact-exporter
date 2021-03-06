package com.example.contactexporter.ui.progress;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.contactexporter.R;
import com.example.contactexporter.ui.base.ViewItem;

import java.util.List;

/**
 * Created by Cillian Myles on 26/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ProgressActivity extends AppCompatActivity {

    private static final String TAG = ProgressActivity.class.getSimpleName();

    private ActionBar toolbar;
    private RecyclerView recyclerView;
    private ProgressAdapter adapter;
    private ProgressViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        bindViews();
        initRecyclerView();
        loadData();
    }

    private void bindViews() {
        toolbar = getSupportActionBar();
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void initRecyclerView() {
        adapter = new ProgressAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadData() {
        viewModel = ViewModelProviders.of(this).get(ProgressViewModel.class);
        viewModel.getProgressData().observe(this, listObserver);
        viewModel.isFinished().observe(this, finishedObserver);
    }

    private Observer<List<ViewItem>> listObserver = new Observer<List<ViewItem>>() {
        @Override
        public void onChanged(@Nullable List<ViewItem> items) {
            boolean validResults = items != null && !items.isEmpty();
            if (validResults) {
                adapter.swap(items);
            }
        }
    };

    private Observer<Boolean> finishedObserver = isFinished -> {
        final boolean finished = isFinished != null && isFinished;
        Log.e(TAG, "finished: " + finished); // TODO: remove
        toolbar.setTitle(finished
                ? R.string.progress_finished
                : R.string.progress_title);
        List<ViewItem> viewItems = viewModel.getProgressData().getValue();
        assert viewItems != null;
        for (ViewItem viewItem : viewItems) {
            if (viewItem instanceof ProgressViewItem) {
                ((ProgressViewItem) viewItem).setFinished(finished);
            }
        }
        viewModel.getProgressData().setValue(viewItems);
    };
}
