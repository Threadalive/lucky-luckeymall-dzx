package com.lucky.controller;

import com.lucky.entity.User;
import com.lucky.entity.UserDetail;
import com.lucky.service.UserDetailService;
import com.lucky.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 用户登陆控制类，用户访问路径/login时访问此控制器。
 *
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/1 15:44
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 用户基本信息信息的服务类
     */
    @Resource
    UserService userService;

    /**
     * 用户详细信息信息的服务类
     */
    @Resource
    UserDetailService userDetailService;



    /**
     * 当用户get请求访问/login时，分配到这个方法，返回一个登陆页面的视图。
     * URL如：http://127.0.0.1:8080/login/
     *
     * @return org.springframework.web.servlet.ModelAndView
     * @author zhenxing.dong@luckincoffee.com
     */
    @GetMapping
    public ModelAndView login(){
        ModelAndView view=new ModelAndView();
        view.setViewName("userView/login");
        return  view;
    }


    /**
     * 根据用户输入的用户名或邮箱，查询数据库，若有匹配，则校验密码，通过则
     * 将结果设为success返回给前台。
     *
     * @param nameOrEmail 用户输入用户名或邮箱
     * @param password 用户输入的密码
     * @param httpSession session暂存用户信息
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author zhenxing.dong@luckincoffee.com
     */
    @PostMapping
    @ResponseBody
    public Map<String,Object> doLogin(String nameOrEmail, String password, HttpSession httpSession){
        String result = "fail";
        User user = userService.getUser(nameOrEmail);
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
