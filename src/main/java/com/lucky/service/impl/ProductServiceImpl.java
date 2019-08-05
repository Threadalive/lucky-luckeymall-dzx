package com.lucky.service.impl;

import com.lucky.dao.ProductDao;
import com.lucky.entity.Product;
import com.lucky.service.ProductService;
import com.lucky.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 产品管理类的服务类，包括对商品的增删改查功能。
 *
 * @Author zhenxing.dong@luckincoffee.com
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
    public Product getProduct(int id) {
        return productDao.getProduct(id);
    }

    @Override
    public Product getProduct(String productName) {
        return productDao.getProduct(productName);
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
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
    public List<Product> getProductsByKeyWord(String searchKeyWord) {
        return productDao.getProductsByKeyWord(searchKeyWord);
    }

    @Override
    public List<Product> getProductsByType(int type) {
        return productDao.getProductsByType(type);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }
}
