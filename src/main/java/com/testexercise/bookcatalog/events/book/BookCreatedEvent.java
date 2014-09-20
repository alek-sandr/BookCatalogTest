package com.testexercise.bookcatalog.events.book;

import com.testexercise.bookcatalog.domain.Book;
import com.testexercise.bookcatalog.events.CreatedEvent;

public class BookCreatedEvent extends CreatedEvent {
    private final long newBookId;
    private final Book book;

    public BookCreatedEvent(long newBookId, final Book book) {
        this.newBookId = newBookId;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public long getNewBookId() {
        return newBookId;
    }
}
