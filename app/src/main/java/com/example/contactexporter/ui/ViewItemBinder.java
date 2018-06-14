package com.example.contactexporter.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Cillian Myles on 14/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public abstract class ViewItemBinder<T extends ViewItem> extends RecyclerView.ViewHolder {

    private T item;

    ViewItemBinder(View itemView) {
        super(itemView);
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    abstract void bind(int position, Context context);
}
