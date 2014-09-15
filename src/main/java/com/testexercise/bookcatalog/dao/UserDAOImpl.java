package com.testexercise.bookcatalog.dao;

import com.testexercise.bookcatalog.domen.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl  implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;
    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Override
    public User getUserByLogin(String login) {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("from User where login = :login")
                .setString("login", login).list();
        if (users.isEmpty()) return null;
        return users.get(0);
    }
}
