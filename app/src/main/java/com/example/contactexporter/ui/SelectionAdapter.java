package com.example.contactexporter.ui;

import android.content.Context;

import com.example.contactexporter.data.Contact;
import com.example.contactexporter.ui.base.ViewItem;
import com.example.contactexporter.ui.base.ViewItemAdapter;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class SelectionAdapter extends ViewItemAdapter implements INameableAdapter {

    private final LetterChangedListener letterListener;
    private final ContactSelectedListener contactListener;

    public SelectionAdapter(Context context, LetterChangedListener letterListener, ContactSelectedListener contactListener) {
        super(context);
        this.letterListener = letterListener;
        this.contactListener = contactListener;
    }

    @Override
    public ViewItem getItem(int position) {
        ViewItem item = getData().get(position);
        if (item instanceof ContactViewItem) {
            ((ContactViewItem) item).setListener(contactListener);
        }
        return item;
    }

    @Override
    public Character getCharacterForElement(int position) {
        switch (getItemViewType(position)) {
            case ViewItem.TYPE_CONTACT: {
                final ContactViewItem contactViewItem = (ContactViewItem) getItem(position);
                final Contact contact = contactViewItem.getContact();
                final Character character = contact != null ? contact.character() : Contact.CHAR_NONE;
                if (letterListener != null) {
                    letterListener.letterChanged(character);
                }
                return character;
            }
            case ViewItem.TYPE_PROGRESS: {
                return Contact.CHAR_NONE; // TODO: remove!?
            }
        }
        throw new IllegalStateException("View type not supported.");
    }
}
