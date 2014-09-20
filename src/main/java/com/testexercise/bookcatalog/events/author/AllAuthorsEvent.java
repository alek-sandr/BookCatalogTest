package com.testexercise.bookcatalog.events.author;

import com.testexercise.bookcatalog.domain.Author;
import com.testexercise.bookcatalog.events.ReadEvent;

import java.util.List;

public class AllAuthorsEvent extends ReadEvent {
    private List<Author> authors;

    public AllAuthorsEvent(List<Author> authors) {
        this.authors = authors;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
