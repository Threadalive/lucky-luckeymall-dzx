package com.lucky.dao;

import com.lucky.entity.Product;
import java.util.List;

/**
 * @Description 用户商品信息的操作层接口，定义商品信息的增删改查方法。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/2 22:16
 */
public interface ProductDao {
    /**
     * 实现根据产品id查询指定商品。
     *
     * @param id 产品id
     * @return com.lucky.entity.Product
     */
    Product getProduct(int id);

    /**
     * 实现根据商品名查询指定商品。
     *
     * @param productName 商品名
     * @return com.lucky.entity.Product
     */
    Product getProduct(String productName);

    /**
     * 实现商品添加。
     *
     * @param product 商品对象
     */
    void addProduct(Product product);

    /**
     * 实现根据商品id 删除指定商品。
     *
     * @param id 商品id
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteProduct(int id);

    /**
     * 实现商品信息的更新。
     *
     * @param product 更新的商品对象
     * @return boolean true:更新成功 false:更新失败
     */
    boolean updateProduct(Product product);

    /**
     * 实现根据关键字列出相关商品，以List<Product>形式返回。
     *
     * @param searchKeyWord 搜索关键字
     * @return 商品对象列表
     */
    List<Product> getProductsByKeyWord(String searchKeyWord);

    /**
     * 实现根据商品类型列出相关商品，以List<Product>形式返回。
     *
     * @param type 商品类型
     * @return 商品对象列表
     */
    List<Product> getProductsByType(int type);

    /**
     * 实现所有商品的展示，以List<Product>形式返回。
     *
     * @param offset 起始位置
     * @param length 长度
     * @return 商品列表
     */
    List<Product> getAllProduct(int offset,int length);

    /**
     * 获取商品最大id
     *
     * @return 商品数目
     */
    int countProduct();

    /**
     * 获取商品总数
     *
     * @return 商品数
     */
    int getProductCount();
}
