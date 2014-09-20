package com.testexercise.bookcatalog.events.book;

import com.testexercise.bookcatalog.domain.Book;
import com.testexercise.bookcatalog.events.UpdateEvent;

public class UpdateBookEvent extends UpdateEvent {
    private long id;
    private Book book;

    public UpdateBookEvent(long id, Book book) {
        this.id = id;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }
}
