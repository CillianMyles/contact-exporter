package com.example.contactexporter.ui;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactexporter.R;
import com.example.contactexporter.data.Contact;
import com.squareup.picasso.Picasso;

/**
 * Created by Cillian Myles on 14/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactViewItemBinder extends ViewItemBinder<ContactViewItem> {

    private static final String TAG = ContactViewItemBinder.class.getSimpleName();

    private ImageView image;
    private TextView initials;
    private TextView name;
    private CheckBox selected;

    private ContactViewItemBinder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        initials = itemView.findViewById(R.id.initials);
        name = itemView.findViewById(R.id.name);
        selected = itemView.findViewById(R.id.selected);
    }

    @Override
    public void bind(int position, Context context) {
        final Resources resources = context.getResources();
        final Contact contact = getItem().getContact();
        final boolean isSelected = getItem().isSelected();
        final ContactSelectedListener listener = getItem().getListener();
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
        selected.setSelected(isSelected);
        selected.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (listener != null) {
                listener.contactSelected(position, contact.getId(), isChecked);
            }
        });
    }

    public static ViewItemBinder inflate(LayoutInflater inflater, ViewGroup parent) {
        return new ContactViewItemBinder(inflater.inflate(R.layout.contact_list_item, parent, false));
    }
}
