package com.testexercise.bookcatalog.domen;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.FieldError;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @NotBlank
    @Length(min = 1, max = 255)
    @Column(name = "TITLE", nullable = false)
    private String title;

    @NotBlank
    @Length(min = 1, max = 1024)
    @Column(name = "DESCRIPTION", nullable = false, length = 1024)
    private String description;

    @NotNull
    @Column(name = "YEAR", nullable = false)
    private int year;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = {@JoinColumn(name = "BOOK_ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID", nullable = false)})
    private List<Author> authors = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
