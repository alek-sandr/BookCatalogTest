package com.testexercise.bookcatalog.events.book;

import com.testexercise.bookcatalog.events.DeleteEvent;

public class DeleteBookEvent extends DeleteEvent {
    private final long id;

    public DeleteBookEvent(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
