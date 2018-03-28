package com.example.contactexporter.data;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cillian Myles on 28/03/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */
public class ContactsLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<Contact>> {

    private final List<Contact> EMPTY_LIST = new ArrayList<>();

    private final Context mContext;

    public ContactsLoaderCallbacks(Context pContext) {
        mContext = pContext;
    }

    @Override
    public Loader<List<Contact>> onCreateLoader(int id, Bundle args) {
        return new ContactsTaskLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<List<Contact>> loader, List<Contact> data) {
        //setValue(data);
        // TODO: send 'data'
    }

    @Override
    public void onLoaderReset(Loader<List<Contact>> loader) {
        //setValue(EMPTY_LIST);
        // TODO: send 'EMPTY_LIST'
    }
}
