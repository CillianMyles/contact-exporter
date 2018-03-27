package com.example.contactexporter;

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

    private final ContactsLiveData mData;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        mData = new ContactsLiveData(application);
    }

    public LiveData<List<Contact>> getData() {
        return mData;
    }
}
