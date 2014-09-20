package com.testexercise.bookcatalog.events.author;

import com.testexercise.bookcatalog.domain.Author;
import com.testexercise.bookcatalog.events.DeletedEvent;

public class AuthorDeletedEvent extends DeletedEvent {
    private long id;
    private Author author;
    private boolean deletionCompleted;

    private AuthorDeletedEvent(long id) {
        this.id = id;
    }

    public AuthorDeletedEvent(long id, Author author) {
        this.id = id;
        this.author = author;
        this.deletionCompleted = true;
    }

    public long getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public boolean isDeletionCompleted() {
        return deletionCompleted;
    }

    public static AuthorDeletedEvent deletionForbidden(long id, Author author) {
        AuthorDeletedEvent ev = new AuthorDeletedEvent(id, author);
        ev.entityFound = true;
        ev.deletionCompleted = false;
        return ev;
    }

    public static AuthorDeletedEvent notFound(long id) {
        AuthorDeletedEvent ev = new AuthorDeletedEvent(id);
        ev.entityFound = false;
        return ev;
    }
}
