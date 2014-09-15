package com.testexercise.bookcatalog.domen;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AUTHORS")
public class Author {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @NotBlank
    @Length(min = 1, max = 30)
    @Column(name = "FIRSTNAME", nullable = false, length = 30)
    private String firstName;

    @NotBlank
    @Length(min = 1, max = 30)
    @Column(name = "LASTNAME", nullable = false, length = 30)
    private String lastName;


    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
