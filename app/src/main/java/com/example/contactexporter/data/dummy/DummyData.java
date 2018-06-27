package com.example.contactexporter.data.dummy;

import com.example.contactexporter.data.Contact;
import com.example.contactexporter.ui.selection.ContactViewItem;
import com.example.contactexporter.ui.progress.ProgressViewItem;
import com.example.contactexporter.ui.base.ViewItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
@SuppressWarnings({"SpellCheckingInspection", "unused", "UnusedAssignment"})
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

    public static List<Contact> contacts() {
        return Arrays.asList(ELON, DONALD, HILARY);
    }

    public static List<Contact> contactsLongList() {
        final List<Contact> list = new ArrayList<>();
        for (ViewItem item : contactItemsLongList()) {
            if (item instanceof ContactViewItem) {
                list.add(((ContactViewItem) item).getContact());
            }
        }
        return list;
    }

    public static List<ViewItem> contactItems() {
        return Arrays.asList(ELON_VIEW_ITEM, DONALD_VIEW_ITEM, HILARY_VIEW_ITEM);
    }

    public static List<ViewItem> contactItemsLongList() {
        long i = 1;
        return Arrays.asList(
                contact(i++, ELON), contact(i++, ELON), contact(i++, ELON), contact(i++, ELON), contact(i++, ELON),
                contact(i++, DONALD), contact(i++, DONALD), contact(i++, DONALD), contact(i++, DONALD), contact(i++, DONALD),
                contact(i++, HILARY), contact(i++, HILARY), contact(i++, HILARY), contact(i++, HILARY), contact(i++, HILARY)
        );
    }

    public static ViewItem contact(long id, Contact contact) {
        return new ContactViewItem(new Contact(id, contact));
    }

    public static ViewItem progress(ContactViewItem contactViewItem) {
        return new ProgressViewItem(new Contact(contactViewItem.getContactId(), contactViewItem.getContact()));
    }

    private static final int ID_ELON = 1;
    private static final Contact ELON;
    private static final ContactViewItem ELON_VIEW_ITEM;

    static {
        Map<String, String> data = new HashMap<>();
        data.put("first_name", "Elon");
        data.put("last_name", "Musk");
        data.put("company", "SpaceX");
        ELON = new Contact(ID_ELON, data);
        ELON_VIEW_ITEM = new ContactViewItem(ELON);
    }

    private static final int ID_DONALD = 2;
    private static final Contact DONALD;
    private static final ContactViewItem DONALD_VIEW_ITEM;

    static {
        Map<String, String> data = new HashMap<>();
        data.put("first_name", "Donald");
        data.put("last_name", "Trump");
        data.put("company", "USA");
        DONALD = new Contact(ID_DONALD, data);
        DONALD_VIEW_ITEM = new ContactViewItem(DONALD);
    }

    private static final int ID_HILARY = 3;
    private static final Contact HILARY;
    private static final ContactViewItem HILARY_VIEW_ITEM;

    static {
        Map<String, String> data = new HashMap<>();
        data.put("first_name", "Hilary");
        data.put("last_name", "Clinton");
        data.put("company", "Unknown");
        HILARY = new Contact(ID_HILARY, data);
        HILARY_VIEW_ITEM = new ContactViewItem(HILARY);
    }
}
