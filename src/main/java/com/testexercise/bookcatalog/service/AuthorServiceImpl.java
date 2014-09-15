package com.testexercise.bookcatalog.service;

import com.testexercise.bookcatalog.dao.AuthorDAO;
import com.testexercise.bookcatalog.domen.Author;
import com.testexercise.bookcatalog.domen.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDAO authorDAO;

    @Override
    @Transactional
    public Author getAuthor(Long id) {
        return authorDAO.getAuthor(id);
    }

    @Override
    @Transactional
    public List<Book> getAuthorBooks(Long authorId) {
        return authorDAO.getAuthorBooks(authorId);
    }

    @Override
    @Transactional
    public void addAuthor(Author author) {
        authorDAO.addAuthor(author);
    }

    @Override
    @Transactional
    public void updateAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }

    @Override
    @Transactional
    public List<Author> listAuthors() {
        return authorDAO.listAuthors();
    }

    @Override
    @Transactional
    public boolean deleteAuthor(Long id) {
        return authorDAO.deleteAuthor(id);
    }
}
