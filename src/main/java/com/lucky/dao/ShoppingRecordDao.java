package com.lucky.dao;

import com.lucky.entity.ShoppingRecord;

import java.util.List;

public interface ShoppingRecordDao {
    ShoppingRecord getShoppingRecord(int userId, int productId, String time);

    void addShoppingRecord(ShoppingRecord shoppingRecord);

    boolean deleteShoppingRecord(int userId,int productId);

    /**
     * 更新用户订单记录
     *
     * @param shoppingRecord 用户订单记录对象
     * @return boolean true:更新成功 false:更新失败
     */
    boolean updateShoppingRecord(ShoppingRecord shoppingRecord);

    /**
     * 通过用户id获取订单记录表
     *
     * @param userId 用户id
     * @return 订单记录列表
     */
    List<ShoppingRecord> getShoppingRecords(int userId);

    /**
     * 获取全部订单记录表
     *
     * @return 订单记录列表
     */
    List<ShoppingRecord> getAllShoppingRecords();


    List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus);

    boolean getUserProductRecord(int userId,int productId);

    boolean deleteShoppingRecordByUser(int userId);

    boolean deleteShoppingRecordByProductId(int productId);
}
