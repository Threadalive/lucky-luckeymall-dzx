package com.lucky.controller;

import com.lucky.entity.PageBean;
import com.lucky.entity.User;
import com.lucky.entity.UserDetail;
import com.lucky.service.UserService;
import com.lucky.util.HttpSenderMain;
import com.lucky.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Description 该类为用户管理类，拥有控制新用户注册，以及用户
 * 信息的更新，用户信息的获取，以及用户的删除几项功能。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/1 15:44
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    /**
     * 用户服务类，供控制器调用
     */
    @Resource
    private UserService userService;

    /**
     * 获取客户端get请求，参数为main时，返回主界面视图。
     * 输入URL为：http://127.0.0.1:8080/user?main
     *
     * @return 视图对象
     */
    @GetMapping(params = "main")
    public ModelAndView main(){
        ModelAndView view=new ModelAndView();
        view.setViewName("main");
        return  view;
    }

    /**
     * 返回给前台一个个人中心页面
     *
     * @return 个人中心页
     */
    @GetMapping(params = "updateSelfInfo")
    public ModelAndView updateSelfInfo(){
        ModelAndView view=new ModelAndView();
        view.setViewName("/userView/update_selfinfo");
        return  view;
    }

    /**
     * 获取客户端get请求，参数为register时，返回注册界面视图。
     * 输入URL为：http://127.0.0.1:8080/user?register
     *
     * @return 视图对象
     */
    @GetMapping(params = "register")
    public ModelAndView register(){
        ModelAndView view=new ModelAndView();

        view.setViewName("/userView/register");
        return  view;
    }

    /**
     * 实现注册方法，根据用户名与邮箱判断用户是否已注册，
     * 不存在则调用service与dao层相关方法进行数据库
     * 添加注册。
     *
     * @param vUser 基本用户参数
     * @param vUserDetail  详细用户参数
     * @return 注册结果
     */
    @PostMapping(params = "register")
    @ResponseBody
    public Map<String, Object> doRegister(User vUser,UserDetail vUserDetail){
        LOGGER.info("=========传入参数："+vUser+"\n"+vUserDetail+"==============");
        //参数处理判断
        if(vUser!=null&&vUserDetail!=null) {
            return userService.doRegister(vUser, vUserDetail);
        }else {
            LOGGER.error("==============传入参数为空==============");
            return null;
        }
    }

    /**
     * 短信发送方法
     *
     * @param phoneNum 发送的手机号
     * @return 验证码
     */
    @PostMapping(params = "sendMsg")
    @ResponseBody
    public Map<String, Object> sengMsg(String phoneNum){
       Map<String,Object> resultMap = new HashMap<>(1);
        String num = HttpSenderMain.sendMessage(phoneNum);
        if("".equals(num)||num == null){
            resultMap.put("result","fail");
        }else {
            resultMap.put("result", num);
        }
        return resultMap;
    }


    /**
     * 实现用户信息更新的方法，根据用户名判断用户是否
     * 已注册，存在则调用service与dao层相关方法进
     * 行数据库更新。
     *
     * @param vUser  基本用户参数
     * @param vUserDetail 详细用户参数
     * @param oldUserName 原先的用户名
     * @return  更新结果
     */
    @PostMapping(params = "updateUser")
    @ResponseBody
    public Map<String,Object> updateUser(User vUser,UserDetail vUserDetail,String oldUserName){
        LOGGER.info("=========传入参数："+vUser+"\n"+vUserDetail+"==============");
        return userService.updateUser(vUser,vUserDetail,oldUserName);
    }

    /**
     * 获取所有的用户信息以页面对象传给前台。
     *
     * @param request 请求
     * @return 用户页面对象
     */
    @PostMapping(params = "getAllUser")
    @ResponseBody
    public PageBean getAllUser(HttpServletRequest request){
        String pageNo = request.getParameter("pageNo");
        if(pageNo == null){
            pageNo = "1";
        }
        //指定大小的用户页面对象
        PageBean pageBean = userService.getAllUser(Integer.valueOf(pageNo), 6);

        return pageBean;
    }

    /**
     * 根据用户id获取具体用户对象
     *
     * @param id 用户id
     * @return 指定id的用户对象
     */
    @PostMapping(params = "getUserById")
    @ResponseBody
    public Map<String, Object> getUserById(int id) {
        if(id!=0) {
            LOGGER.info("传入用户id为:"+id);
            return userService.getUserById(id);
        }else {
            return null;
        }
    }

    /**
     * 根据用户id获取具体用户对象
     *
     * @param userName 用户名
     * @return 指定id的用户对象
     */
    @PostMapping(params = "getUserByName")
    @ResponseBody
    public Map<String, Object> getUserByName(String userName) {
        Map<String,Object> resultMap = new HashMap<>();
        if(!"".equals(userName)) {
            LOGGER.info("传入用户id为:"+userName);
            User user = userService.getUser(userName);
            if(user == null){
                resultMap.put("result","userNoExist");
            }else {
                resultMap.put("result", user);
            }
            return resultMap;
        }else {
            resultMap.put("result","listAll");
            return resultMap;
        }
    }
    /**
     * delete请求控制用户信息的删除，根据URL中传入的参数id，删除对应id的用户。
     * URL如 delete: http://127.0.0.7:8080/user/2，即删除id为2的用户。
     *
     * @param id 用户id
     * @return 执行状态
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Response doDelete(@PathVariable int id){
        LOGGER.info("传入用户id为:"+id);
        return userService.deleteUser(id);
    }

    /**
     * 根据用户id获取他的地址和手机号码
     *
     * @param userId 用户id
     * @return 用户地址，用户手机号码
     */
    @PostMapping(params = "getUserAddressAndPhoneNumber")
    @ResponseBody
    public Map<String,Object> getUserAddressAndPhoneNumber(int userId){
        LOGGER.info("传入用户id为:"+userId);
        return userService.getUserAddressAndPhoneNumber(userId);
    }

    /**
     * 清除session中currentUser属性实现退出登录功能
     *
     * @param httpSession 当前数据暂存区
     * @return 退出登录结果
     */
    @PostMapping(params = "doLogout")
    @ResponseBody
    public Map<String,Object> doLogout(HttpSession httpSession){
        Map<String, Object> resultMap = new HashMap<>();
            httpSession.removeAttribute("currentUser");
            LOGGER.info("用户已退出");
            resultMap.put("result", "success");
            return resultMap;
    }

    /**
     * 记录要修改的用户的id
     * @param userId 用户id
     * @param httpSession 暂存对象
     * @return 暂存结果
     */
    @PostMapping(params = "setId")
    @ResponseBody
    public Map<String,Object> setUpdateId(int userId,HttpSession httpSession){
        Map<String, Object> resultMap = new HashMap<>();
        httpSession.setAttribute("alterUser",userId);
        resultMap.put("result", "success");
        return resultMap;
    }

    /**
     * 根据用户id获取用户详细信息
     *
     * @param id 用户id
     * @return 用户详细信息结果
     */
    @PostMapping(params = "getUserDetailById")
    @ResponseBody
    public Map<String, Object> getUserDetailById(int id) {
        LOGGER.info("传入用户id为:"+id);
        return userService.getUserDetailById(id);
    }

    /**
     * 获取用户数
     *
     * @return 用户数
     */
    @PostMapping(params = "getUserCount")
    @ResponseBody
    public Map<String, Object> getUserCount() {
        return userService.getUserCount();
    }
}
