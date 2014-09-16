package com.testexercise.bookcatalog.dao;

import com.testexercise.bookcatalog.domain.User;

import java.util.List;

public interface UserDAO {

    public void addUser(User user);

    public void deleteUser(Long id);

    public User getUserByLogin(String login);

    public List<User> listUsers();
}
