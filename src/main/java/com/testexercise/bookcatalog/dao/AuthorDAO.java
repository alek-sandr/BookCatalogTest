package com.testexercise.bookcatalog.dao;

import com.testexercise.bookcatalog.events.author.*;

public interface AuthorDAO {

    public AuthorCreatedEvent addAuthor(CreateAuthorEvent createAuthorEvent);

    public AuthorEvent getAuthor(RequestAuthorEvent requestAuthorEvent);

    public AuthorEvent getAuthorWithBooks(RequestAuthorEvent requestAuthorEvent);

    public AllAuthorsEvent listAuthors(RequestAllAuthorsEvent requestAllAuthorsEvent);

    public AuthorUpdatedEvent updateAuthor(UpdateAuthorEvent updateAuthorEvent);

    public AuthorDeletedEvent deleteAuthor(DeleteAuthorEvent deleteAuthorEvent);
}
