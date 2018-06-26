package com.example.contactexporter.ui;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.contactexporter.R;
import com.example.contactexporter.data.Contact;
import com.squareup.picasso.Picasso;

/**
 * Created by Cillian Myles on 26/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ProgressViewItemBinder extends ViewItemBinder<ProgressViewItem> {

    private static final String TAG = ProgressViewItemBinder.class.getSimpleName();

    private ImageView image;
    private TextView initials;
    private TextView name;
    private ProgressBar progress;
    private ImageView done;

    private ProgressViewItemBinder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        initials = itemView.findViewById(R.id.initials);
        name = itemView.findViewById(R.id.name);
        progress = itemView.findViewById(R.id.progress);
        done = itemView.findViewById(R.id.done);
    }

    @Override
    public void bind(int position, Context context) {
        final Resources resources = context.getResources();
        final Contact contact = getItem().getContact();
        final boolean isFinished = getItem().isFinished();
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
        // TODO: convert below to proper delay/feedback!!
//        progress.setVisibility(isFinished ? View.VISIBLE : View.INVISIBLE);
//        done.setVisibility(isFinished ? View.INVISIBLE : View.VISIBLE);
        if (!isFinished) {
            progress.postDelayed(() -> {
                getItem().setFinished(true);
                progress.setVisibility(View.INVISIBLE);
                done.setVisibility(View.VISIBLE);
            }, 2000);
        }
    }

    public static ViewItemBinder inflate(LayoutInflater inflater, ViewGroup parent) {
        return new ProgressViewItemBinder(inflater.inflate(R.layout.progress_list_item, parent, false));
    }
}
