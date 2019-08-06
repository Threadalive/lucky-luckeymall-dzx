package com.lucky.service.impl;

import com.lucky.dao.UserDao;
import com.lucky.entity.User;
import com.lucky.entity.UserDetail;
import com.lucky.service.UserDetailService;
import com.lucky.service.UserService;
import com.lucky.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 用户基本信息管理的服务类，包括用户基本信息的增删改查。
 *
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/1 15:46
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 用户操作的dao类，操作数据库。
     */
    @Autowired
    private UserDao userDao;

    /**
     * 用户细节信息管理的服务类.
     */
    @Autowired
    private UserDetailService userDetailService;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getUser(String nameOrEmail) {
        User user = userDao.getUser(nameOrEmail);
        return user;
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public Response deleteUser(int id) {
        //待补......
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Transactional
    @Override
    public Map<String, Object> doRegister(User vUser, UserDetail vUserDetail){
        //默认注册结果为fail
        String result = "fail";
        //通过用户名判断是否注册
        User user = getUser(vUser.getUserName());
        if(user != null){
            result = "nameExist";
        }else {
            //通过邮箱判断是否注册
            user = getUser(vUser.getEmail());
            if(user != null){
                result = "emailExist";
            }else {
                //设置t_user_mian表的值
                vUser.setScore(0);
                vUser.setRole(0);
                addUser(vUser);
                user = getUser(vUser.getId());
                //设置t_user_detail表的值
                vUserDetail.setId(user.getId());
                vUserDetail.setScore(0);
                //生成并设置当前时间戳
                vUserDetail.setRegisterTime(System.currentTimeMillis());
                userDetailService.addUserDetail(vUserDetail);

                //设置成功的话讲result设置为success
                result = "success";
            }
        }
        //将result结果作为键值对返回给前台
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @Transactional
    @Override
    public Map<String,Object> updateUser(User vUser,UserDetail vUserDetail){
        String result = "";
        User user = getUser(vUser.getUserName());
        if(user == null){
            //若用户不存在，返回result为fail
            result = "fail";
        }else {
            //设置用户对象基本信息
            updateUser(vUser);
            //设置用户对象详细信息
            UserDetail userDetail = userDetailService.getUserDetail(user.getId());
            userDetailService.updateUserDetail(vUserDetail);
            //设置成功将resul设置为success
            result = "success";
        }
        //将result结果作为键值对返回给前台
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @Override
    public Map<String,Object> doLogin(String nameOrEmail, String password, HttpSession httpSession){
        String result = "fail";
        User user = getUser(nameOrEmail);
        if(user == null){
            //若用户名不存在
            result = "nameUnexist";
        }else {
            UserDetail userDetail = userDetailService.getUserDetail(user.getId());
            if(password.equals(userDetail.getPassword())){
                result = "success";
                //暂存用户对象
                httpSession.setAttribute("currentUser",user);
            }else {
                result = "wrong";
            }
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",result);
        return resultMap;
    }
}
