package com.lucky.dao;

import com.lucky.entity.UserDetail;

import java.util.List;

public interface UserDetailDao {

    UserDetail getUserDetail(int id);

    void addUserDetail(UserDetail userDetail);

    boolean deleteUserDetail(int id);

    boolean updateUserDetail(UserDetail userDetail);

    List<UserDetail> getAllUserDetail();
}
