package com.lucky.dao;

import com.lucky.entity.ShoppingRecord;

import java.util.List;

public interface ShoppingRecordDao {
    /**
     * 根据用户id，商品id，创建时间查询唯一的订单记录。
     *
     * @param userId 用户id
     * @param productId 商品id
     * @param createTime 创建时间
     * @return 订单记录
     */
    ShoppingRecord getShoppingRecord(int userId, int productId, long createTime);

    /**
     * 添加订单记录对象
     *
     * @param shoppingRecord 订单记录
     */
    void addShoppingRecord(ShoppingRecord shoppingRecord);

    /**
     * 根据用户id和商品id删除对应的订单信息。
     *
     * @param userId 用户id
     * @param productId 商品id
     * @return boolean true:删除成功 false:删除失败
     */
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


    /**
     * 根据订单状态查询对应的订单记录
     *
     * @param orderStatus 订单状态
     * @return 订单记录对象列表
     */
    List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus);

    /**
     * 根据用户id，商品id用户订单是否存在
     * @param userId 用户id
     * @param productId 商品id
     * @return boolean true:订单存在 false:订单不存在
     */
    boolean getUserProductRecord(int userId,int productId);

    /**
     * 根据用户id删除对应的订单信息
     *
     * @param userId 用户id
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteShoppingRecordByUser(int userId);

    /**
     * 根据商品id删除对应订单信息
     *
     * @param productId 商品id
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteShoppingRecordByProductId(int productId);
}
