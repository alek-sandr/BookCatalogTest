package com.testexercise.bookcatalog.events.book;

import com.testexercise.bookcatalog.events.RequestReadEvent;

public class RequestBookEvent extends RequestReadEvent {
    private long id;

    public RequestBookEvent(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
