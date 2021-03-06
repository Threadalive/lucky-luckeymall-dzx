package com.lucky.service;

import com.lucky.entity.UserDetail;

import java.util.List;

/**
 * @Description 用户详细信息管理的服务类，包括用户详细信息的增删改查。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/2 11:31
 */
public interface UserDetailService {
    /**
     * 根据用户id获取用户细节信息对象。
     *
     * @param id 用户id
     * @return 指定id的详细用户细节对象
     */
    UserDetail getUserDetail(int id);

    /**
     * 添加用户信息类对象。
     *
     * @param userDetail 用户细节信息类的对象
     */
    void addUserDetail(UserDetail userDetail);

    /**
     * 根据id删除指定用户信息类。
     *
     * @param id 用户细节信息类的id
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteUserDetail(int id);

    /**
     * 更新用户的细节信息。
     *
     * @param userDetail 用户细节信息类
     * @return boolean true:更新成功 false:更新失败
     */
    boolean updateUserDetail(UserDetail userDetail);

    /**
     * 获取所有的用户细节信息，以List<UserDetail>的形式返回。
     *
     * @return 所有详细用户细节对象
     */
    List<UserDetail> getAllUserDetail();
}
