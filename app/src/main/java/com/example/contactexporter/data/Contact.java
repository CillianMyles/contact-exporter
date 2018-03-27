package com.example.contactexporter.data;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class Contact implements Serializable {

    private final long mId;
    private final Map<String, String> mData;

    public Contact(long pId, Map<String, String> pData) {
        mId = pId;
        mData = pData;
    }

    public long getId() {
        return mId;
    }

    public Map<String, String> getData() {
        return mData;
    }

    public String fullName() {
        if (mData == null || mData.isEmpty()) {
            return "";
        }

        String lFirst = firstName();
        String lLast = lastName();

        if (!TextUtils.isEmpty(lFirst) && !TextUtils.isEmpty(lLast)) {
            return lFirst + " " + lLast;
        } else if (!TextUtils.isEmpty(lLast)) {
            return lLast;
        } else {
            return lFirst;
        }
    }

    public String initials() {
        if (mData == null || mData.isEmpty()) {
            return "";
        }

        String lFirst = firstName();
        String lLast = lastName();

        final String lInitials;
        if (!TextUtils.isEmpty(lFirst) && !TextUtils.isEmpty(lLast)) {
            lInitials = lFirst.substring(0, 1) + lLast.substring(0, 1);
        } else if (!TextUtils.isEmpty(lLast)) {
            lInitials = lLast.substring(0, 1);
        } else {
            lInitials = lFirst.substring(0, 1);
        }

        return lInitials;
    }

    private String firstName() {
        return getValue("first_name");
    }

    private String lastName() {
        return getValue("last_name");
    }

    private String getValue(String pKey) {
        if (mData == null || mData.isEmpty() || TextUtils.isEmpty(pKey)) {
            return "";
        }

        final String lValue = mData.get(pKey);
        return !TextUtils.isEmpty(lValue) ? lValue : "";
    }
}
