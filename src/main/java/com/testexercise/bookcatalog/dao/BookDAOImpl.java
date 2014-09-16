package com.testexercise.bookcatalog.dao;

import com.testexercise.bookcatalog.domain.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Book getBook(Long id) {
        return (Book) sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Override
    public void addBook(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }

    @Override
    public void updateBook(Book book) {
        if(sessionFactory.getCurrentSession().get(Book.class, book.getId()) != null) {
            sessionFactory.getCurrentSession().merge(book);
        }
    }

    @Override
    public List<Book> listBooks() {
        return sessionFactory.getCurrentSession().createQuery("from Book").list();
    }

    @Override
    public void deleteBook(Long id) {
        Book book = (Book) sessionFactory.getCurrentSession().get(Book.class, id);
        if (book != null) {
            book.getAuthors().clear();
            sessionFactory.getCurrentSession().delete(book);
        }
    }

    @Override
    public List<Book> searchBook(String query) {
        List<Book> books = sessionFactory.getCurrentSession()
                .createQuery("from Book where title like :query")
                .setString("query", "%" + query + "%").list();
        return books;
    }
}
