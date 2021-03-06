package com.lucky.entity;

import java.io.Serializable;

/**
 * @Description 定义购物记录表的符合主键。
 * @Author zhenxing.dong
 * @Date 2019/8/7 09:11
 */
public class ShoppingRecordPK implements Serializable {
    /**
     * 种子
     */
    private final static int SALT = 31;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = SALT * result + getProductId();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShoppingRecordPK)) {
            return false;
        }

        ShoppingRecordPK that = (ShoppingRecordPK) obj;

        if (getUserId() != that.getUserId()) {
            return false;
        }
        if (getProductId() != that.getProductId()) {
            return false;
        }
        return getCreateTime() == (that.getCreateTime());
    }

}
