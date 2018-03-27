package com.example.contactexporter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import static android.Manifest.permission.READ_CONTACTS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.support.design.widget.Snackbar.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_CODE_CONTACTS = 0;

    private ConstraintLayout mBaseLayout;
    private RecyclerView mRecyclerView;
    private TextView mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind/initialise views.
        mBaseLayout = findViewById(R.id.base_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
        mMessage = findViewById(R.id.message);

        // Check contacts permission.
        if (ActivityCompat.checkSelfPermission(this, READ_CONTACTS) == PERMISSION_GRANTED) {

            Log.e(TAG, "Permission granted previously."); // TODO: remove

            // Permission granted previously.
            mMessage.setText(R.string.no_results);

            // TODO: query contacts

        } else {

            Log.e(TAG, "Permission is not granted."); // TODO: remove

            // Permission is not granted.
            mMessage.setText(R.string.no_permission);

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, READ_CONTACTS)) {
                //Snackbar.make(mBaseLayout, R.string.permission_denied, LENGTH_LONG).show();
                Log.e(TAG, "We should show an explanation/rationale."); // TODO: remove
            }

            // No explanation needed; request the permission.
            else {
                ActivityCompat.requestPermissions(this, new String[]{READ_CONTACTS}, REQUEST_CODE_CONTACTS);
                Log.e(TAG, "No explanation needed; request the permission."); // TODO: remove
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
                    Log.e(TAG, "Contacts permission granted."); // TODO: remove
                    mMessage.setText(R.string.no_results);
                    Snackbar.make(mBaseLayout, R.string.permission_granted, LENGTH_LONG).show();
                    // TODO: query contacts
                } else {
                    // Contacts permission denied.
                    Log.e(TAG, "Contacts permission denied."); // TODO: remove
                    mMessage.setText(R.string.no_permission);
                    Snackbar.make(mBaseLayout, R.string.permission_denied, LENGTH_LONG).show();
                }
                break;
            }
        }
    }
}
