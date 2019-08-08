package com.lucky.service;

import com.lucky.entity.Product;
import com.lucky.util.Response;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface ProductService {

    /**
     * 实现根据产品名获取指定产品对象。
     *
     * @param productName 商品名称
     * @return 具体商品对象
     */
    Product getProduct(String productName);

    /**
     * 实现添加商品对象。
     *
     * @param product 产品对象
     * @return void
     */
    Map<String, Object> addProduct(Product product);

    /**
     * 实现根据指定id删除对应商品。
     *
     * @param id
     * @return 执行操作状态
     */
    Response deleteProduct(int id);

    /**
     * 实现商品信息更新。
     *
     * @param product 更新的产品对象
     * @return boolean true:更新成功 false:更新失败
     */
    boolean updateProduct(Product product);

    /**
     * 根据商品关键字查询相关商品，信息以字符串形式返回给前台。
     *
     * @param searchKeyWord 用户搜索的关键字
     * @return 搜索结果
     */
    Map<String,Object> getProductsByKeyWord(String searchKeyWord);

    /**
     * 实现根据产品的类型搜索指定产品。
     *
     * @param type 产品类型
     * @return 商品列表
     */
    List<Product> getProductsByType(int type);

    /**
     * 实现获取所有产品。
     *
     * @return 商品列表
     */
    Map<String,Object> getAllProduct();

    /**
     * 根据指定id返回一个商品的商品详情信息。
     *
     * @param id          商品id
     * @param httpSession 临时缓存session
     * @return 执行结果
     */
    Map<String, Object> getProductDetail(int id, HttpSession httpSession);

    /**
     * 实现根据商品的id获取到指定商品，并将其商品信息以字符串形式返回。
     *
     * @param id 要查询的商品id
     * @return 搜索结果
     */
    Map<String, Object> getProductById(int id);
}
