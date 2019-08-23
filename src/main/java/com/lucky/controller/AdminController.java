package com.lucky.controller;

import com.lucky.entity.PageBean;
import com.lucky.service.ProductService;
import com.lucky.service.ScoreDetailService;
import com.lucky.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 后台的控制器
 *
 * @Author zhenxing.dong
 * @Date 2019/8/20 17:54
 */
@Controller
@RequestMapping("/adminControl")
public class AdminController {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    /**
     * 用户信息服务类
     */
    @Autowired
    private UserService userService;

    /**
     * 商品管理服务类
     */
    @Autowired
    private ProductService productService;

    /**
     * 积分明细管理的服务类
     */
    @Autowired
    private ScoreDetailService scoreDetailService;

    /**
     * 给前台返回一个控制台页面
     *
     * @param request 请求
     * @return 控制台页面
     */
    @GetMapping(params = "control")
    public ModelAndView control(HttpServletRequest request){
        ModelAndView view=new ModelAndView();

        //设置用户页面信息
        int count = userService.count();
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(6);
        pageBean.setTotalRecords(count);
        request.setAttribute("page",pageBean);
        LOGGER.info("设置用户信息页面"+pageBean);

        //设置商品页面信息
        int count1 = productService.count();
        PageBean pageBean1 = new PageBean();
        pageBean1.setPageSize(6);
        pageBean1.setTotalRecords(count1);
        request.setAttribute("page1",pageBean1);
        LOGGER.info("设置商品信息页面"+pageBean1);

        //设置积分详情的页面信息
        int count2 = scoreDetailService.count();
        PageBean pageBean2 = new PageBean();
        pageBean2.setPageSize(6);
        pageBean2.setTotalRecords(count2);
        request.setAttribute("page2",pageBean2);
        LOGGER.info("设置积分信息页面"+pageBean2);

        view.setViewName("admin/control");
        return  view;
    }

    /**
     * 返回给前台订单一个订单记录管理页面
     *
     * @return 订单记录管理视图
     */
    @GetMapping(params = "shoppingRecordHandle")
    public ModelAndView getShoppingHandle() {
        ModelAndView view = new ModelAndView();
        view.setViewName("admin/shopping_handle");
        return view;
    }
}
