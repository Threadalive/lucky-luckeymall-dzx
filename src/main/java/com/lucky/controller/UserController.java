package com.lucky.controller;

import com.alibaba.fastjson.JSONArray;
import com.lucky.entity.User;
import com.lucky.entity.UserDetail;
import com.lucky.service.UserDetailService;
import com.lucky.service.UserService;
import com.lucky.util.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 该类为用户管理类，拥有控制新用户注册，以及用户
 * 信息的更新，用户信息的获取，以及用户的删除几项功能。
 *
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/1 15:44
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 用户服务类，供控制器调用
     */
    @Resource
    UserService userService;

    /**
     * 用户细节信息管理的服务类，供控制器调用
     */
    @Resource
    UserDetailService userDetailService;

    /**
     * 获取客户端get请求，参数为main时，返回主界面视图。
     * 输入URL为：http://127.0.0.1:8080/user?main
     *
     * @return org.springframework.web.servlet.ModelAndView
     * @author zhenxing.dong@luckincoffee.com
     */
    @GetMapping(params = "main")
    public ModelAndView main(){
        ModelAndView view=new ModelAndView();
        view.setViewName("main");
        return  view;
    }

    /**
     * 获取客户端get请求，参数为register时，返回注册界面视图。
     * 输入URL为：http://127.0.0.1:8080/user?register
     *
     * @return org.springframework.web.servlet.ModelAndView
     * @author zhenxing.dong@luckincoffee.com
     */
    @GetMapping(params = "register")
    public ModelAndView register(){
        ModelAndView view=new ModelAndView();
        view.setViewName("user/register");
        return  view;
    }

    /**
     * 实现注册方法，根据用户名与邮箱判断用户是否已注册，
     * 不存在则调用service与dao层相关方法进行数据库
     * 添加注册。
     *
     * @param userName 用户名
     * @param email  用户邮箱
     * @param nickName  用户昵称
     * @param password  用户密码
     * @param phoneNumber   用户电话号码
     * @param sex   用户性别
     * @param birthday 用户生日
     * @param postNumber 用户邮政编码
     * @param address 用户地址
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author zhenxing.dong@luckincoffee.com
     */
    @PostMapping(params = "register")
    @ResponseBody
    public Map<String, Object> doRegister(String userName, String email,
                                          String nickName, String password,
                                          String phoneNumber, int sex, String birthday,
                                          String postNumber, String address){
        //默认注册结果为fail
        String result = "fail";
        //通过用户名判断是否注册
        User user = userService.getUser(userName);
        if(user != null){
            result = "nameExist";
        }else {
            //通过邮箱判断是否注册
            user = userService.getUser(email);
            if(user != null){
                result = "emailExist";
            }else {
                //设置t_user_mian表的值
                User user1 = new User();
                user1.setUserName(userName);
                user1.setEmail(email);
                user1.setNickName(nickName);
                user1.setScore(0);
                user1.setRole(0);
                userService.addUser(user1);
                user1 = userService.getUser(userName);

                //设置t_user_detail表的值
                UserDetail userDetail = new UserDetail();
                userDetail.setId(user1.getId());
                userDetail.setPassword(password);
                userDetail.setPhoneNumber(phoneNumber);
                userDetail.setSex(sex);
                userDetail.setBirthday(birthday);
                userDetail.setPostNumber(postNumber);
                userDetail.setAddress(address);
                userDetail.setScore(0);
                //生成并设置当前格式化时间
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                userDetail.setRegisterTime(simpleDateFormat.format(date));

                userDetailService.addUserDetail(userDetail);
                //设置成功的话讲result设置为success
                result = "success";
            }
        }
        //将result结果作为键值对返回给前台
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 实现用户信息更新的方法，根据用户名判断用户是否
     * 已注册，存在则调用service与dao层相关方法进
     * 行数据库更新。
     *
     * @param userName 用户名
     * @param email  用户邮箱
     * @param nickName  用户昵称
     * @param password  用户密码
     * @param phoneNumber   用户电话号码
     * @param sex   用户性别
     * @param birthday 用户生日
     * @param postNumber 用户邮政编码
     * @param address 用户地址
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author zhenxing.dong@luckincoffee.com
     */
    @PostMapping(params = "updateUser")
    @ResponseBody
    public Map<String,Object> updateUser(String userName, String email, String nickName,
                                         String password, String phoneNumber, int sex,
                                         String birthday, String postNumber, String address){
        String result = "";
        User user = userService.getUser(userName);
        if(user == null){
            //若用户不存在，返回result为fail
            result = "fail";
        }else {
            //设置用户对象基本信息
            user.setUserName(userName);
            user.setEmail(email);
            user.setNickName(nickName);
            userService.updateUser(user);

            //设置用户对象详细信息
            UserDetail userDetail = userDetailService.getUserDetail(user.getId());
            userDetail.setAddress(address);
            userDetail.setBirthday(birthday);
            userDetail.setPassword(password);
            userDetail.setPhoneNumber(phoneNumber);
            userDetail.setSex(sex);
            userDetail.setPostNumber(postNumber);
            userDetailService.updateUserDetail(userDetail);

            //设置成功将resul设置为success
            result = "success";
        }

        //将result结果作为键值对返回给前台
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 获取所有的用户信息以JSON字符串形式作为值存与Map中返回给前台。
     *
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author zhenxing.dong@luckincoffee.com
     */
    @GetMapping(params = "getAllUser")
    @ResponseBody
    public Map<String,Object> getAllUser(){
        List<User> userList = new ArrayList<>();
        userList = userService.getAllUser();

        //将数组中对象信息转为JSON键值对字符串形式
        String allUsers = JSONArray.toJSONString(userList);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("allUsers",allUsers);
        return resultMap;
    }

    /**
     * delete请求控制用户信息的删除，根据URL中传入的参数id，删除对应id的用户。
     * URL如 delete: http://127.0.0.7:8080/user/2，即删除id为2的用户。
     *
     * @return com.lucky.util.Response
     * @author zhenxing.dong@luckincoffee.com
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Response doDelete(@PathVariable int id){
        return userService.deleteUser(id);
    }

}
