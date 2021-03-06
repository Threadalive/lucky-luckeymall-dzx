package com.lucky.service.impl;

import com.lucky.dao.UserDetailDao;
import com.lucky.entity.UserDetail;
import com.lucky.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户详细信息管理的服务类，包括用户详细信息的增删改查。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/2 11:31
 */
@Service
public class UserDetailServiceImpl implements UserDetailService {

    /**
     * 用户详细信息操作层的dao类
     */
    @Autowired
    UserDetailDao userDetailDao;

    @Override
    public UserDetail getUserDetail(int id) {
        return userDetailDao.getUserDetail(id);
    }

    @Override
    public void addUserDetail(UserDetail userDetail) {
        userDetailDao.addUserDetail(userDetail);
    }

    @Override
    public boolean deleteUserDetail(int id) {
        return userDetailDao.deleteUserDetail(id);
    }

    @Override
    public boolean updateUserDetail(UserDetail userDetail) {
        return userDetailDao.updateUserDetail(userDetail);
    }

    @Override
    public List<UserDetail> getAllUserDetail() {
        return userDetailDao.getAllUserDetail();
    }
}
