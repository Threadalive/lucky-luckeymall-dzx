/**
 * @Description 用户管理类
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/1 15:44
 */
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

@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    UserService userService;

    @Resource
    UserDetailService userDetailService;



//    http://127.0.0.1:8080/login/
    @GetMapping
    public ModelAndView login(){
        ModelAndView view=new ModelAndView();
        view.setViewName("login");
        return  view;
    }


    @PostMapping
    @ResponseBody
    public Map<String,Object> doLogin(String nameOrEmail, String password, HttpSession httpSession){
        String result = "fail";
        User user = userService.getUser(nameOrEmail);
        System.out.println(nameOrEmail);
        if(user == null){
            result = "nameUnexist";
        }else {
            UserDetail userDetail = userDetailService.getUserDetail(user.getId());
            if(password.equals(userDetail.getPassword())){
                result = "success";
                httpSession.setAttribute("currentUser",user);
            }else {
                result = "wrong";
            }
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",result);
        System.out.println(resultMap.get("result"));
        return resultMap;
    }
}
