package com.example.contactexporter.data.dummy;

import com.example.contactexporter.data.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
@SuppressWarnings("SpellCheckingInspection")
public class DummyData {

    public static Contact random() {
        double lRandom = Math.random();
        if (lRandom <= 0.33d) {
            return ELON;
        } else if (lRandom > 0.66d) {
            return DONALD;
        } else {
            return HILARY;
        }
    }

    public static List<Contact> list() {
        List<Contact> lContacts = new ArrayList<>();
        lContacts.add(ELON);
        lContacts.add(HILARY);
        lContacts.add(DONALD);
        return lContacts;
    }

    private static final Contact ELON;
    private static final int ID_ELON = 1;

    static {
        Map<String, String> lData = new HashMap<>();
        lData.put("first_name", "Elon");
        lData.put("last_name", "Musk");
        lData.put("company", "SpaceX");
        ELON = new Contact(ID_ELON, lData);
    }

    private static final Contact DONALD;
    private static final int ID_DONALD = 2;

    static {
        Map<String, String> lData = new HashMap<>();
        lData.put("first_name", "Donald");
        lData.put("last_name", "Trump");
        lData.put("company", "USA");
        DONALD = new Contact(ID_DONALD, lData);
    }

    private static final Contact HILARY;
    private static final int ID_HILARY = 3;

    static {
        Map<String, String> lData = new HashMap<>();
        lData.put("first_name", "Hilary");
        lData.put("last_name", "Clinton");
        lData.put("company", "Unknown");
        HILARY = new Contact(ID_HILARY, lData);
    }
}
