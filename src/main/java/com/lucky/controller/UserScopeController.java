package com.lucky.controller;

import com.lucky.entity.PageBean;
import com.lucky.entity.ScoreDetail;
import com.lucky.service.ScoreDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 用户积分控制
 *
 * @Author zhenxing.dong
 * @Date 2019/8/19 15:54
 */
@Controller
@RequestMapping("/score")
public class UserScopeController {

    /**
     * 积分服务类
     */
    @Autowired
    ScoreDetailService scoreDetailService;

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserScopeController.class);

    /**
     * 添加积分记录项
     *
     * @param scoreDetail 积分记录项
     * @return 添加结果
     */
    @PostMapping(params = "addScore")
    @ResponseBody
    public Map<String, Object> addScore(ScoreDetail scoreDetail){
        //参数处理判断
        if(scoreDetail!=null) {
            return scoreDetailService.addScoreDetail(scoreDetail);
        }else {
            LOGGER.error("传入对象为空！！！！");
            return null;
        }
    }
    /**
     * 转发获取所有积分记录
     *
     * @param request 请求
     * @return 所有积分记录的信息页面
     */
    @PostMapping(params = "getAllScoreDetail")
    @ResponseBody
    public PageBean getAllScoreDetail(HttpServletRequest request){
        String pageNo = request.getParameter("pageNo");
        if(pageNo == null){
            pageNo = "1";
        }
        PageBean pageBean = scoreDetailService.getAllScoreDetail(Integer.valueOf(pageNo), 6);
        return pageBean;
    }

    /**
     * 根据用户名或邮箱取积分明细记录
     *
     * @param userName 用户名
     * @return 积分明细
     */
    @PostMapping(params = "getScoreDetailByUser")
    @ResponseBody
    public Map<String, Object> getScoreDetailByUser(String userName){
        Map<String, Object> resultMap = new HashMap<>(1);
        if(userName==null||"".equals(userName)){
            resultMap.put("result","listAll");
            LOGGER.warn("传入空用户名");
            return resultMap;
        }else {
            return scoreDetailService.getScoreDetailByUser(userName);
        }
    }
}
