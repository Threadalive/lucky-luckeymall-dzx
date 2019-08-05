package com.lucky.service;

import com.lucky.entity.Product;
import com.lucky.util.Response;

import java.util.List;

public interface ProductService {
    /**
     * 实现根据产品id获取指定产品。
     *
     * @param id 产品id
     * @return com.lucky.entity.Product
     * @author zhenxing.dong@luckincoffee.com
     */
    Product getProduct(int id);

    /**
     * 实现根据产品名获取指定产品对象。
     *
     * @param productName 产品名称
     * @return com.lucky.entity.Product
     * @author zhenxing.dong@luckincoffee.com
     */
    Product getProduct(String productName);

    /**
     * 实现添加商品对象。
     *
     * @param product 产品对象
     * @return void
     * @author zhenxing.dong@luckincoffee.com
     */
    void addProduct(Product product);

    /**
     * 实现根据指定id删除对应商品。
     *
     * @param id
     * @return com.lucky.util.Response
     * @author zhenxing.dong@luckincoffee.com
     */
    Response deleteProduct(int id);

    /**
     * 实现商品信息更新。
     *
     * @param product 更新的产品对象
     * @return boolean true:更新成功 false:更新失败
     * @author zhenxing.dong@luckincoffee.com
     */
    boolean updateProduct(Product product);

    /**
     * 实现根据关键字搜索相关产品。
     *
     * @param searchKeyWord 搜索的关键字
     * @return java.util.List<com.lucky.entity.Product>
     * @author zhenxing.dong@luckincoffee.com
     */
    List<Product> getProductsByKeyWord(String searchKeyWord);

    /**
     * 实现根据产品的类型搜索指定产品。
     *
     * @param type 产品类型
     * @return java.util.List<com.lucky.entity.Product>
     * @author zhenxing.dong@luckincoffee.com
     */
    List<Product> getProductsByType(int type);

    /**
     * 实现获取所有产品。
     *
     * @return java.util.List<com.lucky.entity.Product>
     * @author zhenxing.dong@luckincoffee.com
     */
    List<Product> getAllProduct();
}
