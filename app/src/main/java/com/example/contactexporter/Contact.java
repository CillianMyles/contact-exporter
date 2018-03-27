package com.example.contactexporter;

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
