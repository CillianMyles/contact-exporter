package com.example.contactexporter.data.dummy;

import com.example.contactexporter.data.Contact;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
@SuppressWarnings({"SpellCheckingInspection", "unused"})
public class DummyData {

    public static Contact random() {
        double random = Math.random();
        if (random <= 0.33d) {
            return ELON;
        } else if (random > 0.66d) {
            return DONALD;
        } else {
            return HILARY;
        }
    }

    public static List<Contact> list() {
        return Arrays.asList(ELON, DONALD, HILARY);
    }

    private static final Contact ELON;
    private static final int ID_ELON = 1;

    static {
        Map<String, String> data = new HashMap<>();
        data.put("first_name", "Elon");
        data.put("last_name", "Musk");
        data.put("company", "SpaceX");
        ELON = new Contact(ID_ELON, data);
    }

    private static final Contact DONALD;
    private static final int ID_DONALD = 2;

    static {
        Map<String, String> data = new HashMap<>();
        data.put("first_name", "Donald");
        data.put("last_name", "Trump");
        data.put("company", "USA");
        DONALD = new Contact(ID_DONALD, data);
    }

    private static final Contact HILARY;
    private static final int ID_HILARY = 3;

    static {
        Map<String, String> data = new HashMap<>();
        data.put("first_name", "Hilary");
        data.put("last_name", "Clinton");
        data.put("company", "Unknown");
        HILARY = new Contact(ID_HILARY, data);
    }
}
