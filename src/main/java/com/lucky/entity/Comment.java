package com.lucky.entity;

import javax.persistence.*;

/**
 * @Description 评价表的实体类
 *
 * @Author zhenxing.dong
 * @Date 2019/8/13 00:45
 */
@Entity
@Table(name = "t_comment")
@IdClass(value = ShoppingRecordPK.class)
public class Comment {
    private int userId;
    private int productId;
    private long createTime;

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
    @Column(name = "create_id")
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
