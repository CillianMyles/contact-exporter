package com.example.contactexporter.ui.progress;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.contactexporter.ui.base.ViewItem;
import com.example.contactexporter.ui.base.ViewItemAdapter;
import com.example.contactexporter.ui.base.ViewItemBinder;

/**
 * Created by Cillian Myles on 26/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ProgressAdapter extends ViewItemAdapter {

    ProgressAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewItemBinder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ViewItem.TYPE_PROGRESS: {
                return ProgressViewItemBinder.inflate(getInflater(), parent);
            }
        }
        throw new IllegalStateException("View type not supported.");
    }
}
