package com.example.contactexporter.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.contactexporter.data.dummy.DummyDataSource;
import com.example.contactexporter.data.local.LocalDataSource;
import com.example.contactexporter.ui.ContactViewItem;
import com.example.contactexporter.ui.base.ViewItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Cillian Myles on 11/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactsRepository implements ContactsDataSource {

    private static final String TAG = ContactsRepository.class.getSimpleName();

    private static volatile ContactsRepository INSTANCE;

    private final List<Long> selectedIds;

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
        this.selectedIds = new ArrayList<>();
    }

    @Override
    public void loadAll(@NonNull LoadCallback callback) {
        // TODO: change to LocalDataSource
        dummySource.loadAll(new LoadCallback() {

            @Override
            public void onLoaded(List<ViewItem> contacts) {
                // TODO : remove this from UI thread!?
                if (!selectedIds.isEmpty()) {
                    for (ViewItem item : contacts) {
                        if (item instanceof ContactViewItem) {
                            final ContactViewItem contactItem = (ContactViewItem) item;
                            final boolean isChecked = selectedIds.contains(contactItem.getContactId());
                            contactItem.setSelected(isChecked);
                        }
                    }
                }
                callback.onLoaded(contacts);
            }

            @Override
            public void onError(String message) {}
        });
    }

    @Override
    public void search(@NonNull String name, @NonNull LoadCallback callback) {
        dummySource.search(name, callback); // TODO: change to LocalDataSource
    }

    @Override
    public void letter(@NonNull String letter, @NonNull LoadCallback callback) {
        dummySource.letter(letter, callback); // TODO: change to LocalDataSource
    }

    @Override
    public void load(long id, @NonNull LoadCallback callback) {
        dummySource.load(id, callback); // TODO: change to LocalDataSource
    }

    @Override
    public void load(@NonNull List<Long> ids, @NonNull LoadCallback callback) {
        dummySource.load(ids, callback); // TODO: change to LocalDataSource
    }

    public void contactSelection(long contactId, boolean isSelected) {
        if (isSelected) {
            if (!selectedIds.contains(contactId)) {
                selectedIds.add(contactId);
            }
        } else {
            final int index = selectedIds.indexOf(contactId);
            if (index != -1) {
                selectedIds.remove(index);
            }
        }
        Log.e(TAG, "selectedIds: " + selectedIds); // TODO: remove!!
    }

    public void contactSelection(Map<Long, Boolean> selectionStateMap) {
        if (selectionStateMap == null) return;
        for (Map.Entry<Long, Boolean> entry : selectionStateMap.entrySet()) {
            contactSelection(entry.getKey(), entry.getValue());
        }
    }

    public List<Long> selectedIds() {
        return selectedIds;
    }
}
