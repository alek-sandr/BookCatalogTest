package com.testexercise.bookcatalog.service;

import com.testexercise.bookcatalog.domen.Book;

import java.util.List;

public interface BookService {

    public void addBook(Book book);

    public void updateBook(Book book);

    public List<Book> listBooks();

    public void deleteBook(Long id);

    public Book getBook(Long id);

    public List<Book> searchBook(String query);
}
