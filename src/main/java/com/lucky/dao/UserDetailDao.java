package com.lucky.dao;

import com.lucky.entity.UserDetail;

import java.util.List;

public interface UserDetailDao {

    /**
     * 根据id获取用户细节信息类的指定对象。
     *
     * @param id 用户细节信息类的id
     * @return 指定id的用户细节信息对象
     */
    UserDetail getUserDetail(int id);

    /**
     * 添加用户信息对象。
     *
     * @param userDetail 用户信息对象
     * @return void
     */
    void addUserDetail(UserDetail userDetail);

    /**
     * 根据用户信息对象id删除指定用户信息对象。
     *
     * @param id 用户信息对象id
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteUserDetail(int id);

    /**
     * 更新用户细节信息对象。
     *
     * @param userDetail 用户细节信息对象
     * @return boolean true:更新成功 false:更新失败
     */
    boolean updateUserDetail(UserDetail userDetail);

    /**
     * 获取所有用户细节信息对象。
     *
     * @return 所有用户详细信息对象
     */
    List<UserDetail> getAllUserDetail();
}
