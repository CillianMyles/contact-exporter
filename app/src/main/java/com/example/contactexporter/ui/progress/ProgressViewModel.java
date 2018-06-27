package com.example.contactexporter.ui.progress;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.contactexporter.ui.base.ViewItem;

import java.util.List;

/**
 * Created by Cillian Myles on 27/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ProgressViewModel extends AndroidViewModel {

    private final ProgressLiveData liveData;

    public ProgressViewModel(@NonNull Application application) {
        super(application);
        liveData = ProgressLiveData.getInstance(application);
    }

    public LiveData<List<ViewItem>> getLiveData() {
        return liveData;
    }

    public void onDestroy() {
        // TODO: fix state of isFinished!? (loading animation)
    }

    public void load(long id) {
        liveData.loadById(id);
    }

    public void load(@NonNull List<Long> ids) {
        liveData.loadByIds(ids);
    }

    public void loadSelected() {
        liveData.loadSelected();
    }
}
