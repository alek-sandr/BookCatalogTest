package com.testexercise.bookcatalog.dao;

import com.testexercise.bookcatalog.domain.Author;
import com.testexercise.bookcatalog.domain.Book;
import com.testexercise.bookcatalog.events.author.*;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public AuthorCreatedEvent addAuthor(CreateAuthorEvent createAuthorEvent) {
        Long id = (Long) sessionFactory.getCurrentSession().save(createAuthorEvent.getAuthor());
        return new AuthorCreatedEvent(id, createAuthorEvent.getAuthor());
    }

    @Override
    public AuthorEvent getAuthor(RequestAuthorEvent requestAuthorEvent) {
        Author author = (Author) sessionFactory.getCurrentSession()
                .get(Author.class, requestAuthorEvent.getId());
        if (author == null) {
            return AuthorEvent.notFound(requestAuthorEvent.getId());
        }
        return new AuthorEvent(author.getId(), author);
    }

    @Override
    public AuthorEvent getAuthorWithBooks(RequestAuthorEvent requestAuthorEvent) {
        AuthorEvent ae = getAuthor(requestAuthorEvent);
        if (!ae.isEntityFound()) {
            return ae;
        }
        Hibernate.initialize(ae.getAuthor().getBooks());
        return ae;
    }

    @Override
    public AllAuthorsEvent listAuthors(RequestAllAuthorsEvent requestAllAuthorsEvent) {
        List<Author> authors = sessionFactory.getCurrentSession()
                .createQuery("from Author").list();
        return new AllAuthorsEvent(authors);
    }

    @Override
    public AuthorUpdatedEvent updateAuthor(UpdateAuthorEvent updateAuthorEvent) {
        Author storedAuthor = (Author) sessionFactory.getCurrentSession()
                .get(Author.class, updateAuthorEvent.getId());
        if(storedAuthor == null) {
            return AuthorUpdatedEvent.notFound(updateAuthorEvent.getId());
        }
        sessionFactory.getCurrentSession().merge(updateAuthorEvent.getAuthor());
        return new AuthorUpdatedEvent(updateAuthorEvent.getId(),
                updateAuthorEvent.getAuthor());
    }

    @Override
    public AuthorDeletedEvent deleteAuthor(DeleteAuthorEvent deleteAuthorEvent) {
        Author author = (Author) sessionFactory.getCurrentSession()
                .get(Author.class, deleteAuthorEvent.getId());
        if (author == null) {
            return AuthorDeletedEvent.notFound(deleteAuthorEvent.getId());
        }
        boolean canDelete = true;
        List<Book> books = author.getBooks();
        for (Book b : books) {
            if (b.getAuthors().size() > 1) {//not single author
                canDelete = false;
                break;
            }
        }
        if (!canDelete) {
            return AuthorDeletedEvent.deletionForbidden(author.getId(), author);
        }
        for (Book b : books) {
            sessionFactory.getCurrentSession().delete(b);
        }
        sessionFactory.getCurrentSession().delete(author);
        return new AuthorDeletedEvent(author.getId(), author);
    }
}
