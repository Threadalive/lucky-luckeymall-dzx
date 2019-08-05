/**
 * @Description TODO
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/5 00:43
 */
package com.lucky.service.impl;

import com.lucky.entity.Product;
import com.lucky.service.ProductService;
import com.lucky.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
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
    public Response deleteProduct(int id) {
        return null;
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
