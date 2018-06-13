package com.example.contactexporter.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactexporter.R;
import com.example.contactexporter.data.Contact;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private static final int CONTACT_ITEM = R.layout.contact_list_item;

    private final Context context;
    private final LayoutInflater inflater;
    private List<Contact> data;

    public ContactsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(inflater.inflate(CONTACT_ITEM, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public List<Contact> swap(List<Contact> newList) {
        List<Contact> oldList = data;
        // Reset all the class members.
        data = newList;
        // Update the UI.
        if (newList != null) {
            // Notify observers about the new list items.
            notifyDataSetChanged();
        } else {
            // Notify the observers about the lack of a data set.
            // notifyDataSetInvalidated();
            notifyItemRangeRemoved(0, getItemCount());
        }
        return oldList;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView initials;
        private TextView name;

        ContactViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            initials = itemView.findViewById(R.id.initials);
            name = itemView.findViewById(R.id.name);
        }

        private void bind(Contact contact) {
            image.setBackgroundColor(contact.backgroundColor(context));
            if (!TextUtils.isEmpty(contact.photoUri())) {
                final int pixels = context.getResources().getInteger(R.integer.contact_photo_pixels);
                Picasso.get()
                        .load(contact.photoUri())
                        .noPlaceholder()
                        .resize(pixels, pixels)
                        .into(image);
            }
            initials.setText(contact.initials());
            name.setText(contact.fullName());
        }
    }
}
