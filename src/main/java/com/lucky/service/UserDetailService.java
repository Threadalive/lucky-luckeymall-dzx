package com.lucky.service;

import com.lucky.entity.UserDetail;

import java.util.List;

public interface UserDetailService {
    /**
     * 根据用户id获取用户细节信息对象。
     *
     * @param id 用户id
     * @return com.lucky.entity.UserDetail
     * @author zhenxing.dong@luckincoffee.com
     */
    UserDetail getUserDetail(int id);

    /**
     * 添加用户信息类对象。
     *
     * @param userDetail 用户细节信息类的对象
     * @return void
     * @author zhenxing.dong@luckincoffee.com
     */
    void addUserDetail(UserDetail userDetail);

    /**
     * 根据id删除指定用户信息类。
     *
     * @param id 用户细节信息类的id
     * @return boolean true:删除成功 false:删除失败
     * @author zhenxing.dong@luckincoffee.com
     */
    boolean deleteUserDetail(int id);

    /**
     * 更新用户的细节信息。
     *
     * @param userDetail 用户细节信息类
     * @return boolean true:更新成功 false:更新失败
     * @author zhenxing.dong@luckincoffee.com
     */
    boolean updateUserDetail(UserDetail userDetail);

    /**
     * 获取所有的用户细节信息，以List<UserDetail>的形式返回。
     *
     * @return java.util.List<com.lucky.entity.UserDetail>
     * @author zhenxing.dong@luckincoffee.com
     */
    List<UserDetail> getAllUserDetail();
}
