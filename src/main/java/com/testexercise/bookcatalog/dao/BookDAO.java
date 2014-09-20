package com.testexercise.bookcatalog.dao;

import com.testexercise.bookcatalog.events.book.*;

public interface BookDAO {

    public BookCreatedEvent addBook(CreateBookEvent createBookEvent);

    public BookEvent getBook(RequestBookEvent requestBookEvent);

    public BooksEvent listBooks(RequestAllBooksEvent requestAllBooksEvent);

    public BookUpdatedEvent updateBook(UpdateBookEvent updateBookEvent);

    public BookDeletedEvent deleteBook(DeleteBookEvent deleteBookEvent);

    public BooksEvent searchBook(SearchBookEvent searchBookEvent);
}
