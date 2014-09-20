package com.testexercise.bookcatalog.events.author;

import com.testexercise.bookcatalog.domain.Author;
import com.testexercise.bookcatalog.events.UpdateEvent;

public class UpdateAuthorEvent extends UpdateEvent {
    private long id;
    private Author author;

    public UpdateAuthorEvent(long id, Author author) {
        this.id = id;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }
}
