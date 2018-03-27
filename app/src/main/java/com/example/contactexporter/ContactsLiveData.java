package com.example.contactexporter;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */
public class ContactsLiveData extends LiveData<List<Contact>> {

    private final Context mContext;

    public ContactsLiveData(Context pContext) {
        mContext = pContext;
        loadData();
    }

    private void loadData() {
        // TODO: pass search into task/query !?
        new AsyncTask<Void, Void, List<Contact>>() {

            @Override
            protected List<Contact> doInBackground(Void... pVoids) {
                List<Contact> lContacts = new ArrayList<>();
                // TODO: remove fake contacts below.
                lContacts.add(new Contact(1, new HashMap<String, String>()));
                lContacts.add(new Contact(2, new HashMap<String, String>()));
                lContacts.add(new Contact(3, new HashMap<String, String>()));
                // TODO: query phone contacts in db.
                return lContacts;
            }

            @Override
            protected void onPostExecute(List<Contact> pContacts) {
                setValue(pContacts);
            }
        }.execute();
    }
}
