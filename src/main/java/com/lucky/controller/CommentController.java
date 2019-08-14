package com.lucky.controller;

import com.lucky.entity.Comment;
import com.lucky.service.CommentService;
import org.apache.log4j.Logger;
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
    private static final Logger logger = Logger.getLogger(ShoppingCarController.class);


    @PostMapping(params = "addComment")
    @ResponseBody
    public Map<String,Object> addComment(Comment comment){
        if(comment!=null) {
            logger.info("传入评论为:"+comment.getContent());
            return commentService.addComment(comment);
        }else {
            logger.error("传入评论为空");
            return null;
        }
    }

    @PostMapping(params = "getCommentByProductId")
    @ResponseBody
    public Map<String,Object> getCommentByProductId(int productId){
        if(productId!=0) {
            logger.info("查询商品id为:"+productId);
            return commentService.getCommentByProductId(productId);
        }else {
            logger.error("参数不存在");
            return null;
        }
    }
}
