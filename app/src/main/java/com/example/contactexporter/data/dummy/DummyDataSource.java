package com.example.contactexporter.data.dummy;

import android.support.annotation.NonNull;

import com.example.contactexporter.data.ContactsDataSource;
import com.example.contactexporter.ui.base.ViewDataLoadedCallback;
import com.example.contactexporter.ui.base.ViewItem;
import com.example.contactexporter.ui.selection.ContactViewItem;

import java.util.ArrayList;
import java.util.List;

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
    public void loadAll(@NonNull ViewDataLoadedCallback callback) {
        callback.onLoaded(DummyData.contactItemsLongList());
    }

    @Override
    public void search(@NonNull String name, @NonNull ViewDataLoadedCallback callback) {
        callback.onLoaded(DummyData.contactItems());
    }

    @Override
    public void letter(@NonNull String letter, @NonNull ViewDataLoadedCallback callback) {
        callback.onLoaded(DummyData.contactItems());
    }

    @Override
    public void loadById(long id, @NonNull ViewDataLoadedCallback callback) {
        final List<ViewItem> all = DummyData.contactItemsLongList();
        for (ViewItem item : all) {
            if (item instanceof ContactViewItem && ((ContactViewItem) item).getContactId() == id) {
                final int position = all.indexOf(item);
                callback.onLoaded(all.subList(position, position + 1));
                return;
            }
        }
    }

    @Override
    public void loadByIds(@NonNull List<Long> ids, @NonNull ViewDataLoadedCallback callback) {
        if (ids.isEmpty()) return;
        final List<ViewItem> all = DummyData.contactItemsLongList();
        final List<Long> idsLeft = new ArrayList<>(ids);
        final List<ViewItem> result = new ArrayList<>();
        for (ViewItem item : all) {
            if (item instanceof ContactViewItem) {
                final ContactViewItem contactViewItem = (ContactViewItem) item;
                final long tempId = contactViewItem.getContactId();
                if (idsLeft.contains(tempId)) {
                    idsLeft.remove(tempId);
                    result.add(DummyData.progress(contactViewItem));
                    if (idsLeft.isEmpty()) {
                        break;
                    }
                }
            }
        }
        callback.onLoaded(result);
    }
}
