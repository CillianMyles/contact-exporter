package com.example.contactexporter.ui;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.contactexporter.R;
import com.example.contactexporter.data.ContactsViewModel;
import com.turingtechnologies.materialscrollbar.AlphabetIndicator;
import com.turingtechnologies.materialscrollbar.MaterialScrollBar;

import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.support.design.widget.Snackbar.LENGTH_LONG;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class MainActivity extends AppCompatActivity implements ContactsAdapter.LetterChangedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_CODE_CONTACTS = 0;

    private View baseLayout;
    private TextView message;
    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    private TextView currentLetter;
    private MaterialScrollBar letterScrollBar;
    private ContactsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        initRecyclerView();
        checkPermissionAndLoad();
        handleIntent(getIntent());
    }

    private void bindViews() {
        baseLayout = findViewById(R.id.base_layout);
        message = findViewById(R.id.message);
        recyclerView = findViewById(R.id.recycler_view);
        currentLetter = findViewById(R.id.current_letter);
        letterScrollBar = findViewById(R.id.letter_scroll_bar);
    }

    private void initRecyclerView() {
        adapter = new ContactsAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        letterScrollBar.setIndicator(new AlphabetIndicator(this), false);
    }

    private void checkPermissionAndLoad() {
        // Check contacts permission.
        if (ActivityCompat.checkSelfPermission(this, READ_CONTACTS) == PERMISSION_GRANTED) {

            // Permission granted previously.
            Log.d(TAG, "Permission granted previously.");
            message.setText(R.string.no_results);

            // Query contacts.
            loadContacts();

        } else {

            // Permission is not granted.
            Log.d(TAG, "Permission is not granted.");
            message.setText(R.string.no_permission);

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

    private void loadContacts() {
        viewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        viewModel.getLiveData().observe(this, observer);
    }

    private Observer<List<ViewItem>> observer = new Observer<List<ViewItem>>() {
        @Override
        public void onChanged(@Nullable List<ViewItem> contacts) {
            boolean validResults = contacts != null && !contacts.isEmpty();
            recyclerView.setVisibility(validResults ? View.VISIBLE : View.GONE);
            currentLetter.setVisibility(validResults ? View.VISIBLE : View.GONE);
            letterScrollBar.setVisibility(validResults ? View.VISIBLE : View.GONE);
            message.setVisibility(validResults ? View.GONE : View.VISIBLE);
            if (validResults) {
                adapter.swap(contacts);
                for (ViewItem item : contacts) {
                    if (item instanceof ContactViewItem) {
                        currentLetter.setText(((ContactViewItem) item).getContact().letter());
                        break;
                    }
                }
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    // Contacts permission granted.
                    Log.d(TAG, "Contacts permission granted.");
                    message.setText(R.string.no_results);
                    Snackbar.make(baseLayout, R.string.permission_granted, LENGTH_LONG).show();

                    // Query contacts.
                    loadContacts();

                } else {
                    // Contacts permission denied.
                    Log.d(TAG, "Contacts permission denied.");
                    message.setText(R.string.no_permission);
                    Snackbar.make(baseLayout, R.string.permission_denied, LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    @Override
    public void showLetter(Character character) {
        currentLetter.setText(character != null ? character.toString() : null);
    }

    /*
     * Everything below here is related to search.
     */

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            search(intent.getStringExtra(SearchManager.QUERY));
        }
    }

    private int counter = 1; // TODO: remove

    private void search(String query) {
        if (TextUtils.isEmpty(query)) return;
        Log.e(TAG, "UI search count: " + counter++); // TODO: remove
        viewModel.search(query);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        initSearchInterface(menu);
        return true;
    }

    private void initSearchInterface(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();

        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setIconifiedByDefault(true);

        //noinspection deprecation
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Log.e(TAG, "Search menu CLOSED!"); // TODO: remove
                viewModel.reset();
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Log.e(TAG, "Search menu EXPANDED!"); // TODO: remove
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search: {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
