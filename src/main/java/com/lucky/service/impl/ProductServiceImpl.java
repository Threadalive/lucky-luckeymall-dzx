package com.lucky.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.lucky.dao.ProductDao;
import com.lucky.entity.Product;
import com.lucky.service.ProductService;
import com.lucky.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 产品管理类的服务类，包括对商品的增删改查功能。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/5 00:43
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * 用于操作产品类数据库的dao层。
     */
    @Autowired
    ProductDao productDao;

    @Override
    public Product getProduct(String productName) {
        return productDao.getProduct(productName);
    }

    @Override
    public Map<String, Object> addProduct(Product product) {
        String result = "fail";
        productDao.addProduct(product);
        Map<String, Object> resultMap = new HashMap<>();
        result = "success";
        resultMap.put("result", result);
        return resultMap;
    }

    @Override
    public Response deleteProduct(int id) {
        /**
         * 待补
         */
        return null;
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public Map<String, Object> getProductsByKeyWord(String searchKeyWord) {
        List<Product> productList = new ArrayList<>();
        productList = productDao.getProductsByKeyWord(searchKeyWord);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", productList);
        return resultMap;
    }

    @Override
    public List<Product> getProductsByType(int type) {
        return productDao.getProductsByType(type);
    }

    @Override
    public Map<String, Object> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        productList = productDao.getAllProduct();
//        String allProducts = JSONArray.toJSONString(productList);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("allProducts", productList);
        return resultMap;    }

    @Override
    public Map<String, Object> getProductDetail(int id, HttpSession httpSession) {
        Product product = productDao.getProduct(id);
        httpSession.setAttribute("productDetail", product);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "success");
        return resultMap;
    }

    @Override
    public Map<String, Object> getProductById(int id) {
        Product product = productDao.getProduct(id);
//        String searchResult = JSONArray.toJSONString(product);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", product);
        return resultMap;
    }
}
