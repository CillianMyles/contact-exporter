package com.example.contactexporter.data.dummy;

import android.support.annotation.NonNull;

import com.example.contactexporter.data.ContactsDataSource;

/**
 * Created by Cillian Myles on 11/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class DummyDataSource implements ContactsDataSource {

    private static volatile DummyDataSource INSTANCE;

    private static final Object lock = new Object();

    public static DummyDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (lock) {
                if (INSTANCE == null) {
                    INSTANCE = new DummyDataSource();
                }
            }
        }
        return INSTANCE;
    }

    private DummyDataSource() {
        // Don't allow instantiation externally.
    }

    @Override
    public void loadAll(@NonNull LoadCallback callback) {
        callback.onLoaded(DummyData.longListViewItems());
    }

    @Override
    public void search(@NonNull String name, @NonNull LoadCallback callback) {
        callback.onLoaded(DummyData.contactViewItems());
    }

    @Override
    public void letter(@NonNull String letter, @NonNull LoadCallback callback) {
        callback.onLoaded(DummyData.contactViewItems());
    }
}
