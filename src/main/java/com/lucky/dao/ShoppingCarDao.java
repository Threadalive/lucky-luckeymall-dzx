package com.lucky.dao;

import com.lucky.entity.ShoppingCar;

import java.util.List;

/**
 * @Description 购物车的操作层接口，定义购物车的增删改查方法。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/2 22:16
 */
public interface ShoppingCarDao {
    /**
     * 根据用户id与商品id查询指定购物车记录
     *
     * @param userId 用户id
     * @param productId 商品id
     * @return 指定购物车对象
     */
    ShoppingCar getShoppingCar(int userId, int productId);

    /**
     * 向指定购物车添加商品项
     *
     * @param shoppingCar 具体要添加的购物车项
     */
    void addShoppingCar(ShoppingCar shoppingCar);

    /**
     * 根据用户id与商品id删除指定的购物车项
     *
     * @param userId 用户id
     * @param productId 商品id
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteShoppingCar(int userId,int productId);

    /**
     * 更新对应的购物车项
     *
     * @param shoppingCar 要更新的新的购物车项
     * @return boolean true:更新成功 false:更新失败
     */
    boolean updateShoppingCar(ShoppingCar shoppingCar);

    /**
     * 根据指定用户id获取指定购物车对象
     *
     * @param userId 用户id
     * @return 指定的购物车对象集
     */
    List<ShoppingCar> getShoppingCars(int userId);

    /**
     * 根据用户id删除该用户所有购物车对象
     *
     * @param userId 用户id
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteShoppingCarByUserId(int userId);

    /**
     * 根据商品id删除该商品对应的所有购物车对象
     *
     * @param productId 商品id
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteShoppingCarByProduct(int productId);
}
