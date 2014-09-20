package com.testexercise.bookcatalog.events.author;

import com.testexercise.bookcatalog.domain.Author;
import com.testexercise.bookcatalog.events.CreateEvent;

public class CreateAuthorEvent extends CreateEvent {
    private Author author;

    public CreateAuthorEvent(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }
}