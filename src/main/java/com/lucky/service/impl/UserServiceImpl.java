package com.lucky.service.impl;

import com.lucky.dao.UserDao;
import com.lucky.entity.User;
import com.lucky.service.UserService;
import com.lucky.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户基本信息管理的服务类，包括用户基本信息的增删改查。
 *
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/1 15:46
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 用户操作的dao类，操作数据库。
     */
    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getUser(String nameOrEmail) {
        User user = userDao.getUser(nameOrEmail);
        return user;
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public Response deleteUser(int id) {
        //待补......
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
