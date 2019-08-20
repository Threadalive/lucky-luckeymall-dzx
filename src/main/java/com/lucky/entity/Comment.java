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
    /**
     * 用户id
     */
    private int userId;
    /**
     * 商品id
     */
    private int productId;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 评价内容
     */
    private String content;

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

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
