package com.example.contactexporter.ui.progress;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.contactexporter.R;
import com.example.contactexporter.data.Contact;
import com.example.contactexporter.ui.base.ViewItemBinder;
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
        Log.e(TAG, "position: " + position + " - isFinished: " + isFinished + " - contact: " + contact); // TODO: remove
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
        done.setVisibility(isFinished ? View.VISIBLE : View.INVISIBLE);
        progress.setVisibility(isFinished ? View.INVISIBLE : View.VISIBLE);
    }

    public static ViewItemBinder inflate(LayoutInflater inflater, ViewGroup parent) {
        return new ProgressViewItemBinder(inflater.inflate(R.layout.progress_list_item, parent, false));
    }
}
