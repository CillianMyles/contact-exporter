package com.example.contactexporter.data.dummy;

import android.support.annotation.NonNull;

import com.example.contactexporter.data.ContactsDataSource;
import com.example.contactexporter.ui.ContactViewItem;
import com.example.contactexporter.ui.ViewItem;

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
    public void loadAll(@NonNull LoadCallback callback) {
        callback.onLoaded(DummyData.viewItemsLongList());
    }

    @Override
    public void search(@NonNull String name, @NonNull LoadCallback callback) {
        callback.onLoaded(DummyData.viewItems());
    }

    @Override
    public void letter(@NonNull String letter, @NonNull LoadCallback callback) {
        callback.onLoaded(DummyData.viewItems());
    }

    @Override
    public void load(long id, @NonNull LoadCallback callback) {
        final List<ViewItem> all = DummyData.viewItemsLongList();
        for (ViewItem item : all) {
            if (item instanceof ContactViewItem && ((ContactViewItem) item).getContactId() == id) {
                final int position = all.indexOf(item);
                callback.onLoaded(all.subList(position, position + 1));
                return;
            }
        }
    }

    @Override
    public void load(@NonNull List<Long> ids, @NonNull LoadCallback callback) {
        if (ids.isEmpty()) return;
        final List<ViewItem> all = DummyData.viewItemsLongList();
        final List<Long> idsLeft = new ArrayList<>(ids);
        final List<ViewItem> result = new ArrayList<>();
        for (ViewItem item : all) {
            if (item instanceof ContactViewItem) {
                final long tempId = ((ContactViewItem) item).getContactId();
                if (idsLeft.contains(tempId)) {
                    idsLeft.remove(tempId);
                    result.add(item);
                    if (idsLeft.isEmpty()) {
                        break;
                    }
                }
            }
        }
        callback.onLoaded(result);
    }
}
