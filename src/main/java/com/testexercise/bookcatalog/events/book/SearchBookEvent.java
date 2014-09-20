package com.testexercise.bookcatalog.events.book;

import com.testexercise.bookcatalog.events.RequestReadEvent;

public class SearchBookEvent extends RequestReadEvent {
    private final String query;

    public SearchBookEvent(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
