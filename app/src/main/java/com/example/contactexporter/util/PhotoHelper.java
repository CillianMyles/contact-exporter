package com.example.contactexporter.util;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.contactexporter.R;

/**
 * Created by Cillian Myles on 13/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class PhotoHelper {

    public static int backgroundColor(@NonNull Resources resources, String identifier) {
        final int defaultColor = resources.getColor(R.color.contact_letter_tile_default);
        if (TextUtils.isEmpty(identifier)) return defaultColor;
        final TypedArray colors = resources.obtainTypedArray(R.array.contact_letter_tile_colors);
        final int index = Math.abs(identifier.hashCode()) % colors.length();
        final int color = colors.getColor(index, defaultColor);
        colors.recycle();
        return color;
    }
}
