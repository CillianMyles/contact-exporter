package com.example.contactexporter.ui;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactexporter.R;
import com.example.contactexporter.data.Contact;
import com.squareup.picasso.Picasso;

/**
 * Created by Cillian Myles on 13/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = ContactViewHolder.class.getSimpleName();

    private ImageView image;
    private TextView initials;
    private TextView name;
    private CheckBox selected;

    public ContactViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        initials = itemView.findViewById(R.id.initials);
        name = itemView.findViewById(R.id.name);
        selected = itemView.findViewById(R.id.selected);
    }

    public void bind(@NonNull Resources resources, @NonNull final Contact contact) {
        image.setBackgroundColor(contact.backgroundColor(resources));
        if (!TextUtils.isEmpty(contact.photoUri())) {
            final int pixels = resources.getInteger(R.integer.contact_photo_pixels);
            Picasso.get()
                    .load(contact.photoUri())
                    .noPlaceholder()
                    .resize(pixels, pixels)
                    .into(image);
        }
        initials.setText(contact.initials());
        name.setText(contact.fullName());
        itemView.setOnClickListener(view -> selected.toggle());
        selected.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            Log.e(TAG, "id: " + contact.getId() + " - isChecked: " + isChecked); // TODO: remove
        });
    }
}
