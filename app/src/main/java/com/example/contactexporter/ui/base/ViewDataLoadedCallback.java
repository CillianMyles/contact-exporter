package com.example.contactexporter.ui.base;

import java.util.List;

/**
 * Created by Cillian Myles on 28/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public interface ViewDataLoadedCallback {

    void onLoaded(List<ViewItem> data);

    void onError(String message);
}
