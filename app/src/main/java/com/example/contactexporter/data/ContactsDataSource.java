package com.example.contactexporter.data;

import android.support.annotation.NonNull;

import com.example.contactexporter.ui.base.ViewDataLoadedCallback;

import java.util.List;

/**
 * Created by Cillian Myles on 07/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public interface ContactsDataSource {

    void loadAll(@NonNull ViewDataLoadedCallback callback);

    void search(@NonNull String name, @NonNull ViewDataLoadedCallback callback);

    void letter(@NonNull String letter, @NonNull ViewDataLoadedCallback callback);

    void loadById(long id, @NonNull ViewDataLoadedCallback callback);

    void loadByIds(@NonNull List<Long> ids, @NonNull ViewDataLoadedCallback callback);
}
