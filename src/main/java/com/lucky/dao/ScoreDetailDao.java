package com.lucky.dao;

import com.lucky.entity.ScoreDetail;

import java.util.List;

/**
 * @Description 用户积分明细表的操作层接口，定义用户积分明细的增删改查方法。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/2 22:16
 */
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
     * @param scoreDetail 积分明细
     * @return boolean true: 更新成功 false:更新失败
     */
    boolean updateScoreDetail(ScoreDetail scoreDetail);

    /**
     * 获取所有积分明细
     *
     * @param offset 起始位置
     * @param length 长度
     * @return 积分明细列表
     */
    List<ScoreDetail> getAllScoreDetail(int offset,int length);

    /**
     * 获取明细数
     *
     * @return 明细数量
     */
    int getScoreDetailCount();

    /**
     * 根据用户id与记录描述获取指定记录集
     *
     * @param userId 用户id
     * @param itemName 记录描述
     * @return 记录集
     */
    List<ScoreDetail> getScoreDetailByUserIdAndItemName(int userId,String itemName);
}
