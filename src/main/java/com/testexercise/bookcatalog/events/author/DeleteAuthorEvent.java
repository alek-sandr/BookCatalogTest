package com.testexercise.bookcatalog.events.author;

import com.testexercise.bookcatalog.events.DeleteEvent;

public class DeleteAuthorEvent extends DeleteEvent {
    private final long id;

    public DeleteAuthorEvent(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
