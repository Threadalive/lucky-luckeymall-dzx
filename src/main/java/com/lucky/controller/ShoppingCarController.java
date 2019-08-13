package com.lucky.controller;

import com.lucky.entity.ShoppingCar;
import com.lucky.service.ShoppingCarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Description 用作用户购物车的控制类，负责购物车商品的增删改查等
 *
 * @Author zhenxing.dong
 * @Date 2019/8/12 00:07
 */
@Controller
@RequestMapping("/shoppingCar")
public class ShoppingCarController {

    @Autowired
    ShoppingCarService shoppingCarService;

    /**
     * 日志对象
     */
    private static final Logger logger = Logger.getLogger(ShoppingCarController.class);

    /**
     * 返回给前台购物车页面
     *
     * @return 购物车视图
     */
    @GetMapping
    public ModelAndView getShoppingRecord() {
        ModelAndView view = new ModelAndView();
        view.setViewName("userView/shopping_car");
        return view;
    }

    /**
     * 添加购物车功能的控制类，负责调用对应服务层方法实现具体功能。
     *
     * @param shoppingCar 要添加的购物车
     * @return 添加结果
     */
    @PostMapping(params = "addShoppingCar")
    @ResponseBody
    public Map<String,Object> addShoppingCar(ShoppingCar shoppingCar){
        if (shoppingCar!=null) {
            return shoppingCarService.addShoppingCar(shoppingCar);
        }else {
            logger.error("传入的购物车信息为空！！！！");
            return null;
        }
    }

    /**
     * 根据用户id获取指定购物车对象
     *
     * @param userId 用户id
     * @return 获取购物车对象的结果
     */
    @PostMapping(params = "getShoppingCarById")
    @ResponseBody
    public Map<String,Object> getShoppingCars(int userId){
        return shoppingCarService.getShoppingCars(userId);
    }

    /**
     * 根据用户id、商品id删除指定购物车记录
     * @param userId 用户id
     * @param productId 商品id
     * @return 删除结果
     */
    @PostMapping(params = "deleteShoppingCar")
    @ResponseBody
    public Map<String,Object> deleteShoppingCar(int userId,int productId){
        return shoppingCarService.deleteShoppingCar(userId,productId);
    }
}
