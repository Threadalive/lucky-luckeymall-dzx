package com.lucky.service;

import com.lucky.entity.ScoreDetail;

import java.util.Map;

public interface ScoreDetailService {
    /**
     * 添加积分记录项
     *
     * @param scoreDetail 积分明细记录
     * @return 添加结果
     */
    Map<String,Object> addScoreDetail(ScoreDetail scoreDetail);
}
