package com.example.contactexporter.data;

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
public class ContactsViewModel extends AndroidViewModel {

    private final ContactsLiveData liveData;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        liveData = ContactsLiveData.getInstance(application);
    }

    public LiveData<List<ViewItem>> getLiveData() {
        return liveData;
    }

    public void reset() {
        liveData.reset();
    }

    public void search(@NonNull String name) {
        liveData.search(name);
    }

    public void letter(@NonNull String letter) {
        liveData.letter(letter);
    }

    public void load(long id) {
        liveData.load(id);
    }

    public void load(@NonNull List<Long> ids) {
        liveData.load(ids);
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
