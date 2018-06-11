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

    private final ContactsRepository repository;

    private final Application context;

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
        repository.loadAll(callback);
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
