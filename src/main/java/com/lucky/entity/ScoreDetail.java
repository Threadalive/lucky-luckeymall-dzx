package com.lucky.entity;

import javax.persistence.*;

/**
 * @Description 用户积分实体
 *
 * @Author zhenxing.dong
 * @Date 2019/8/19 15:55
 */
@Entity
@Table(name = "t_score_detail")
@IdClass(value = ScoreDetailPK.class)
public class ScoreDetail {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 用户积分
     */
    private int score;
    /**
     * 用户获得的积分
     */
    private int income;
    /**
     * 用户消耗的积分
     */
    private int expend;
    /**
     * 该活动项创建的时间
     */
    private long createTime;
    /**
     * 对应记录描述
     */
    private String itemName;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Column(name = "income")
    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    @Column(name = "expend")
    public int getExpend() {
        return expend;
    }

    public void setExpend(int expend) {
        this.expend = expend;
    }

    @Id
    @Column(name = "create_time")
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Column(name = "item_name")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
