package com.lucky.dao;

import com.lucky.entity.User;

import java.util.List;

public interface UserDao{
    /**
     * 实现用户对象添加。
     *
     * @param user 注册用户对象
     * @return void
     * @author zhenxing.dong@luckincoffee.com
     */
    void addUser(User user);

    /**
     * 通过用户名或邮箱获取用户。
     *
     * @param nameOrEmail 用户名或邮箱
     * @return com.lucky.entity.User
     * @author zhenxing.dong@luckincoffee.com
     */
    User getUser(String nameOrEmail);

    /**
     * 通过用户id获取用户。
     *
     * @param id 用户id
     * @return com.lucky.entity.User
     * @author zhenxing.dong@luckincoffee.com
     */
    User getUser(int id);

    /**
     * 通过用那个户id删除指定用户。
     *
     * @param id 用户id
     * @return boolean
     * @author zhenxing.dong@luckincoffee.com
     */
    boolean deleteUser(int id);

    /**
     * 更新用户信息。
     *
     * @param user 用户对象
     * @return boolean
     * @author zhenxing.dong@luckincoffee.com
     */
    boolean updateUser(User user);

    /**
     * 获取所有用户，以List<User>的形式返回。
     *
     * @return java.util.List<com.lucky.entity.User>
     * @author zhenxing.dong@luckincoffee.com
     */
    List<User> getAllUser();
}
