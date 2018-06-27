package com.example.contactexporter.ui.selection;

/**
 * Created by Cillian Myles on 21/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public interface ContactSelectedListener {

    void contactSelected(long adapterPosition, long contactId, boolean isChecked);
}
