package com.lucky.controller;

import com.lucky.entity.ShoppingRecord;
import com.lucky.service.ShoppingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
        view.setViewName("userView/shopping_handle");
        return view;
    }

    /**
     * 添加订单信息
     *
     * @return 添加结果
     */
    @PostMapping(params = "addShoppingRecord")
    public Map<String,Object> addShoppingRecord(){
        return null;
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
        return shoppingRecordService.getShoppingRecords(userId);
    }

    @PostMapping(params = "updateShoppingRecords")
    @ResponseBody
    public Map<String,Object> updateShoppingRecords(ShoppingRecord shoppingRecord){
        return shoppingRecordService.updateShoppingRecord(shoppingRecord);
    }
}
