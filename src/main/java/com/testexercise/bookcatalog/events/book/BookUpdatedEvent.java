package com.testexercise.bookcatalog.events.book;

import com.testexercise.bookcatalog.domain.Book;
import com.testexercise.bookcatalog.events.UpdatedEvent;

public class BookUpdatedEvent extends UpdatedEvent {
    private long id;
    private Book book;

    public BookUpdatedEvent(long id, Book book) {
        this.id = id;
        this.book = book;
    }

    public BookUpdatedEvent(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public static BookUpdatedEvent notFound(long id) {
        BookUpdatedEvent ev = new BookUpdatedEvent(id);
        ev.entityFound = false;
        return ev;
    }
}
