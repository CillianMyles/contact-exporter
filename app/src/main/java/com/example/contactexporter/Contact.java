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
        String lName = "Unknown Name"; // TODO: !?
        if (mData == null || mData.isEmpty()) {
            return lName;
        }

        String lFirst = mData.get("first_name");
        String lLast = mData.get("last_name");

        String lTemp = "";
        if (!TextUtils.isEmpty(lFirst)) {
            lTemp += lFirst;
        }
        if (!TextUtils.isEmpty(lLast)) {
            lTemp += " " + lLast;
        }
        if (!TextUtils.isEmpty(lTemp)) {
            lName = lTemp;
        }

        return lName;
    }
}
