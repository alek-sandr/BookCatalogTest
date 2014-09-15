package com.testexercise.bookcatalog.domen;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @NotBlank
    @Length(min = 3, max = 30)
    @Column(name = "LOGIN", nullable = false, length = 30)
    private String login;

    @NotBlank
    @Length(min = 8, max = 30)
    @Column(name = "PASSWORD", nullable = false, length = 30)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
