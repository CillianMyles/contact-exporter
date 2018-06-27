package com.example.contactexporter.data;

import android.support.annotation.NonNull;

import com.example.contactexporter.ui.base.ViewItem;

import java.util.List;

/**
 * Created by Cillian Myles on 07/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public interface ContactsDataSource {

    interface LoadCallback {

        void onLoaded(List<ViewItem> contacts);

        void onError(String message);
    }

    void loadAll(@NonNull LoadCallback callback);

    void search(@NonNull String name, @NonNull LoadCallback callback);

    void letter(@NonNull String letter, @NonNull LoadCallback callback);

    void loadById(long id, @NonNull LoadCallback callback);

    void loadByIds(@NonNull List<Long> ids, @NonNull LoadCallback callback);
}
