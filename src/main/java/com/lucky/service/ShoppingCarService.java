package com.lucky.service;

import com.lucky.entity.ShoppingCar;
import java.util.Map;


public interface ShoppingCarService {
    /**
     * 根据用户id、商品id获取购物车对象
     * @param userId 用户id
     * @param productId 商品id
     * @return 指定的购物车对象
     */
    Map<String,Object> getShoppingCar(int userId, int productId);

    /**
     * 添加购物车项
     *
     * @param shoppingCar 要添加的购物车项
     */
    Map<String,Object> addShoppingCar(ShoppingCar shoppingCar);

    /**
     * 根据用户id、商品id删除指定的购物车对象
     * @param userId 用户id
     * @param productId 商品id
     * @return 删除结果
     */
    Map<String,Object> deleteShoppingCar(int userId,int productId);

    /**
     *
     * @param shoppingCar 要更新的购物车对象
     * @return 更新结果
     */
    boolean updateShoppingCar(ShoppingCar shoppingCar);

    /**
     * 根据用户id获取指定购物车对象
     *
     * @param userId 用户id
     * @return 指定的购物车对象集
     */
    Map<String,Object> getShoppingCars(int userId);
}
