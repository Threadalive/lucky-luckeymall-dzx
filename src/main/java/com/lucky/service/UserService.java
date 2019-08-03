package com.lucky.service;

import com.lucky.entity.User;
import com.lucky.util.Response;

import java.util.List;

/**
 *
 */
public interface UserService {
    void addUser(User user);
    User getUser(String nameOrEmail);
    User getUser(int id);
    Response deleteUser(int id);
    boolean updateUser(User user);
    List<User> getAllUser();

}
