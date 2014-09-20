package com.testexercise.bookcatalog.events.book;

import com.testexercise.bookcatalog.domain.Book;
import com.testexercise.bookcatalog.events.ReadEvent;

import java.util.List;

public class BooksEvent extends ReadEvent {
    private List<Book> books;

    public BooksEvent(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
