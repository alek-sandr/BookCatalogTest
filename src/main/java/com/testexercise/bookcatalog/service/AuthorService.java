package com.testexercise.bookcatalog.service;

import com.testexercise.bookcatalog.events.author.*;

public interface AuthorService {

    public AuthorCreatedEvent createAuthor(CreateAuthorEvent createAuthorEvent);

    public AuthorEvent requestAuthor(RequestAuthorEvent requestAuthorEvent);

    public AuthorEvent requestAuthorWithBooks(RequestAuthorEvent requestAuthorEvent);

    public AllAuthorsEvent requestAllAuthors(RequestAllAuthorsEvent requestAllAuthorsEvent);

    public AuthorUpdatedEvent updateAuthor(UpdateAuthorEvent updateAuthorEvent);

    public AuthorDeletedEvent deleteAuthor(DeleteAuthorEvent deleteAuthorEvent);
}
