package com.lucky.service;

import com.lucky.entity.PageBean;
import com.lucky.entity.ScoreDetail;

import java.util.Map;

/**
 * @Description 积分明细表的服务实现类
 *
 * @Author zhenxing.dong
 * @Date 2019/8/19 17:16
 */
public interface ScoreDetailService {
    /**
     * 添加积分记录项
     *
     * @param scoreDetail 积分明细记录
     * @return 添加结果
     */
    Map<String,Object> addScoreDetail(ScoreDetail scoreDetail);

    /**
     * 获取积分记录
     *
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @return 积分明细记录页面
     */
    PageBean getAllScoreDetail(int currentPage, int pageSize);

    /**
     * 根据用户名或邮箱获取对应用户的积分记录
     *
     * @param userName 用户名
     * @return 积分记录
     */
    Map<String,Object> getScoreDetailByUser(String userName);

    /**
     * 明细记录数
     *
     * @return 明细记录数
     */
    int count();

}
