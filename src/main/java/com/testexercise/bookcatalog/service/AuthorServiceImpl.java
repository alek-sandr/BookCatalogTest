package com.testexercise.bookcatalog.service;

import com.testexercise.bookcatalog.dao.AuthorDAO;
import com.testexercise.bookcatalog.events.author.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDAO authorDAO;

    @Override
    @Transactional
    public AuthorCreatedEvent createAuthor(CreateAuthorEvent createAuthorEvent) {
        return authorDAO.addAuthor(createAuthorEvent);
    }

    @Override
    @Transactional
    public AuthorEvent requestAuthor(RequestAuthorEvent requestAuthorEvent) {
        return authorDAO.getAuthor(requestAuthorEvent);
    }

    @Override
    @Transactional
    public AuthorEvent requestAuthorWithBooks(RequestAuthorEvent requestAuthorEvent) {
        return authorDAO.getAuthorWithBooks(requestAuthorEvent);
    }

    @Override
    @Transactional
    public AllAuthorsEvent requestAllAuthors(RequestAllAuthorsEvent requestAllAuthorsEvent) {
        return authorDAO.listAuthors(requestAllAuthorsEvent);
    }

    @Override
    @Transactional
    public AuthorUpdatedEvent updateAuthor(UpdateAuthorEvent updateAuthorEvent) {
        return authorDAO.updateAuthor(updateAuthorEvent);
    }

    @Override
    @Transactional
    public AuthorDeletedEvent deleteAuthor(DeleteAuthorEvent deleteAuthorEvent) {
        return authorDAO.deleteAuthor(deleteAuthorEvent);
    }
}
