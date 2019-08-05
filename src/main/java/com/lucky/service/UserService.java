package com.lucky.service;

import com.lucky.entity.User;
import com.lucky.util.Response;

import java.util.List;

/**
 *
 */
public interface UserService {
    /**
     * 用户基本信息添加
     *
     * @param user 传入用户对象
     * @return void
     * @author zhenxing.dong@luckincoffee.com
     */
    void addUser(User user);

    /**
     * 通过用户名或邮箱获取用户对象
     *
     * @param nameOrEmail 用户名或邮箱
     * @return com.lucky.entity.User
     * @author zhenxing.dong@luckincoffee.com
     */
    User getUser(String nameOrEmail);

    /**
     * 根据用户id获取用户对象
     *
     * @param id 用户id
     * @return com.lucky.entity.User
     * @author zhenxing.dong@luckincoffee.com
     */
    User getUser(int id);

    /**
     * 根据用户id删除用户对象
     *
     * @param id 用户id
     * @return com.lucky.util.Response
     * @author zhenxing.dong@luckincoffee.com
     */
    Response deleteUser(int id);

    /**
     * 更新用户信息
     *
     * @param user 新的用户对象
     * @return boolean true:更新成功 false:更新失败
     * @author zhenxing.dong@luckincoffee.com
     */
    boolean updateUser(User user);

    /**
     * 获取所有用户对象
     *
     * @return java.util.List<com.lucky.entity.User>
     * @author zhenxing.dong@luckincoffee.com
     */
    List<User> getAllUser();
}
