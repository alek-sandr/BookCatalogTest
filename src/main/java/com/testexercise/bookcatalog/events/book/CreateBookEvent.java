package com.testexercise.bookcatalog.events.book;

import com.testexercise.bookcatalog.domain.Book;
import com.testexercise.bookcatalog.events.CreateEvent;

public class CreateBookEvent extends CreateEvent {
    private Book book;

    public CreateBookEvent(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
