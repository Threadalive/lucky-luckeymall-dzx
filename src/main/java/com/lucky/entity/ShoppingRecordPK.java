package com.lucky.entity;

import java.io.Serializable;

/**
 * @Description 定义购物记录表的符合主键
 * @Author zhenxing.dong
 * @Date 2019/8/7 09:11
 */
public class ShoppingRecordPK implements Serializable {
    private int userId;
    private int productId;
    private String createTime;


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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = 31 * result + getProductId();
        result = 31 * result + getCreateTime().hashCode();
        return result;    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ShoppingRecordPK)) return false;

        ShoppingRecordPK that = (ShoppingRecordPK) obj;

        if (getUserId() != that.getUserId()) return false;
        if (getProductId() != that.getProductId()) return false;
        return getCreateTime().equals(that.getCreateTime());
    }

}
