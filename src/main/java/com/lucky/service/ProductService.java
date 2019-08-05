package com.lucky.service;

import com.lucky.entity.Product;
import com.lucky.util.Response;

import java.util.List;

public interface ProductService {
    Product getProduct(int id);

    Product getProduct(String name);

    void addProduct(Product product);

    Response deleteProduct(int id);

    boolean updateProduct(Product product);

    List<Product> getProductsByKeyWord(String searchKeyWord);

    List<Product> getProductsByType(int type);

    List<Product> getAllProduct();
}
