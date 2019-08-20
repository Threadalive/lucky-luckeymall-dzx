package com.lucky.dao;

import com.lucky.entity.Product;
import java.util.List;

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
     * @param product
     * @return void
     */
    void addProduct(Product product);

    /**
     * 实现根据商品id 删除指定商品。
     *
     * @param id 商品id
     * @return boolean
     */
    boolean deleteProduct(int id);

    /**
     * 实现商品信息的更新。
     *
     * @param product 更新的商品对象
     * @return boolean
     */
    boolean updateProduct(Product product);

    /**
     * 实现根据关键字列出相关商品，以List<Product>形式返回。
     *
     * @param searchKeyWord 搜索关键字
     * @return java.util.List<com.lucky.entity.Product>
     */
    List<Product> getProductsByKeyWord(String searchKeyWord);

    /**
     * 实现根据商品类型列出相关商品，以List<Product>形式返回。
     *
     * @param type 商品类型
     * @return java.util.List<com.lucky.entity.Product>
     */
    List<Product> getProductsByType(int type);

    /**
     * 实现所有商品的展示，以List<Product>形式返回。
     *
     * @return java.util.List<com.lucky.entity.Product>
     */
    List<Product> getAllProduct();

    /**
     * 获取商品数目
     *
     * @return 商品数目
     */
    int countProduct();
}
