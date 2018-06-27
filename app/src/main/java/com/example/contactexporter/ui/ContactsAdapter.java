package com.example.contactexporter.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.contactexporter.data.Contact;
import com.example.contactexporter.ui.base.ViewItem;
import com.example.contactexporter.ui.base.ViewItemBinder;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

import java.util.List;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ViewItemBinder> implements INameableAdapter {

    private final Context context;
    private final LayoutInflater inflater;

    private final LetterChangedListener letterListener;
    private final ContactSelectedListener contactListener;

    private List<ViewItem> data;

    ContactsAdapter(Context context, LetterChangedListener letterListener, ContactSelectedListener contactListener) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.letterListener = letterListener;
        this.contactListener = contactListener;
    }

    @NonNull
    @Override
    public ViewItemBinder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ViewItem.TYPE_CONTACT: {
                return ContactViewItemBinder.inflate(inflater, parent);
            }
            case ViewItem.TYPE_PROGRESS: { // TODO: remove this!!!
                return ProgressViewItemBinder.inflate(inflater, parent);
            }
        }
        throw new IllegalStateException("View type not supported.");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(@NonNull ViewItemBinder holder, int position) {
        holder.setItem(getItem(position));
        holder.bind(position, context);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        ViewItem item = getItem(position);
        return item.getItemViewType();
    }

    private ViewItem getItem(int position) {
        ViewItem item = data.get(position);
        if (item instanceof ContactViewItem) {
            ((ContactViewItem) item).setListener(contactListener);
        }
        return item;
    }

    @SuppressWarnings("UnusedReturnValue")
    public List<ViewItem> swap(List<ViewItem> newList) {
        List<ViewItem> oldList = data;
        // Reset all the class members.
        data = newList;
        // Update the UI.
        if (newList != null) {
            // Notify observers about the new contacts items.
            notifyDataSetChanged();
        } else {
            // Notify the observers about the lack of a data set.
            // notifyDataSetInvalidated();
            notifyItemRangeRemoved(0, getItemCount());
        }
        return oldList;
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
