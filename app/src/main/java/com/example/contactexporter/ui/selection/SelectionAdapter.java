package com.example.contactexporter.ui.selection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.contactexporter.data.Contact;
import com.example.contactexporter.ui.base.ViewItem;
import com.example.contactexporter.ui.base.ViewItemAdapter;
import com.example.contactexporter.ui.base.ViewItemBinder;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class SelectionAdapter extends ViewItemAdapter implements INameableAdapter {

    private final LetterChangedListener letterListener;
    private final ContactSelectedListener contactListener;

    SelectionAdapter(Context context, LetterChangedListener letterListener, ContactSelectedListener contactListener) {
        super(context);
        this.letterListener = letterListener;
        this.contactListener = contactListener;
    }

    @NonNull
    @Override
    public ViewItemBinder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ViewItem.TYPE_CONTACT: {
                return ContactViewItemBinder.inflate(getInflater(), parent);
            }
        }
        throw new IllegalStateException("View type not supported.");
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
        }
        throw new IllegalStateException("View type not supported.");
    }
}
