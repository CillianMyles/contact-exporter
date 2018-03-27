package com.example.contactexporter.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactsLiveData extends LiveData<List<Contact>> {

    private final Context mContext;

    public ContactsLiveData(Context pContext) {
        mContext = pContext;
        loadData();
    }

    private void loadData() {
        // TODO: pass search into task/query !?
        new LoadTask().execute(); // TODO: LoadTask to be static or singleton !?
    }

    private class LoadTask extends AsyncTask<Void, Void, List<Contact>> {

        @Override
        protected List<Contact> doInBackground(Void... pVoids) {
            // TODO: query phone contacts in db.
            return DummyData.list();
        }

        @Override
        protected void onPostExecute(List<Contact> pContacts) {
            setValue(pContacts);
        }
    }
}
