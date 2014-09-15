package com.testexercise.bookcatalog.service;

import com.testexercise.bookcatalog.domen.User;

public interface UserService {

    public void addUser(User user);

    public void deleteUser(Long id);
}
