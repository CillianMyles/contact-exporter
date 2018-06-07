package com.example.contactexporter.data;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.contactexporter.data.dummy.DummyData;

import java.util.List;

/**
 * Created by Cillian Myles on 28/03/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */
public class ContactsTaskLoader extends AsyncTaskLoader<List<Contact>> {

    public ContactsTaskLoader(Context pContext) {
        super(pContext);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Contact> loadInBackground() {
        // TODO: query phone contacts in db.
        return DummyData.list();
    }
}
