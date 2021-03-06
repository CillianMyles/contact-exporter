package com.example.contactexporter.data.local;

import android.support.annotation.NonNull;

import com.example.contactexporter.data.ContactsDataSource;
import com.example.contactexporter.ui.base.ViewDataLoadedCallback;

import java.util.List;

/**
 * Created by Cillian Myles on 07/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class LocalDataSource implements ContactsDataSource {

    private static volatile LocalDataSource INSTANCE;

    private static final Object lock = new Object();

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (lock) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSource();
                }
            }
        }
        return INSTANCE;
    }

    private LocalDataSource() {
        // Don't allow instantiation externally.
    }

    @Override
    public void loadAll(@NonNull ViewDataLoadedCallback callback) {
        // TODO: implement !!
    }

    @Override
    public void search(@NonNull String name, @NonNull ViewDataLoadedCallback callback) {
        // TODO: implement !!
    }

    @Override
    public void letter(@NonNull String letter, @NonNull ViewDataLoadedCallback callback) {
        // TODO: implement !!
    }

    @Override
    public void loadById(long id, @NonNull ViewDataLoadedCallback callback) {
        // TODO: implement !!
    }

    @Override
    public void loadByIds(@NonNull List<Long> ids, @NonNull ViewDataLoadedCallback callback) {
        // TODO: implement !!
    }
}
