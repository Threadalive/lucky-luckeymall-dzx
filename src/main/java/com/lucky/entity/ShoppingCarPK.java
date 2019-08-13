package com.lucky.entity;

import java.io.Serializable;

/**
 * @Description 购物车主键类
 *
 * @Author zhenxing.dong
 * @Date 2019/8/9 15:29
 */
public class ShoppingCarPK implements Serializable {
    private int userId;
    private int productId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCarPK)) return false;

        ShoppingCarPK that = (ShoppingCarPK) o;

        if (userId != that.userId) return false;
        return productId == that.productId;

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + productId;
        return result;
    }
}
