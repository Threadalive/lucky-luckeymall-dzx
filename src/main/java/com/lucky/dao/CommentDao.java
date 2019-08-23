package com.lucky.dao;

import com.lucky.entity.Comment;

import java.util.List;

/**
 * @Description 用户商品评价信息的操作层接口，定义评价信息的增删改查方法。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/2 22:16
 */
public interface CommentDao {
    /**
     * 通过用户id、商品id、创建时间获取指定评价对象
     * @param userId 用户id
     * @param productId 商品id
     * @param createTime 创建时间
     * @return 指定评价对象
     */
    Comment getComment(int userId,int productId,long createTime);

    /**
     * 添加评价项
     *
     * @param comment 要添加的评价对象
     */
    void addComment(Comment comment);

    /**
     * 通过用户id、商品id、创建时间删除指定评价对象
     *
     * @param userId 用户id
     * @param productId 商品id
     * @param createTime 创建时间
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteComment(int userId,int productId,long createTime);

    /**
     * 更新指定评价项
     *
     * @param comment 新的评价对象
     * @return boolean true:更新成功 false:更新失败
     */
    boolean updateComment(Comment comment);

    /**
     * 获取指定商品的所有评价
     *
     * @param productId 商品id
     * @return 评价列表
     */
    List<Comment> getCommentByProductId(int productId);

    /**
     * 删除指定用户的所有评价
     *
     * @param userId 用户id
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteCommentByUserId(int userId);

    /**
     * 删除指定商品的所有评价
     *
     * @param productId 商品id
     * @return boolean true:删除成功 false:删除失败
     */
    boolean deleteCommentByProductId(int productId);
}
