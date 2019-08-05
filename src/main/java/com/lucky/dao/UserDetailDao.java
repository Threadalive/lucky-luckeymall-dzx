package com.lucky.dao;

import com.lucky.entity.UserDetail;

import java.util.List;

public interface UserDetailDao {

    /**
     * 根据id获取用户细节信息类的指定对象。
     *
     * @param id 用户细节信息类的id
     * @return com.lucky.entity.UserDetail
     * @author zhenxing.dong@luckincoffee.com
     */
    UserDetail getUserDetail(int id);

    /**
     * 添加用户信息对象。
     *
     * @param userDetail 用户信息对象
     * @return void
     * @author zhenxing.dong@luckincoffee.com
     */
    void addUserDetail(UserDetail userDetail);

    /**
     * 根据用户信息对象id删除指定用户信息对象。
     *
     * @param id 用户信息对象id
     * @return boolean true:删除成功 false:删除失败
     * @author zhenxing.dong@luckincoffee.com
     */
    boolean deleteUserDetail(int id);

    /**
     * 更新用户细节信息对象。
     *
     * @param userDetail 用户细节信息对象
     * @return boolean
     * @author zhenxing.dong@luckincoffee.com
     */
    boolean updateUserDetail(UserDetail userDetail);

    /**
     * 获取所有用户细节信息对象。
     *
     * @return java.util.List<com.lucky.entity.UserDetail>
     * @author zhenxing.dong@luckincoffee.com
     */
    List<UserDetail> getAllUserDetail();
}
