package com.example.contactexporter;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
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
}
