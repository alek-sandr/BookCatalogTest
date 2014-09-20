package com.testexercise.bookcatalog.events.author;

import com.testexercise.bookcatalog.domain.Author;
import com.testexercise.bookcatalog.events.ReadEvent;

public class AuthorEvent extends ReadEvent {
    private long id;
    private Author author;

    private AuthorEvent() {}

    public AuthorEvent(long id, Author author) {
        this.id = id;
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public long getId() {
        return id;
    }

    public static AuthorEvent notFound(long id) {
        AuthorEvent ev = new AuthorEvent();
        ev.id = id;
        ev.entityFound = false;
        return ev;
    }
}
