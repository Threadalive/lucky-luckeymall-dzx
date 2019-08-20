package com.lucky.service.impl;

import com.lucky.dao.ScoreDetailDao;
import com.lucky.dao.UserDetailDao;
import com.lucky.entity.ScoreDetail;
import com.lucky.service.ScoreDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 积分明细表的服务实现类
 *
 * @Author zhenxing.dong
 * @Date 2019/8/19 17:16
 */
@Service
public class ScoreDetailServiceImpl implements ScoreDetailService {
    /**
     * 积分明细操作层
     */
    @Autowired
    ScoreDetailDao scoreDetailDao;
    /**
     * 用户信息的dao类
     */
    @Autowired
    UserDetailDao userDetailDao;

    @Transactional
    @Override
    public Map<String, Object> addScoreDetail(ScoreDetail scoreDetail) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            //设置时间，更改用户表
            scoreDetail.setCreateTime(System.currentTimeMillis());
            scoreDetail.setExpend(0);
            //设置明细表当前总积分
            scoreDetail.setScore(userDetailDao.getUserScore(scoreDetail.getUserId())+scoreDetail.getIncome());

            scoreDetailDao.addScoreDetail(scoreDetail);
            //更新用户表积分值
            userDetailDao.updateUserScore(scoreDetail.getUserId(),scoreDetail.getScore());

            resultMap.put("result","success");
            return resultMap;
        } catch (Exception e) {
            resultMap.put("result","fail");
            return resultMap;
        }
    }


}
