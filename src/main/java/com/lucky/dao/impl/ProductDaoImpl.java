/**
 * @Description TODO
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/5 00:45
 */
package com.lucky.dao.impl;

import com.lucky.dao.ProductDao;
import com.lucky.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product getProduct(int id) {
        return null;
    }

    @Override
    public Product getProduct(String name) {
        return null;
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public List<Product> getProductsByKeyWord(String searchKeyWord) {
        return null;
    }

    @Override
    public List<Product> getProductsByType(int type) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }
}
