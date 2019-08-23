package com.lucky.dao.impl;

import com.lucky.dao.CommentDao;
import com.lucky.entity.Comment;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 评论类的dao层实现
 *
 * @Author zhenxing.dong
 * @Date 2019/8/13 01:05
 */
@Repository
public class CommentDaoImpl implements CommentDao {
    /**
     * session工厂
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Hibernate的增删改查模板方法类
     */
    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentDaoImpl.class);

    @Override
    public Comment getComment(int userId, int productId, long createTime) {
        String hql = "from Comment where userId=? and productId=? and createTime=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(2, createTime);
        LOGGER.info("成功设置参数");
        return (Comment) query.uniqueResult();
    }

    @Override
    public void addComment(Comment comment) {
        hibernateTemplate.save(comment);
        LOGGER.info("评论添加成功");
    }

    @Override
    public boolean deleteComment(int userId, int productId, long createTime) {
        String hql = "delete Comment where userId=? and productId=? and createTime=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(2, createTime);
        LOGGER.info("成功设置删除参数");
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateComment(Comment comment) {
        try {
            //清一下session中存在的对象防止重复主键异常
            sessionFactory.getCurrentSession().clear();

            hibernateTemplate.update(comment);
            return true;
        } catch (Exception e) {
            LOGGER.error("更新失败",e.getMessage());
            return false;
        }
    }

    @Override
    public List<Comment> getCommentByProductId(int productId) {
        String hql = "from Comment where productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, productId);
        return  query.list();
    }

    @Override
    public boolean deleteCommentByUserId(int userId) {
        String hql = "from Comment where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        try {
            hibernateTemplate.deleteAll(query.list());
            return true;
        } catch (Exception e) {
            LOGGER.error("用户评论删除失败",e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCommentByProductId(int productId) {
        String hql = "from Comment where productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,productId);
        try {
            hibernateTemplate.deleteAll(query.list());
            return true;
        } catch (Exception e) {
            LOGGER.error("商品评论删除失败",e.getMessage());
            return false;
        }
    }
}
