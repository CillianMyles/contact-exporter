package com.example.contactexporter.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.contactexporter.data.Contact;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

import java.util.List;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ViewItemBinder> implements INameableAdapter {

    interface LetterChangedListener {
        void showLetter(Character character);
    }

    private final Context context;
    private final LayoutInflater inflater;
    private final LetterChangedListener listener;
    private List<ViewItem> data;

    ContactsAdapter(Context context, LetterChangedListener listener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewItemBinder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ViewItem.TYPE_CONTACT: {
                return ContactViewItemBinder.inflate(inflater, parent);
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
        return data.get(position);
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
                final Character character = contact != null ? contact.letter().charAt(0) : '#';
                if (listener != null) {
                    listener.showLetter(character);
                }
                return character;
            }
        }
        throw new IllegalStateException("View type not supported.");
    }
}
