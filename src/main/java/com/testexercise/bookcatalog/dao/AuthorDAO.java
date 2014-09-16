package com.testexercise.bookcatalog.dao;

import com.testexercise.bookcatalog.domain.Author;
import com.testexercise.bookcatalog.domain.Book;

import java.util.List;

public interface AuthorDAO {

    public void addAuthor(Author author);

    public void updateAuthor(Author author);

    public List<Author> listAuthors();

    public boolean deleteAuthor(Long id);

    public Author getAuthor(Long id);

    public List<Book> getAuthorBooks(Long authorId);
}
