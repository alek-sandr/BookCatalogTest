package com.testexercise.bookcatalog.events.author;

import com.testexercise.bookcatalog.events.RequestReadEvent;

public class RequestAuthorEvent extends RequestReadEvent {
    private long id;

    public RequestAuthorEvent(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
