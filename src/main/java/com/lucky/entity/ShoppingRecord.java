package com.lucky.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Description 用户购物记录实体类，复合主键：
 * userId、productId与createTime
 *
 * @Author zhenxing.dong
 * @Date 2019/8/5 17:11
 */
@Entity
@Table(name = "t_shopping_record")
@IdClass(value = ShoppingRecordPK.class)
public class ShoppingRecord {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 产品id
     */
    private int productId;
    /**
     * 订单创建时间
     */
    private long createTime;
    /**
     * 订单状态
     */
    private int orderStatus;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 商品库存
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

    @Id
    @Column(name = "create_time")
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Column(name = "order_status")
    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
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
}
