package com.testexercise.bookcatalog.events.book;

import com.testexercise.bookcatalog.domain.Book;
import com.testexercise.bookcatalog.events.DeletedEvent;

public class BookDeletedEvent extends DeletedEvent {
    private long id;
    private Book book;
    private boolean deletionCompleted;

    private BookDeletedEvent(long id) {
        this.id = id;
    }

    public BookDeletedEvent(long id, Book book) {
        this.id = id;
        this.book = book;
        this.deletionCompleted = true;
    }

    public long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public boolean isDeletionCompleted() {
        return deletionCompleted;
    }

    public static BookDeletedEvent deletionForbidden(long id, Book book) {
        BookDeletedEvent ev = new BookDeletedEvent(id, book);
        ev.entityFound = true;
        ev.deletionCompleted = false;
        return ev;
    }

    public static BookDeletedEvent notFound(long id) {
        BookDeletedEvent ev = new BookDeletedEvent(id);
        ev.entityFound = false;
        return ev;
    }
}
