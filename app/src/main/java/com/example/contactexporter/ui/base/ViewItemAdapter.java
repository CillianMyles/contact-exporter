package com.example.contactexporter.ui.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by Cillian Myles on 27/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
@SuppressWarnings("WeakerAccess")
public abstract class ViewItemAdapter extends RecyclerView.Adapter<ViewItemBinder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<ViewItem> data;

    public ViewItemAdapter(Context context, List<ViewItem> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public ViewItemAdapter(Context context) {
        this(context, null);
    }

    public Context getContext() {
        return context;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public List<ViewItem> getData() {
        return data;
    }

    public ViewItem getItem(int position) {
        return data.get(position);
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

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(@NonNull ViewItemBinder holder, int position) {
        holder.setItem(getItem(position));
        holder.bind(position, getContext());
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
}
