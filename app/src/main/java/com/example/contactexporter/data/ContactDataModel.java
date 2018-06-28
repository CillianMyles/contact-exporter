package com.example.contactexporter.data;

/**
 * Created by Cillian Myles on 28/06/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */
public class ContactDataModel {

    private final Contact contact;
    private State state;

    public ContactDataModel(Contact contact) {
        this.contact = contact;
        this.state = State.UNSELECTED;
    }

    public Contact getContact() {
        return contact;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
