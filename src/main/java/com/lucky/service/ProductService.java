package com.lucky.service;

import com.lucky.entity.PageBean;
import com.lucky.entity.Product;
import com.lucky.util.Response;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Description 产品管理类的服务类，包括对商品的增删改查功能。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/5 00:43
 */
public interface ProductService {

    /**
     * 实现根据产品名获取指定产品对象。
     *
     * @param productName 商品名称
     * @return 具体商品对象
     */
    Product getProduct(String productName);

    /**
     * 商品对象添加
     *
     * @param product 商品对象
     * @param file 图片文件
     * @param request 请求
     * @return 上传结果
     */
    Map<String, Object> addProduct(Product product, MultipartFile file, HttpServletRequest request);

    /**
     * 实现根据指定id删除对应商品。
     *
     * @param id 商品id
     * @return 执行操作状态
     */
    Response deleteProduct(int id);

    /**
     * 实现商品信息更新。
     *
     * @param request 请求
     * @param file 图片文件
     * @param product 更新的产品对象
     * @return boolean true:更新成功 false:更新失败
     */
    boolean updateProduct(Product product, MultipartFile file, HttpServletRequest request);

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
     * 实现获取所有产品
     *
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @return 所有商品
     */
    PageBean getAllProduct(int currentPage, int pageSize);

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

    /**
     * 获取商品数
     *
     * @return 商品数
     */
    Map<String,Object> getProductCount();

    /**
     * 商品总数
     *
     * @return 商品数
     */
    int count();

}
