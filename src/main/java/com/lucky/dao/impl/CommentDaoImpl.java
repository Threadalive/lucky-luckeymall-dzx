package com.lucky.dao.impl;

import com.lucky.dao.CommentDao;
import com.lucky.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author zhenxing.dong
 * @Date 2019/8/13 01:05
 */
@Repository
public class CommentDaoImpl implements CommentDao {
    @Override
    public Comment getComment(int userId, int productId, String createTime) {
        return null;
    }

    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public boolean deleteComment(int userId, int productId, String createTime) {
        return false;
    }

    @Override
    public boolean updateComment(Comment comment) {
        return false;
    }

    @Override
    public List<Comment> getCommentByProductId(int productId) {
        return null;
    }

    @Override
    public boolean deleteCommentByUserId(int userId) {
        return false;
    }

    @Override
    public boolean deleteCommentByProductId(int productId) {
        return false;
    }
}
