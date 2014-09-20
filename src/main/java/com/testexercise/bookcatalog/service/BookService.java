package com.testexercise.bookcatalog.service;

import com.testexercise.bookcatalog.events.book.*;

public interface BookService {

    public BookCreatedEvent createBook(CreateBookEvent createBookEvent);

    public BookEvent getBook(RequestBookEvent requestBookEvent);

    public BooksEvent listBooks(RequestAllBooksEvent requestAllBooksEvent);

    public BookUpdatedEvent updateBook(UpdateBookEvent updateBookEvent);

    public BookDeletedEvent deleteBook(DeleteBookEvent deleteBookEvent);

    public BooksEvent searchBook(SearchBookEvent searchBookEvent);
}
