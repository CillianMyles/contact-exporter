package com.example.contactexporter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.contactexporter.data.Contact;
import com.example.contactexporter.data.ContactsViewModel;

import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.support.design.widget.Snackbar.LENGTH_LONG;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_CODE_CONTACTS = 0;

    private ConstraintLayout mBaseLayout;
    private RecyclerView mRecyclerView;
    private TextView mMessage;
    private ContactsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind views.
        mBaseLayout = findViewById(R.id.base_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
        mMessage = findViewById(R.id.message);

        // Initialise RV stuff.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ContactsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        // Check contacts permission.
        if (ActivityCompat.checkSelfPermission(this, READ_CONTACTS) == PERMISSION_GRANTED) {

            // Permission granted previously.
            Log.d(TAG, "Permission granted previously.");
            mMessage.setText(R.string.no_results);

            // Query contacts.
            loadContacts();

        } else {

            // Permission is not granted.
            Log.d(TAG, "Permission is not granted.");
            mMessage.setText(R.string.no_permission);

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, READ_CONTACTS)) {
                Log.d(TAG, "We should show an explanation/rationale.");
            }

            // No explanation needed; request the permission.
            else {
                Log.d(TAG, "No explanation needed; request the permission.");
                ActivityCompat.requestPermissions(this, new String[]{READ_CONTACTS}, REQUEST_CODE_CONTACTS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    // Contacts permission granted.
                    Log.d(TAG, "Contacts permission granted.");
                    mMessage.setText(R.string.no_results);
                    Snackbar.make(mBaseLayout, R.string.permission_granted, LENGTH_LONG).show();

                    // Query contacts.
                    loadContacts();

                } else {
                    // Contacts permission denied.
                    Log.d(TAG, "Contacts permission denied.");
                    mMessage.setText(R.string.no_permission);
                    Snackbar.make(mBaseLayout, R.string.permission_denied, LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    private void loadContacts() {
        ContactsViewModel lModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        lModel.getData().observe(this, mObserver);
    }

    private Observer<List<Contact>> mObserver = new Observer<List<Contact>>() {
        @Override
        public void onChanged(@Nullable List<Contact> pContacts) {
            boolean lValidResults = pContacts != null && !pContacts.isEmpty();
            mRecyclerView.setVisibility(lValidResults ? View.VISIBLE : View.GONE);
            mMessage.setVisibility(lValidResults ? View.GONE : View.VISIBLE);
            if (lValidResults) {
                mAdapter.swap(pContacts);
            }
        }
    };
}
