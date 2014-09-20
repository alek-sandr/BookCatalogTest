package com.testexercise.bookcatalog.dao;

import com.testexercise.bookcatalog.domain.Book;
import com.testexercise.bookcatalog.events.book.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public BookCreatedEvent addBook(CreateBookEvent createBookEvent) {
        Long id = (Long) sessionFactory.getCurrentSession().save(createBookEvent.getBook());
        return new BookCreatedEvent(id, createBookEvent.getBook());
    }

    @Override
    public BookEvent getBook(RequestBookEvent requestBookEvent) {
        Book book = (Book) sessionFactory.getCurrentSession()
                .get(Book.class, requestBookEvent.getId());
        if (book == null) {
            return BookEvent.notFound(requestBookEvent.getId());
        }
        return new BookEvent(book.getId(), book);
    }

    @Override
    public BooksEvent listBooks(RequestAllBooksEvent requestAllBooksEvent) {
        List<Book> allBooks = sessionFactory.getCurrentSession()
                .createQuery("from Book").list();
        return new BooksEvent(allBooks);
    }

    @Override
    public BookUpdatedEvent updateBook(UpdateBookEvent updateBookEvent) {
        if(sessionFactory.getCurrentSession().get(Book.class, updateBookEvent.getId()) != null) {
            sessionFactory.getCurrentSession().merge(updateBookEvent.getBook());
            return new BookUpdatedEvent(updateBookEvent.getId(), updateBookEvent.getBook());
        } else {
            return BookUpdatedEvent.notFound(updateBookEvent.getId());
        }
    }

    @Override
    public BookDeletedEvent deleteBook(DeleteBookEvent deleteBookEvent) {
        Book book = (Book) sessionFactory.getCurrentSession()
                .get(Book.class, deleteBookEvent.getId());
        if (book != null) {
            book.getAuthors().clear();
            sessionFactory.getCurrentSession().delete(book);
            return new BookDeletedEvent(book.getId(), book);
        } else {
            return BookDeletedEvent.notFound(deleteBookEvent.getId());
        }
    }

    @Override
    public BooksEvent searchBook(SearchBookEvent searchBookEvent) {
        List<Book> books = sessionFactory.getCurrentSession()
                .createQuery("from Book where title like :query")
                .setString("query", "%" + searchBookEvent.getQuery() + "%").list();
        return new BooksEvent(books);
    }
}
