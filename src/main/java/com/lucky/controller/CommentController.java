package com.lucky.controller;

import com.lucky.entity.Comment;
import com.lucky.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Description 评价的控制器
 *
 * @Author zhenxing.dong
 * @Date 2019/8/13 01:13
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    /**
     * 评论类的服务类
     */
    @Autowired
    CommentService commentService;

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);


    /**
     * 添加评论记录
     *
     * @param comment 评论对象
     * @return 添加结果
     */
    @PostMapping(params = "addComment")
    @ResponseBody
    public Map<String,Object> addComment(Comment comment){
        if(comment!=null) {
            LOGGER.info("传入评论为:"+comment.getContent());
            return commentService.addComment(comment);
        }else {
            LOGGER.error("传入评论为空");
            return null;
        }
    }

    /**
     * 根据商品Id获取评论信息
     *
     * @param productId 商品id
     * @return 获取的指定评论集
     */
    @PostMapping(params = "getCommentByProductId")
    @ResponseBody
    public Map<String,Object> getCommentByProductId(int productId){
        if(productId!=0) {
            LOGGER.info("查询商品id为:"+productId);
            return commentService.getCommentByProductId(productId);
        }else {
            LOGGER.error("参数不存在");
            return null;
        }
    }
}
