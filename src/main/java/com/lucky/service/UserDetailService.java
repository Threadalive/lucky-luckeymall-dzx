package com.lucky.service;

import com.lucky.entity.UserDetail;

import java.util.List;

public interface UserDetailService {
    UserDetail getUserDetail(int id);

    void addUserDetail(UserDetail userDetail);

    boolean deleteUserDetail(int id);

    boolean updateUserDetail(UserDetail userDetail);

    List<UserDetail> getAllUserDetail();
}
