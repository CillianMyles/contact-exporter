package com.example.contactexporter.data;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.contactexporter.util.PhotoHelper;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class Contact implements Serializable {

    public static final String LETTER_NONE = "#";

    private long id; // TODO: change to final
    private final Map<String, String> data;

    public Contact(long id, Map<String, String> data) {
        this.id = id;
        this.data = data;
    }

    public void setId(long id) { // TODO: remove method when id final
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Map<String, String> getData() {
        return data;
    }

    public String fullName() {
        if (data == null || data.isEmpty()) {
            return "";
        }

        String first = firstName();
        String last = lastName();

        if (!TextUtils.isEmpty(first) && !TextUtils.isEmpty(last)) {
            return first + " " + last;
        } else if (!TextUtils.isEmpty(last)) {
            return last;
        } else {
            return first;
        }
    }

    public String initials() {
        if (data == null || data.isEmpty()) {
            return "";
        }

        String first = firstName();
        String last = lastName();

        final String initials;
        if (!TextUtils.isEmpty(first) && !TextUtils.isEmpty(last)) {
            initials = first.substring(0, 1) + last.substring(0, 1);
        } else if (!TextUtils.isEmpty(last)) {
            initials = last.substring(0, 1);
        } else {
            initials = first.substring(0, 1);
        }

        return initials;
    }

    public int backgroundColor(@NonNull Resources resources) {
        return PhotoHelper.backgroundColor(resources, identifier());
    }

    public String photoUri() {
        return getValue("photo_url");
    }

    public String firstName() {
        return getValue("first_name");
    }

    public String lastName() {
        return getValue("last_name");
    }

    public String letter() {
        final String initials = initials();
        return initials.length() >= 1 ? initials.substring(0, 1) : LETTER_NONE;
    }

    private String identifier() {
        return fullName();
    }

    private String getValue(String key) {
        if (data == null || data.isEmpty() || TextUtils.isEmpty(key)) {
            return "";
        }

        final String value = data.get(key);
        return !TextUtils.isEmpty(value) ? value : "";
    }
}
