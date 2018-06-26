package com.example.contactexporter.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.contactexporter.data.dummy.DummyDataSource;
import com.example.contactexporter.data.local.LocalDataSource;
import com.example.contactexporter.ui.ViewItem;

import java.util.List;
import java.util.Map;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactsLiveData extends LiveData<List<ViewItem>> {

    public static final int MODE_ALL = 0;
    public static final int MODE_SEARCH = 1;
    public static final int MODE_LETTER = 2; // TODO: remove!?

    private static int mode = MODE_ALL;
    private static final String NO_SEARCH = null;

    private final Application context; // TODO: remove!?
    private final ContactsRepository repository;

    private static volatile ContactsLiveData INSTANCE;
    private static final Object lock = new Object();

    public static ContactsLiveData getInstance(Application context) {
        if (INSTANCE == null) {
            synchronized (lock) {
                if (INSTANCE == null) {
                    INSTANCE = new ContactsLiveData(context);
                }
            }
        }
        return INSTANCE;
    }

    private ContactsLiveData(Application context) {
        this.context = context;
        repository = ContactsRepository.getInstance(
                LocalDataSource.getInstance(),
                DummyDataSource.getInstance());
        reset();
    }

    public void reset() {
        mode = MODE_ALL;
        repository.loadAll(callback);
    }

    public void search(String name) {
        mode = MODE_SEARCH;
        repository.search(name, callback);
    }

    public void letter(String letter) {
        mode = MODE_LETTER;
        repository.letter(letter, callback);
    }

    public void load(long id) {
        mode = MODE_ALL;
        repository.load(id, callback);
    }

    public void load(List<Long> ids) {
        mode = MODE_ALL;
        repository.load(ids, callback);
    }

    private ContactsRepository.LoadCallback callback = new ContactsRepository.LoadCallback() {

        @Override
        public void onLoaded(List<ViewItem> data) {
            setValue(data);
        }

        @Override
        public void onError(String message) {
            setValue(null);
        }
    };

    public void contactSelection(long contactId, boolean isSelected) {
        repository.contactSelection(contactId, isSelected);
    }

    public void contactSelection(Map<Long, Boolean> selectionStateMap) {
        repository.contactSelection(selectionStateMap);
    }

    public List<Long> selectedIds() {
        return repository.selectedIds();
    }
}
