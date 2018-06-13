package com.example.contactexporter.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.contactexporter.data.dummy.DummyDataSource;
import com.example.contactexporter.data.local.LocalDataSource;

import java.util.List;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactsLiveData extends LiveData<List<Contact>> {

    public static final int MODE_ALL = 0;
    public static final int MODE_SEARCH = 1;
    public static final int MODE_LETTER = 2;

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

    private ContactsRepository.LoadCallback callback = new ContactsRepository.LoadCallback() {

        @Override
        public void onLoaded(List<Contact> data) {
            setValue(data);
        }

        @Override
        public void onError(String message) {
            setValue(null);
        }
    };
}
