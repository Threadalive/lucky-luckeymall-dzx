package com.lucky.dao;

import com.lucky.entity.ScoreDetail;

import java.util.List;

public interface ScoreDetailDao {
    /**
     * 根据用户id获取其积分明细表
     * @param userId 用户id
     * @return 查询的记录集
     */
    List<ScoreDetail> getScoreDetail(int userId);

    /**
     * 添加积分明细记录
     *
     * @param scoreDetail 明细记录对象
     */
    void addScoreDetail(ScoreDetail scoreDetail);

    /**
     * 根据用户id和创建时间删除指定记录项
     *
     * @param userId 用户id
     * @param createTime 创建时间
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteScoreDetail(int userId,long createTime);

    /**
     * 更新积分记录
     *
     * @param scoreDetail
     * @return boolean true: 更新成功 false:更新失败
     */
    boolean updateScoreDetail(ScoreDetail scoreDetail);

}
