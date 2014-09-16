package com.testexercise.bookcatalog.service;

import com.testexercise.bookcatalog.domain.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);

    public void deleteUser(Long id);

    public List<User> listUsers();
}
