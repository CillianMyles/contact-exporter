package com.example.contactexporter.ui.base;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Cillian Myles on 14/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public abstract class ViewItem implements Serializable, Comparable<ViewItem> {

    public static final int TYPE_CONTACT = 1;
    public static final int TYPE_PROGRESS = 2;

    public abstract int getItemViewType();

    @Override
    public int compareTo(@NonNull ViewItem another) {
        return 0;
    }
}
