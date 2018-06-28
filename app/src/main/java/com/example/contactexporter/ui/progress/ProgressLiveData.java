package com.example.contactexporter.ui.progress;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.contactexporter.data.ContactsRepository;
import com.example.contactexporter.ui.base.ViewDataLoadedCallback;
import com.example.contactexporter.ui.base.ViewItem;

import java.util.List;

/**
 * Created by Cillian Myles on 27/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ProgressLiveData extends LiveData<List<ViewItem>> {

    private final Application context; // TODO: remove!?
    private final ContactsRepository repository;

    private static volatile ProgressLiveData INSTANCE;
    private static final Object lock = new Object();

    public static ProgressLiveData getInstance(Application context) {
        if (INSTANCE == null) {
            synchronized (lock) {
                if (INSTANCE == null) {
                    INSTANCE = new ProgressLiveData(context);
                }
            }
        }
        return INSTANCE;
    }

    private ProgressLiveData(Application context) {
        this.context = context;
        repository = ContactsRepository.getInstance();
        loadSelected();
    }

    // FIXME: this is a hack - should either be MutableLiveData or the change should be sent deeper!?
    public void setValue(List<ViewItem> value) {
        super.setValue(value);
    }

    public void loadSelected() {
        repository.loadSelected(callback);
    }

    public void loadById(long id) {
        repository.loadById(id, callback);
    }

    public void loadByIds(List<Long> ids) {
        repository.loadByIds(ids, callback);
    }

    private ViewDataLoadedCallback callback = new ViewDataLoadedCallback() {

        @Override
        public void onLoaded(List<ViewItem> data) {
            setValue(data);
        }

        @Override
        public void onError(String message) {
            setValue(null);
        }
    };
}
