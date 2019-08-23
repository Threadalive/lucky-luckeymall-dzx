package com.lucky.service.impl;

import com.lucky.dao.CommentDao;
import com.lucky.dao.ShoppingCarDao;
import com.lucky.dao.ShoppingRecordDao;
import com.lucky.dao.UserDao;
import com.lucky.entity.PageBean;
import com.lucky.entity.User;
import com.lucky.entity.UserDetail;
import com.lucky.service.UserDetailService;
import com.lucky.service.UserService;
import com.lucky.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Author zhenxing.dong
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
     * 商品评论的dao层类
     */
    @Autowired
    private CommentDao commentDao;

    /**
     * 购物车的dao层类
     */
    @Autowired
    private ShoppingCarDao shoppingCarDao;

    /**
     * 订单的dao层类
     */
    @Autowired
    private ShoppingRecordDao shoppingRecordDao;
    /**
     * 用户细节信息管理的服务类.
     */
    @Autowired
    private UserDetailService userDetailService;

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

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
    public Map<String, Object> getUserById(int id) {
        User user = userDao.getUser(id);
        Map<String, Object> resultMap = new HashMap<String, Object>(1);
        resultMap.put("result", user);
        return resultMap;
    }

    /**
     * 事务执行，出错回滚防止删除部分数据
     */
    @Transactional
    @Override
    public Response deleteUser(int id) {
        //判断此用户是否存在购买记录、评价记录、购物车记录，如果存在，则应该先删除对应的记录，否则后续删除会出错
        try {
            commentDao.deleteCommentByUserId(id);
            shoppingRecordDao.deleteShoppingRecordByUser(id);
            shoppingCarDao.deleteShoppingCarByUserId(id);
            userDetailService.deleteUserDetail(id);
            userDao.deleteUser(id);
            //删除后设置对应状态
            return new Response(1, "删除成功", null);
        }catch (Exception e) {
            LOGGER.error("删除失败",e.getMessage());
            return new Response(0, "删除失败", null);
        }
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public PageBean getAllUser(int currentPage,int pageSize) {
        PageBean pageBean = new PageBean();
        int allRecord = userDao.getUserCount();
        int offset = pageBean.countOffset(currentPage,pageSize);
        //调取dao获取对应条数记录
        List<User> userList = userDao.getAllUser(offset,pageSize);
        pageBean.setPageSize(pageSize);
        pageBean.setPageNo(currentPage);
        pageBean.setTotalRecords(allRecord);
        pageBean.setList(userList);
        return pageBean;
    }

    @Override
    public Map<String, Object> getUserAddressAndPhoneNumber(int userId) {
        String address = userDetailService.getUserDetail(userId).getAddress();
        String phoneNumber = userDetailService.getUserDetail(userId).getPhoneNumber();

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("address",address);
        resultMap.put("phoneNumber",phoneNumber);
        return resultMap;
    }

    @Override
    public Map<String, Object> getUserDetailById(int id) {
        UserDetail userDetail = userDetailService.getUserDetail(id);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",userDetail);
        return resultMap;
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
                vUser.setRole(0);
                addUser(vUser);
                user = userDao.getUser(vUser.getId());
                //设置t_user_detail表的值
                vUserDetail.setId(user.getId());
                vUserDetail.setScore(0);
                //生成并设置当前时间戳
                vUserDetail.setRegisterTime(System.currentTimeMillis());
                userDetailService.addUserDetail(vUserDetail);
                LOGGER.info("用户信息:"+vUser.toString());
                //设置成功的话讲result设置为success
                result = "success";
            }
        }
        //将result结果作为键值对返回给前台
        Map<String, Object> resultMap = new HashMap<String, Object>(1);
        resultMap.put("result", result);
        return resultMap;
    }

    @Transactional
    @Override
    public Map<String,Object> updateUser(User vUser,UserDetail vUserDetail,String oldUserName){
        String result = "";
        User user = getUser(oldUserName);
        if(user == null){
            //若用户不存在，返回result为fail
            result = "fail";
        }else {
            //设置用户对象基本信息
            updateUser(vUser);
            userDetailService.updateUserDetail(vUserDetail);
            //设置成功将resul设置为success
            result = "success";
        }
        //将result结果作为键值对返回给前台
        Map<String, Object> resultMap = new HashMap<String, Object>(1);
        resultMap.put("result", result);
        return resultMap;
    }

    @Override
    public Map<String,Object> doLogin(String nameOrEmail, String password, HttpSession httpSession){
        String result = "fail";
        LOGGER.info(nameOrEmail);
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
                if(user.getRole()==1){
                    result = "admin";
                }
            }else {
                result = "wrong";
            }
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",result);
        resultMap.put("user1",user);
        return resultMap;
    }

    @Override
    public Map<String, Object> getUserCount() {
        Map<String,Object> resultMap = new HashMap<>();
        int userCount = userDao.getUserCount();
        resultMap.put("result",userCount);
        return resultMap;
    }

    @Override
    public int count() {
        return userDao.getUserCount();
    }
}
