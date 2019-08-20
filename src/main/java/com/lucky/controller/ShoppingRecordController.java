package com.lucky.controller;

import com.lucky.entity.ShoppingRecord;
import com.lucky.service.ShoppingRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 订单记录控制
 *
 * @Author zhenxing.dong
 * @Date 2019/8/7 09:46
 */
@Controller
@RequestMapping("/shoppingRecord")
public class ShoppingRecordController {

    /**
     * 日志服务
     */
    private static final Logger logger = LoggerFactory.getLogger(ShoppingRecordController.class);

    /**
     * 订单记录的服务类
     */
    @Autowired
    private ShoppingRecordService shoppingRecordService;

    /**
     * 返回给前台订单一个订单记录显示页面
     *
     * @return 订单记录显示视图
     */
    @GetMapping(params = "showShoppingRecord")
    public ModelAndView getShoppingRecord() {
        ModelAndView view = new ModelAndView();
        view.setViewName("userView/shopping_record");
        return view;
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

    /**
     * 根据用户id获取订单记录对象，结果以JSON字符串对象返回给前台
     *
     * @param userId 用户id
     * @return 包含订单记录的JSON字符串对象
     */
    @PostMapping(params = "getShoppingRecords")
    @ResponseBody
    public Map<String,Object> getShoppingRecords(int userId){
        logger.info("传入用户id为:"+userId);
        return shoppingRecordService.getShoppingRecords(userId);
    }

    /**
     * 根据订单记录的3个主键确定并更新订单
     *
     * @param shoppingRecord 指定订单记录
     * @return 更新结果
     */
    @PostMapping(params = "updateShoppingRecords")
    @ResponseBody
    public Map<String,Object> updateShoppingRecords(ShoppingRecord shoppingRecord){
        logger.info("要更新订单记录的用户id为:"+shoppingRecord.getUserId());
        return shoppingRecordService.updateShoppingRecord(shoppingRecord);
    }

    /**
     * 用户下单后添加订单记录项
     *
     * @param shoppingRecord 新的订单记录项
     * @return 添加结果
     */
    @PostMapping(params = "addShoppingRecord")
    @ResponseBody
    public  Map<String,Object> addShoppingRecord(ShoppingRecord shoppingRecord){
        logger.info("要添加订单记录的用户id为:"+shoppingRecord.getUserId());
        return shoppingRecordService.addShoppingRecord(shoppingRecord);
    }

    /**
     * 根据订单状态获取订单记录
     *
     * @param orderStatus 订单状态
     * @return 指定状态的订单记录集
     */
    @PostMapping(params = "getShoppingRecordByOrderStatus")
    @ResponseBody
    public Map<String,Object> getShoppingRecordByOrderStatus(int orderStatus){
        logger.info("查询订单的订单状态为:"+orderStatus);
        if(orderStatus!=0&&orderStatus!=1&&orderStatus!=2){
            return null;
        }else
            {
            return shoppingRecordService.getShoppingRecordByOrderStatus(orderStatus);
        }
    }

    /**
     * 获取所有订单记录项
     *
     * @return 所有状态记录集
     */
    @PostMapping(params = "getAllShoppingRecords")
    @ResponseBody
    public Map<String, Object> getAllShoppingRecords() {
        return shoppingRecordService.getAllShoppingRecords();
    }

    /**
     * 通过用户id和商品id判断订单是否存在
     *
     * @param userId 用户id
     * @param productId 商品id
     * @return 判断结果
     */
    @PostMapping(params = "getUserProductRecord")
    @ResponseBody
    public Map<String,Object> getUserProductRecord(int userId,int productId){
        logger.info("查询订单的用户id与产品id分别是:"+userId+'\n'+productId);
        String result = "false";
        if(shoppingRecordService.getUserProductRecord(userId,productId)){
            result = "true";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * 根据用户id、商品id删除指定订单
     *
     * @param userId 用户id
     * @param productId 商品id
     * @return 删除结果
     */
    @PostMapping(params = "deleteShoppingRecord")
    @ResponseBody
    public Map<String,Object> deleteShoppingRecord(int userId, int productId){
        logger.info("指定订单的用户id与产品id分别是:"+userId+'\n'+productId);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(shoppingRecordService.deleteShoppingRecord(userId,productId)){
            resultMap.put("result","success");
        }else {
            resultMap.put("result","fail");
        }
        return resultMap;
    }
}


