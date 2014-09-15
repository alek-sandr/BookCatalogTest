package com.testexercise.bookcatalog.dao;

import com.testexercise.bookcatalog.domen.User;

public interface UserDAO {

    public void addUser(User user);

    public void deleteUser(Long id);

    public User getUserByLogin(String login);
}
