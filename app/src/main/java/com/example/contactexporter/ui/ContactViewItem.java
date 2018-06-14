package com.example.contactexporter.ui;

import com.example.contactexporter.data.Contact;

/**
 * Created by Cillian Myles on 14/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactViewItem extends ViewItem {

    private Contact contact;

    public ContactViewItem(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public int getItemViewType() {
        return TYPE_CONTACT;
    }
}
