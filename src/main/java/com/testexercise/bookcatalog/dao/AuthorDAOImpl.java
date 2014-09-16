package com.testexercise.bookcatalog.dao;

import com.testexercise.bookcatalog.domain.Author;
import com.testexercise.bookcatalog.domain.Book;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Author getAuthor(Long id) {
        return (Author) sessionFactory.getCurrentSession().get(Author.class, id);
    }

    @Override
    public List<Book> getAuthorBooks(Long authorId) {
        Author author = getAuthor(authorId);
        List<Book> books = null;
        if (author != null) {
            books = author.getBooks();
            Hibernate.initialize(books);
        } else {
            books = new ArrayList<>();
        }
        return books;
    }

    @Override
    public void addAuthor(Author author) {
        sessionFactory.getCurrentSession().save(author);
    }

    @Override
    public void updateAuthor(Author author) {
        if(sessionFactory.getCurrentSession().get(Author.class, author.getId()) != null) {
            sessionFactory.getCurrentSession().merge(author);
        }
    }

    @Override
    public List<Author> listAuthors() {
        return sessionFactory.getCurrentSession().createQuery("from Author").list();
    }

    @Override
    public boolean deleteAuthor(Long id) {
        Author author = (Author) sessionFactory.getCurrentSession().get(Author.class, id);
        boolean canDelete = true;
        if (author != null) {
            List<Book> books = author.getBooks();
            for (Book b : books) {
                if (b.getAuthors().size() > 1) {//not single author
                    canDelete = false;
                }
            }
            if (canDelete) {
                for (Book b : books) {
                    sessionFactory.getCurrentSession().delete(b);
                }
                sessionFactory.getCurrentSession().delete(author);
            }
        }
        return canDelete;
    }
}
