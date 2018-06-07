package com.example.contactexporter.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.example.contactexporter.data.dummy.DummyData;

import java.util.List;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactsLiveData extends LiveData<List<Contact>> {

    private final Context context;

    public ContactsLiveData(Context context) {
        this.context = context;
        loadData();
    }

    private void loadData() {
        // TODO: pass search into task/query !?
        new LoadTask().execute(); // TODO: LoadTask to be static or singleton !?
    }

    private class LoadTask extends AsyncTask<String, Void, List<Contact>> {

        @Override
        protected List<Contact> doInBackground(String... searchArray) {
            //final String search = searchArray[0];
            // TODO: query phone contacts in db.
            return DummyData.list();
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {
            setValue(contacts);
        }
    }
}
