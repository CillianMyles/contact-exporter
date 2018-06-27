package com.example.contactexporter.ui;

import com.example.contactexporter.data.Contact;
import com.example.contactexporter.ui.base.ViewItem;

/**
 * Created by Cillian Myles on 26/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ProgressViewItem extends ViewItem {

    private final Contact contact;
    private boolean isFinished;

    public ProgressViewItem(Contact contact, boolean isFinished) {
        this.contact = contact;
        this.isFinished = isFinished;
    }

    public ProgressViewItem(Contact contact) {
        this(contact, false);
    }

    public Contact getContact() {
        return contact;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public ProgressViewItem setFinished(boolean finished) {
        isFinished = finished;
        return this;
    }

    @Override
    public int getItemViewType() {
        return TYPE_PROGRESS;
    }
}
