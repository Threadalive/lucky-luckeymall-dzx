package com.lucky.service;

import com.lucky.entity.Comment;
import java.util.Map;

/**
 * @Description 评论类的服务类
 *
 * @Author zhenxing.dong
 * @Date 2019/8/13 01:12
 */
public interface CommentService {
    /**
     * 通过用户id、商品id、创建时间获取指定评价对象
     * @param userId 用户id
     * @param productId 商品id
     * @param createTime 创建时间
     * @return 指定评价对象
     */
    Map<String,Object> getComment(int userId, int productId, long createTime);

    /**
     * 添加评价
     *
     * @param comment 要添加的评价对象
     * @return 添加结果
     */
    Map<String,Object> addComment(Comment comment);

    /**
     * 通过用户id、商品id、创建时间删除指定评价对象
     *
     * @param userId 用户id
     * @param productId 商品id
     * @param createTime 创建时间
     * @return 删除结果
     */
    Map<String,Object> deleteComment(int userId,int productId,long createTime);

    /**
     * 更新指定评价项
     *
     * @param comment 新的评价对象
     * @return 更新结果
     */
    Map<String,Object> updateComment(Comment comment);

    /**
     * 获取指定商品的所有评价
     *
     * @param productId 商品id
     * @return 评价结果集
     */
    Map<String,Object> getCommentByProductId(int productId);

}
