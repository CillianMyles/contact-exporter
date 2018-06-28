package com.example.contactexporter.data;

import java.util.List;

/**
 * Created by Cillian Myles on 28/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public interface ContactDataLoadedCallback {

    void onLoaded(List<ContactDataModel> contacts);

    void onError(String message);
}
