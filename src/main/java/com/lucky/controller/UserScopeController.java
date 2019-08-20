package com.lucky.controller;

import com.lucky.entity.ScoreDetail;
import com.lucky.entity.User;
import com.lucky.entity.UserDetail;
import com.lucky.service.ScoreDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping(params = "addScore")
    @ResponseBody
    public Map<String, Object> addScore(ScoreDetail scoreDetail){
        //参数处理判断
        if(scoreDetail!=null) {
            return scoreDetailService.addScoreDetail(scoreDetail);
        }else {
            return null;
        }
    }
}
