package com.lucky.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Description 购物车实体类:复合主键userId与productId。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/9 15:28
 */
@Entity
@Table(name = "t_shopping_car")
@IdClass(value = ShoppingCarPK.class)
public class ShoppingCar {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 商品id
     */
    private int productId;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 数量
     */
    private int counts;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Column(name = "product_price")
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Column(name = "counts")
    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", productPrice=" + productPrice +
                ", counts=" + counts +
                '}';
    }
}
