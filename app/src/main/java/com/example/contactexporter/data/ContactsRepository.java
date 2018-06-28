package com.example.contactexporter.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.contactexporter.data.dummy.DummyDataSource;
import com.example.contactexporter.data.local.LocalDataSource;
import com.example.contactexporter.ui.base.ViewDataLoadedCallback;
import com.example.contactexporter.ui.base.ViewItem;
import com.example.contactexporter.ui.selection.ContactViewItem;

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
    private final List<Long> completedIds;

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

    public static ContactsRepository getInstance() {
        return getInstance(
                LocalDataSource.getInstance(),
                DummyDataSource.getInstance());
    }

    private ContactsRepository(LocalDataSource local, DummyDataSource dummy) {
        if (local == null || dummy == null) {
            throw new IllegalStateException("Both local and dummy data sources must be valid");
        }
        this.localSource = local;
        this.dummySource = dummy;
        this.selectedIds = new ArrayList<>();
        this.completedIds = new ArrayList<>();
    }

    @Override
    public void loadAll(@NonNull ViewDataLoadedCallback callback) {
        // TODO: change to LocalDataSource
        dummySource.loadAll(new ViewDataLoadedCallback() {

            @Override
            public void onLoaded(List<ViewItem> viewItems) {
                // TODO : remove this from UI thread!?
                if (!selectedIds.isEmpty()) {
                    for (ViewItem item : viewItems) {
                        if (item instanceof ContactViewItem) {
                            final ContactViewItem contactItem = (ContactViewItem) item;
                            final boolean isChecked = selectedIds.contains(contactItem.getContactId());
                            contactItem.setSelected(isChecked);
                        }
                    }
                }
                callback.onLoaded(viewItems);
            }

            @Override
            public void onError(String message) {}
        });
    }

    @Override
    public void search(@NonNull String name, @NonNull ViewDataLoadedCallback callback) {
        dummySource.search(name, callback); // TODO: change to LocalDataSource
    }

    @Override
    public void letter(@NonNull String letter, @NonNull ViewDataLoadedCallback callback) {
        dummySource.letter(letter, callback); // TODO: change to LocalDataSource
    }

    @Override
    public void loadById(long id, @NonNull ViewDataLoadedCallback callback) {
        dummySource.loadById(id, callback); // TODO: change to LocalDataSource
    }

    @Override
    public void loadByIds(@NonNull List<Long> ids, @NonNull ViewDataLoadedCallback callback) {
        dummySource.loadByIds(ids, callback); // TODO: change to LocalDataSource
    }

    // TODO: add to ContactsDataSource !?
    public void loadSelected(@NonNull ViewDataLoadedCallback callback) {
        dummySource.loadByIds(selectedIds, callback); // TODO: change to LocalDataSource
    }

    public void contactSelection(long contactId, boolean isSelected) {
        updateList(selectedIds, contactId, isSelected);
        Log.e(TAG, "selectedIds: " + selectedIds); // TODO: remove!!
    }

    public void contactSelection(Map<Long, Boolean> selectionStateMap) {
        if (selectionStateMap == null) return;
        for (Map.Entry<Long, Boolean> entry : selectionStateMap.entrySet()) {
            contactSelection(entry.getKey(), entry.getValue());
        }
    }

    public void operationCompletion(long contactId, boolean isCompleted) {
        updateList(completedIds, contactId, isCompleted);
        Log.e(TAG, "completedIds: " + completedIds); // TODO: remove!!
    }

    public void operationCompletion(Map<Long, Boolean> operationCompletionMap) {
        if (operationCompletionMap == null) return;
        for (Map.Entry<Long, Boolean> entry : operationCompletionMap.entrySet()) {
            contactSelection(entry.getKey(), entry.getValue());
        }
    }

    public List<Long> selectedIds() {
        return selectedIds;
    }

    private void updateList(List<Long> list, long item, boolean add) {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (add) {
            if (!list.contains(item)) {
                list.add(item);
            }
        } else {
            final int index = list.indexOf(item);
            if (index != -1) {
                list.remove(index);
            }
        }
    }
}
