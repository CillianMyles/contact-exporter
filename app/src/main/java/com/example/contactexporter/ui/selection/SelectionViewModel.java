package com.example.contactexporter.ui.selection;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.contactexporter.ui.base.ViewItem;

import java.util.List;
import java.util.Map;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class SelectionViewModel extends AndroidViewModel {

    private final SelectionLiveData liveData;

    public SelectionViewModel(@NonNull Application application) {
        super(application);
        liveData = SelectionLiveData.getInstance(application);
    }

    public LiveData<List<ViewItem>> getLiveData() {
        return liveData;
    }

    public void reset() {
        liveData.reset();
    }

    public void loadAll() {
        liveData.loadAll();
    }

    public void search(@NonNull String name) {
        liveData.search(name);
    }

    public void letter(@NonNull String letter) {
        liveData.letter(letter);
    }

    public void contactSelection(long contactId, boolean isSelected) {
        liveData.contactSelection(contactId, isSelected);
    }

    public void contactSelection(Map<Long, Boolean> selectionStateMap) {
        liveData.contactSelection(selectionStateMap);
    }

    public List<Long> selectedIds() {
        return liveData.selectedIds();
    }
}
