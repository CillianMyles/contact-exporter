package com.example.contactexporter.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactsViewModel extends AndroidViewModel {

    private final ContactsLiveData liveData;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        liveData = ContactsLiveData.getInstance(application);
    }

    public LiveData<List<Contact>> getLiveData() {
        return liveData;
    }

    public void reset() {
        liveData.reset();
    }

    public void search(@NonNull String name) {
        liveData.search(name);
    }

    public void letter(@NonNull String letter) {
        liveData.letter(letter);
    }
}
