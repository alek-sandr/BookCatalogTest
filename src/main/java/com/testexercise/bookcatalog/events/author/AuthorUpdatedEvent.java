package com.testexercise.bookcatalog.events.author;

import com.testexercise.bookcatalog.domain.Author;
import com.testexercise.bookcatalog.events.UpdatedEvent;

public class AuthorUpdatedEvent extends UpdatedEvent {
    private long id;
    private Author author;

    public AuthorUpdatedEvent(long id, Author Author) {
        this.id = id;
        this.author = Author;
    }

    public AuthorUpdatedEvent(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public static AuthorUpdatedEvent notFound(long id) {
        AuthorUpdatedEvent ev = new AuthorUpdatedEvent(id);
        ev.entityFound = false;
        return ev;
    }
}
