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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    UserDetailService userDetailService;



    //http://127.0.0.1:8080/user?register
    @GetMapping(params = "register")
    public ModelAndView register(){

        ModelAndView view=new ModelAndView();
        view.addObject("user",new User());
        view.setViewName("register");

       // ${user.userName}
        //restful 风格
        return  view;
        // userService.addUser(user);
    }


    //http://127.0.0.1:8080/doRegister
    @PostMapping(params = "register")
    @ResponseBody
    public Map<String, Object> doRegister(String userName, String email,
                                          String nickName, String password,
                                          String phoneNumber, int sex, String birthday,
                                          String postNumber, String address){
        System.out.println(444444);

        String result = "fail";
        User user = userService.getUser(userName);
        if(user != null){
            result = "nameExist";
//            System.out.println(6666666);
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
//                System.out.println(23333333);
                user1 = userService.getUser(userName);

                UserDetail userDetail = new UserDetail();
                userDetail.setId(user1.getId());
                userDetail.setPassword(password);
                userDetail.setPhoneNumber(phoneNumber);
                userDetail.setSex(sex);
                userDetail.setBirthday(birthday);
                userDetail.setPostNumber(postNumber);
                userDetail.setAddress(address);
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                userDetail.setRegisterTime(simpleDateFormat.format(date));

                userDetailService.addUserDetail(userDetail);
                result = "succees";
            }
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }



    //delete: http://127.0.0.7:8080/user/2
    @DeleteMapping("/{id}")
    @ResponseBody
    public Boolean doRegister(@PathVariable Integer id){

        //userService.de(user);
        return true;
    }

}
