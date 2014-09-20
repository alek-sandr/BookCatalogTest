package com.testexercise.bookcatalog.events.book;

import com.testexercise.bookcatalog.domain.Book;
import com.testexercise.bookcatalog.events.ReadEvent;

public class BookEvent extends ReadEvent {
    private long id;
    private Book book;

    private BookEvent() {}

    public BookEvent(long id, Book book) {
        this.id = id;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public static BookEvent notFound(long id) {
        BookEvent be = new BookEvent();
        be.id = id;
        be.entityFound = false;
        return be;
    }
}
