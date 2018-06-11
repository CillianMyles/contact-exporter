package com.example.contactexporter.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Cillian Myles on 07/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public interface ContactsDataSource {

    interface LoadCallback {

        void onLoaded(List<Contact> contacts);

        void onError(String message);
    }

    void loadAll(@NonNull LoadCallback callback);
}
