package com.lucky.dao;

import com.lucky.entity.User;

import java.util.List;

/**
 * @Description 用户基本信息的操作层接口，定义用户详细信息的增删改查方法。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/2 22:16
 */
public interface UserDao{
    /**
     * 实现用户对象添加。
     *
     * @param user 注册用户对象
     */
    void addUser(User user);

    /**
     * 通过用户名或邮箱获取用户。
     *
     * @param nameOrEmail 用户名或邮箱
     * @return 用户对象
     */
    User getUser(String nameOrEmail);

    /**
     * 通过用户id获取用户。
     *
     * @param id 用户id
     * @return 用户对象
     */
    User getUser(int id);

    /**
     * 通过用那个户id删除指定用户。
     *
     * @param id 用户id
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteUser(int id);

    /**
     * 更新用户信息。
     *
     * @param user 用户对象
     * @return boolean true:更新成功 false:更新失败
     */
    boolean updateUser(User user);

    /**
     * 获取所有用户，以List<User>的形式返回。
     *
     * @param offset 起始位置
     * @param length 长度
     * @return 用户列表
     */
    List<User> getAllUser(int offset,int length);

    /**
     * 统计用户数量
     *
     * @return 用户数量
     */
    int getUserCount();
}
