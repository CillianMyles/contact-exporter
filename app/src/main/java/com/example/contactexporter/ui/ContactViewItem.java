package com.example.contactexporter.ui;

import com.example.contactexporter.data.Contact;

/**
 * Created by Cillian Myles on 14/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactViewItem extends ViewItem {

    private final Contact contact;
    private ContactSelectedListener listener;

    public ContactViewItem(Contact contact, ContactSelectedListener listener) {
        this.contact = contact;
        this.listener = listener;
    }

    public ContactViewItem(Contact contact) {
        this(contact, null);
    }

    public Contact getContact() {
        return contact;
    }

    public ContactViewItem setListener(ContactSelectedListener listener) {
        this.listener = listener;
        return this;
    }

    public ContactSelectedListener getListener() {
        return listener;
    }

    @Override
    public int getItemViewType() {
        return TYPE_CONTACT;
    }
}
