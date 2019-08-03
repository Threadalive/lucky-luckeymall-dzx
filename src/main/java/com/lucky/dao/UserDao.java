package com.lucky.dao;

import com.lucky.entity.User;

import java.util.List;

public interface UserDao{
    void addUser(User user);
    User getUser(String nameOrEmail);
    User getUser(int id);
    boolean deleteUser(int id);
    boolean updateUser(User user);
    List<User> getAllUser();
}
