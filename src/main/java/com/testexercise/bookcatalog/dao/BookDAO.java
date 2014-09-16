package com.testexercise.bookcatalog.dao;

import com.testexercise.bookcatalog.domain.Book;

import java.util.List;

public interface BookDAO {

    public void addBook(Book book);

    public void updateBook(Book book);

    public List<Book> listBooks();

    public void deleteBook(Long id);

    public Book getBook(Long id);

    public List<Book> searchBook(String query);
}
