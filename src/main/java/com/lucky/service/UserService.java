package com.lucky.service;

import com.lucky.entity.PageBean;
import com.lucky.entity.User;
import com.lucky.entity.UserDetail;
import com.lucky.util.Response;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description 用户基本信息管理的操作类，包括用户基本信息的增删改查。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/1 15:46
 */
public interface UserService {
    /**
     * 用户基本信息添加
     *
     * @param user 传入用户对象
     */
    void addUser(User user);

    /**
     * 通过用户名或邮箱获取用户对象
     *
     * @param nameOrEmail 用户名或邮箱
     * @return 指定用户对象
     */
    User getUser(String nameOrEmail);

    /**
     * 根据用户id获取用户对象
     *
     * @param id 用户id
     * @return 指定用户对象
     */
    Map<String, Object> getUserById(int id);

    /**
     * 根据用户id删除用户对象
     *
     * @param id 用户id
     * @return Response 操作状态
     */
    Response deleteUser(int id);

    /**
     * 更新用户信息
     *
     * @param user 新的用户对象
     * @return boolean true:更新成功 false:更新失败
     */
    boolean updateUser(User user);

    /**
     * 获取所有用户对象
     *
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @return 用户列表页
     */
    PageBean getAllUser(int currentPage, int pageSize);

    /**
     * 实现注册功能的业务逻辑
     *
     * @param vUser       用户基本信息表单内容
     * @param vUserDetail 用户详细信息表单内容
     * @return 注册结果
     */
    Map<String, Object> doRegister(User vUser, UserDetail vUserDetail);

    /**
     * 实现用户信息更新的方法，根据用户名判断用户是否
     * 已注册，存在则调用service与dao层相关方法进
     * 行数据库更新。
     *
     * @param vUser       基本用户参数
     * @param vUserDetail 详细用户参数
     * @param oldUserName 原先的用户名
     * @return 更新结果
     */
    Map<String, Object> updateUser(User vUser, UserDetail vUserDetail, String oldUserName);

    /**
     * 根据用户输入的用户名或邮箱，查询数据库，若有匹配，则校验密码，通过则
     * 将结果设为success返回给前台。
     *
     * @param nameOrEmail 用户输入用户名或邮箱
     * @param password    用户输入的密码
     * @param httpSession session暂存用户信息
     * @return 登录结果
     */
    Map<String, Object> doLogin(String nameOrEmail, String password, HttpSession httpSession);

    /**
     * 通过用户id获取用户的地址和手机号码
     *
     * @param userId 用户id
     * @return 用户地址或手机号码
     */
    Map<String, Object> getUserAddressAndPhoneNumber(int userId);

    /**
     * 根据用户id获取用户详细信息
     *
     * @param id 用户id
     * @return 用户详细信息结果
     */
    Map<String, Object> getUserDetailById(int id);

    /**
     * 获取用户数
     *
     * @return 用户数
     */
    Map<String, Object> getUserCount();

    /**
     * 用户数
     *
     * @return 用户数
     */
    int count();
}
