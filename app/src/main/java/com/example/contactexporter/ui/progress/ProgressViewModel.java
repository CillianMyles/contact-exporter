package com.example.contactexporter.ui.progress;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.example.contactexporter.ui.base.ViewItem;

import java.util.List;

/**
 * Created by Cillian Myles on 27/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ProgressViewModel extends AndroidViewModel {

    private final ProgressLiveData progressData;
    private final MutableLiveData<Boolean> isFinished;

    public ProgressViewModel(@NonNull Application application) {
        super(application);
        progressData = ProgressLiveData.getInstance(application);
        isFinished = new MutableLiveData<>();
        isFinished.setValue(false);
        fakeDelayedCompletion(); // TODO: delete this!!
    }

    private void fakeDelayedCompletion() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> isFinished.setValue(true), 2000);
    }

    public ProgressLiveData getProgressData() {
        return progressData;
    }

    public void setValue(List<ViewItem> value) {
        progressData.setValue(value);
    }

    public LiveData<Boolean> isFinished() {
        return isFinished;
    }

    public void onDestroy() {
        // TODO: fix state of isFinished!? (loading animation)
    }

    public void load(long id) {
        progressData.loadById(id);
    }

    public void load(@NonNull List<Long> ids) {
        progressData.loadByIds(ids);
    }

    public void loadSelected() {
        progressData.loadSelected();
    }
}
