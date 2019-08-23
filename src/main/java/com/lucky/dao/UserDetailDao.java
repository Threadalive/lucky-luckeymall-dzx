package com.lucky.dao;

import com.lucky.entity.UserDetail;

import java.util.List;

/**
 * @Description 用户详细信息的操作层接口，定义用户详细信息的增删改查方法。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/2 22:16
 */
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

    /**
     * boolean true:更新成功 false:更新失败
     *
     * @param userId 用户id
     * @param score 积分
     * @return boolean true:更新成功 false:更新失败
     */
    boolean updateUserScore(int userId,int score);

    /**
     * 根据用户id获取其当前总积分
     *
     * @param userId 用户积分
     * @return 用户积分
     */
    int getUserScore(int userId);
}
