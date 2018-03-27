package com.example.contactexporter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cillian Myles on 27/03/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private static final int CONTACT_ITEM = R.layout.contact_list_item;

    private final Context mContext;
    private final LayoutInflater mInflater;
    private List<Contact> mData;

    public ContactsAdapter(Context pContext) {
        mContext = pContext;
        mInflater = LayoutInflater.from(pContext);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(mInflater.inflate(CONTACT_ITEM, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public List<Contact> swap(List<Contact> pNewList) {
        List<Contact> lOldList = mData;
        // Reset all the class members.
        mData = pNewList;
        // Update the UI.
        if (pNewList != null) {
            // Notify observers about the new list items.
            notifyDataSetChanged();
        } else {
            // Notify the observers about the lack of a data set.
            // notifyDataSetInvalidated();
            notifyItemRangeRemoved(0, getItemCount());
        }
        return lOldList;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mInitials;
        private TextView mName;

        public ContactViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mInitials = itemView.findViewById(R.id.initials);
            mName = itemView.findViewById(R.id.name);
        }

        private void bind(Contact pContact) {
            // TODO: image
            mInitials.setText(pContact.initials());
            mName.setText(pContact.fullName());
        }
    }
}
