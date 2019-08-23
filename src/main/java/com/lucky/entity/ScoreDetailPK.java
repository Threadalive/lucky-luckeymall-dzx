package com.lucky.entity;

import java.io.Serializable;

/**
 * @Description 积分表的主键类
 * @Author zhenxing.dong
 * @Date 2019/8/19 16:07
 */
public class ScoreDetailPK implements Serializable {
    /**
     * 种子
     */
    private final static int SALT = 31;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 该项创建时间
     */
    private long createTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        result = SALT * result;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScoreDetailPK)) {
            return false;
        }

        ScoreDetailPK that = (ScoreDetailPK) obj;

        if (getUserId() != that.getUserId()) {
            return false;
        }
        return getCreateTime() == (that.getCreateTime());
    }
}
