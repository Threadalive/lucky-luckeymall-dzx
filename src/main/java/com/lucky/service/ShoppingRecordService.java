package com.lucky.service;

import com.lucky.entity.ShoppingRecord;

import java.util.List;
import java.util.Map;

public interface ShoppingRecordService {
    /**
     * 根据复合主键查询指定订单
     *
     * @param userId 用户id
     * @param productId 商品id
     * @param createTime 订单创建时间
     * @return 订单记录对象
     */
    ShoppingRecord getShoppingRecord(int userId, int productId, String createTime);

    /**
     * 添加订单记录
     *
     * @param shoppingRecord 订单记录对象
     */
    void addShoppingRecord(ShoppingRecord shoppingRecord);

    /**
     * 根据用户id与产品id删除指定订单记录
     *
     * @param userId 用户id
     * @param productId 商品id
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteShoppingRecord(int userId,int productId);

    /**
     * 更新订单记录
     *
     * @param shoppingRecord 新的订单记录对象
     * @return boolean true:更新成功 false:更新失败
     */
    Map<String,Object> updateShoppingRecord(ShoppingRecord shoppingRecord);

    /**
     * 根据订单状态获取订单记录
     *
     * @param orderStatus 订单状态
     * @return 订单记录列表
     */
    List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus);

    /**
     * 根据用户id获取订单记录
     *
     * @param userId 用户id
     * @return Json字符串化的订单记录
     */
    Map<String,Object> getShoppingRecords(int userId);

    /**
     * 获取所有订单记录
     *
     * @return 订单记录列表
     */
    List<ShoppingRecord> getAllShoppingRecords();

    /**
     * 根据用户id与商品id查询订单是否存在
     *
     * @param userId 用户id
     * @param productId 商品id
     * @return boolean true:订单存在 false:订单不存在
     */
    boolean getUserProductRecord(int userId,int productId);
}
