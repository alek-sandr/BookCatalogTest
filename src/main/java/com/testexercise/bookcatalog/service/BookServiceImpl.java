package com.testexercise.bookcatalog.service;

import com.testexercise.bookcatalog.dao.BookDAO;
import com.testexercise.bookcatalog.domen.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    @Transactional
    public Book getBook(Long id) {
        return bookDAO.getBook(id);
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    @Transactional
    public List<Book> listBooks() {
        return bookDAO.listBooks();
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookDAO.deleteBook(id);
    }

    @Override
    @Transactional
    public List<Book> searchBook(String query) {
        return bookDAO.searchBook(query);
    }
}
