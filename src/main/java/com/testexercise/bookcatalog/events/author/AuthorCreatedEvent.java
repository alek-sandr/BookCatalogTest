package com.testexercise.bookcatalog.events.author;

import com.testexercise.bookcatalog.domain.Author;
import com.testexercise.bookcatalog.events.CreatedEvent;

public class AuthorCreatedEvent extends CreatedEvent {
    private final long newAuthorId;
    private final Author author;

    public AuthorCreatedEvent(long newAuthorId, final Author author) {
        this.newAuthorId = newAuthorId;
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public long getNewAuthorId() {
        return newAuthorId;
    }
}
