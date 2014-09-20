package com.testexercise.bookcatalog.service;

import com.testexercise.bookcatalog.dao.BookDAO;
import com.testexercise.bookcatalog.events.book.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    @Transactional
    public BookCreatedEvent createBook(CreateBookEvent createBookEvent) {
        return bookDAO.addBook(createBookEvent);
    }

    @Override
    @Transactional
    public BookEvent getBook(RequestBookEvent requestBookEvent) {
        return bookDAO.getBook(requestBookEvent);
    }

    @Override
    @Transactional
    public BooksEvent listBooks(RequestAllBooksEvent requestAllBooksEvent) {
        return bookDAO.listBooks(requestAllBooksEvent);
    }

    @Override
    @Transactional
    public BookUpdatedEvent updateBook(UpdateBookEvent updateBookEvent) {
        return bookDAO.updateBook(updateBookEvent);
    }

    @Override
    @Transactional
    public BookDeletedEvent deleteBook(DeleteBookEvent deleteBookEvent) {
        return bookDAO.deleteBook(deleteBookEvent);
    }

    @Override
    @Transactional
    public BooksEvent searchBook(SearchBookEvent searchBookEvent) {
        return bookDAO.searchBook(searchBookEvent);
    }
}
