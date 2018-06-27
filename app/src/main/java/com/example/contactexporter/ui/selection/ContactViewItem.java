package com.example.contactexporter.ui.selection;

import com.example.contactexporter.data.Contact;
import com.example.contactexporter.ui.base.ViewItem;

/**
 * Created by Cillian Myles on 14/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactViewItem extends ViewItem {

    private final Contact contact;
    private boolean isSelected;
    private ContactSelectedListener listener;

    public ContactViewItem(Contact contact, boolean isSelected, ContactSelectedListener listener) {
        this.contact = contact;
        this.isSelected = isSelected;
        this.listener = listener;
    }

    public ContactViewItem(Contact contact) {
        this(contact, false, null);
    }

    public Contact getContact() {
        return contact;
    }

    public long getContactId() {
        return contact != null ? contact.getId() : -1;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public ContactViewItem setSelected(boolean selected) {
        isSelected = selected;
        return this;
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
