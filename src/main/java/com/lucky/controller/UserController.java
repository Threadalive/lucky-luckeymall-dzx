/**
 * @Description 用户管理类
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/1 15:44
 */
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

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    UserDetailService userDetailService;


    @GetMapping(params = "main")
    public ModelAndView main(){
        ModelAndView view=new ModelAndView();
        view.setViewName("main");
        return  view;
    }

//    http://127.0.0.1:8080/user?register
    @GetMapping(params = "register")
    public ModelAndView register(){
        ModelAndView view=new ModelAndView();
        view.setViewName("register");
        return  view;
    }
    //http://127.0.0.1:8080/doRegister
    @PostMapping(params = "register")
    @ResponseBody
    public Map<String, Object> doRegister(String userName, String email,
                                          String nickName, String password,
                                          String phoneNumber, int sex, String birthday,
                                          String postNumber, String address){
        String result = "fail";
        User user = userService.getUser(userName);
        if(user != null){
            result = "nameExist";
        }else {
            user = userService.getUser(email);
            if(user != null){
                result = "emailExist";
            }else {
                User user1 = new User();
                user1.setUserName(userName);
                user1.setEmail(email);
                user1.setNickName(nickName);
                user1.setScore(0);
                user1.setRole(0);
                userService.addUser(user1);
                user1 = userService.getUser(userName);

                UserDetail userDetail = new UserDetail();
                userDetail.setId(user1.getId());
                userDetail.setPassword(password);
                userDetail.setPhoneNumber(phoneNumber);
                userDetail.setSex(sex);
                userDetail.setBirthday(birthday);
                userDetail.setPostNumber(postNumber);
                userDetail.setAddress(address);
                userDetail.setScore(0);
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                userDetail.setRegisterTime(simpleDateFormat.format(date));

                userDetailService.addUserDetail(userDetail);
                result = "success";
            }
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @PostMapping(params = "updateUser")
    @ResponseBody
    public Map<String,Object> updateUser(String userName, String email, String nickName,
                                         String password, String phoneNumber, int sex,
                                         String birthday, String postNumber, String address){
        String result = "";
        User user = userService.getUser(userName);
        if(user == null){
            result = "fail";
        }else {
            user.setUserName(userName);
            user.setEmail(email);
            user.setNickName(nickName);
            userService.updateUser(user);

            UserDetail userDetail = userDetailService.getUserDetail(user.getId());
            userDetail.setAddress(address);
            userDetail.setBirthday(birthday);
            userDetail.setPassword(password);
            userDetail.setPhoneNumber(phoneNumber);
            userDetail.setSex(sex);
            userDetail.setPostNumber(postNumber);
            userDetailService.updateUserDetail(userDetail);
            result = "success";
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @GetMapping(params = "getAllUser")
    @ResponseBody
    public Map<String,Object> getAllUser(){
        List<User> userList = new ArrayList<>();
        userList = userService.getAllUser();
        String allUsers = JSONArray.toJSONString(userList);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("allUsers",allUsers);
        return resultMap;
    }



    //delete: http://127.0.0.7:8080/user/2
    @DeleteMapping("/{id}")
    @ResponseBody
    public Response doDelete(@PathVariable int id){
        return userService.deleteUser(id);
    }

}
