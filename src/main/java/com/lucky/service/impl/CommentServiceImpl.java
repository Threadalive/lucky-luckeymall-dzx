package com.lucky.service.impl;

import com.lucky.entity.Comment;
import com.lucky.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description TODO
 * @Author zhenxing.dong
 * @Date 2019/8/13 01:12
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public Map<String, Object> getComment(int userId, int productId, String createTime) {
        return null;
    }

    @Override
    public Map<String, Object> addComment(Comment comment) {
        return null;
    }

    @Override
    public Map<String, Object> deleteComment(int userId, int productId, String createTime) {
        return null;
    }

    @Override
    public Map<String, Object> updateComment(Comment comment) {
        return null;
    }

    @Override
    public Map<String, Object> getCommentByProductId(int productId) {
        return null;
    }
}
