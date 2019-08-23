package com.lucky.service.impl;

import com.lucky.dao.ScoreDetailDao;
import com.lucky.dao.UserDao;
import com.lucky.dao.UserDetailDao;
import com.lucky.entity.PageBean;
import com.lucky.entity.ScoreDetail;
import com.lucky.entity.User;
import com.lucky.service.ScoreDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 积分明细表的服务实现类
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

    /**
     * 用户操作dao类
     */
    @Autowired
    UserDao userDao;

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreDetailServiceImpl.class);

    @Transactional
    @Override
    public Map<String, Object> addScoreDetail(ScoreDetail scoreDetail) {
        Map<String, Object> resultMap = new HashMap<>();
        if (scoreDetail.getItemName().equals("登录赠送积分")) {
            int userId = scoreDetail.getUserId();
            String itemName = scoreDetail.getItemName();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf.format(new Date(System.currentTimeMillis())); // 时间戳转换日期
            LOGGER.info("当前日期" + currentDate);
            List<ScoreDetail> scoreDetailList = scoreDetailDao.getScoreDetailByUserIdAndItemName(userId, itemName);

            if (scoreDetailList.size() != 0) {
                //遍历记录查询是否存在时间在同一天的记录
                for (ScoreDetail sd : scoreDetailList) {
                    if (sdf.format(sd.getCreateTime()).equals(currentDate)) {
                        resultMap.put("result", "sameLogin");
                        return resultMap;
                    }
                }
            }
        }
        try {
            //设置时间，更改用户表
            scoreDetail.setCreateTime(System.currentTimeMillis());

            //设置明细表当前总积分
            int currentScore = userDetailDao.getUserScore(scoreDetail.getUserId());

            int scoreChange = scoreDetail.getIncome() - scoreDetail.getExpend();
            scoreDetail.setScore(currentScore + scoreChange);
            //添加
            scoreDetailDao.addScoreDetail(scoreDetail);
            //更新用户表积分值
            userDetailDao.updateUserScore(scoreDetail.getUserId(), scoreDetail.getScore());

            resultMap.put("result", "success");
        } catch (Exception e) {
            LOGGER.error("添加失败", e.getMessage());
            resultMap.put("result", "fail");
        }
        return resultMap;
    }

    @Override
    public PageBean getAllScoreDetail(int currentPage, int pageSize) {
        PageBean pageBean = new PageBean();
        int allRecord = scoreDetailDao.getScoreDetailCount();
        int offset = pageBean.countOffset(currentPage, pageSize);
        //调取dao获取对应条数记录
        List<ScoreDetail> scoreDetailList = scoreDetailDao.getAllScoreDetail(offset, pageSize);
        pageBean.setPageSize(pageSize);
        pageBean.setPageNo(currentPage);
        pageBean.setTotalRecords(allRecord);
        pageBean.setList(scoreDetailList);
        return pageBean;
    }

    @Override
    public Map<String, Object> getScoreDetailByUser(String userName) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        User user = userDao.getUser(userName);
        if (user != null) {
            List<ScoreDetail> scoreDetailList = scoreDetailDao.getScoreDetail(user.getId());
            if (scoreDetailList != null) {
                resultMap.put("result", scoreDetailList);
            } else {
                resultMap.put("result", "scoreDetailNoExist");
            }
        } else {
            resultMap.put("result", "userNoExist");
        }
        return resultMap;
    }

    @Override
    public int count() {
        return scoreDetailDao.getScoreDetailCount();
    }
}
