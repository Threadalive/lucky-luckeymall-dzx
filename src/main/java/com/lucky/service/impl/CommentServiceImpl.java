package com.lucky.service.impl;

import com.lucky.dao.CommentDao;
import com.lucky.entity.Comment;
import com.lucky.service.CommentService;
import com.lucky.service.ShoppingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 评论类的服务类
 *
 * @Author zhenxing.dong
 * @Date 2019/8/13 01:12
 */
@Service
public class CommentServiceImpl implements CommentService {
    /**
     * 评论类的具体dao层对象
     */
    @Autowired
    CommentDao commentDao;
    /**
     * 订单管理服务类
     */
    @Autowired
    ShoppingRecordService shoppingRecordService;

    @Override
    public Map<String, Object> getComment(int userId, int productId, long createTime) {
        Comment comment =commentDao.getComment(userId,productId,createTime);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",comment);
        return resultMap;
    }

    @Override
    public Map<String, Object> addComment(Comment comment) {
        Map<String,Object> resultMap = new HashMap<>();
        //检查订单是否存在，存在则添加评论，不存在则返回提示
        if(shoppingRecordService.getUserProductRecord(comment.getUserId(),comment.getProductId()))
        {
            comment.setCreateTime(System.currentTimeMillis());
            commentDao.addComment(comment);
            resultMap.put("result","success");
        }else {
            resultMap.put("result","orderNoExist");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> deleteComment(int userId, int productId, long createTime) {
        Map<String,Object> resultMap = new HashMap<>();
        if(commentDao.deleteComment(userId,productId,createTime)){
            resultMap.put("result","success");
        }else {
            resultMap.put("result","fail");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> updateComment(Comment comment) {
        Map<String,Object> resultMap = new HashMap<>();
        //判断评论是否存在
        if(commentDao.getComment(comment.getUserId(),comment.getProductId(),comment.getCreateTime())!=null){
            if(commentDao.updateComment(comment)){
                resultMap.put("result","success");
            }else {
                resultMap.put("result","fail");
            }
        }else {
            resultMap.put("result","commentNoExist");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getCommentByProductId(int productId) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            List<Comment> commentList = commentDao.getCommentByProductId(productId);
            resultMap.put("result",commentList);
        } catch (Exception e) {
            resultMap.put("result","fail");
        }
        return resultMap;
    }
}
