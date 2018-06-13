package com.example.contactexporter.data;

import android.support.annotation.NonNull;

import com.example.contactexporter.data.dummy.DummyDataSource;
import com.example.contactexporter.data.local.LocalDataSource;

/**
 * Created by Cillian Myles on 11/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactsRepository implements ContactsDataSource {

    private static volatile ContactsRepository INSTANCE;

    private final LocalDataSource localSource;
    private final DummyDataSource dummySource;

    private static final Object lock = new Object();

    public static ContactsRepository getInstance(LocalDataSource local, DummyDataSource dummy) {
        if (INSTANCE == null) {
            synchronized (lock) {
                if (INSTANCE == null) {
                    INSTANCE = new ContactsRepository(local, dummy);
                }
            }
        }
        return INSTANCE;
    }

    private ContactsRepository(LocalDataSource local, DummyDataSource dummy) {
        if (local == null || dummy == null) {
            throw new IllegalStateException("Both local and dummy data sources must be valid");
        }
        this.localSource = local;
        this.dummySource = dummy;
    }

    @Override
    public void loadAll(@NonNull LoadCallback callback) {
        dummySource.loadAll(callback); // TODO: change to LocalDataSource
    }

    @Override
    public void search(@NonNull String name, @NonNull LoadCallback callback) {
        dummySource.search(name, callback); // TODO: change to LocalDataSource
    }

    @Override
    public void letter(@NonNull String letter, @NonNull LoadCallback callback) {
        dummySource.letter(letter, callback); // TODO: change to LocalDataSource
    }
}
