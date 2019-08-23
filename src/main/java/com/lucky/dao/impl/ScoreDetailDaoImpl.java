package com.lucky.dao.impl;

import com.lucky.dao.ScoreDetailDao;
import com.lucky.entity.ScoreDetail;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 积分明细表对数据库的具体操作dao类
 *
 * @Author zhenxing.dong
 * @Date 2019/8/19 16:34
 */
@Repository
public class ScoreDetailDaoImpl implements ScoreDetailDao {
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
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);

    @Override
    public List<ScoreDetail> getScoreDetail(int userId) {
        String hql = "from ScoreDetail where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        return  query.list();
    }

    @Override
    public void addScoreDetail(ScoreDetail scoreDetail) {
        hibernateTemplate.save(scoreDetail);
    }

    @Override
    public boolean deleteScoreDetail(int userId, long createTime) {
        String hql = "from ScoreDetail where userId=? and createTime=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        query.setParameter(1,createTime);
        try {
            hibernateTemplate.delete(query.uniqueResult());
            return true;
        } catch (Exception e) {
            LOGGER.error("删除积分明细失败",e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateScoreDetail(ScoreDetail scoreDetail) {
        try {
            //清session中存在的对象防止重复主键异常
            sessionFactory.getCurrentSession().clear();
            hibernateTemplate.update(scoreDetail);
            return true;
        } catch (Exception e) {
            LOGGER.error("更新积分明细失败",e.getMessage());
            return false;
        }
    }

    @Override
    public List<ScoreDetail> getAllScoreDetail(int offset,int length) {
        List<ScoreDetail> scoreDetailList = null;
        //Spring4于Hibernate5之间存在冲突，类型无法转换，这里用Hibernate4
        try {
            String hql = "from ScoreDetail";
            Query query =  sessionFactory.getCurrentSession().createQuery(hql);
            query.setFirstResult(offset);
            query.setMaxResults(length);
            scoreDetailList = query.list();
        } catch (Exception e) {
            LOGGER.error("获取积分明细失败",e.getMessage());
        }
        return scoreDetailList;
    }

    @Override
    public int getScoreDetailCount() {
        String hql = "select count(*) from ScoreDetail";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        Object count = query.uniqueResult();
        return Integer.parseInt(count.toString());
    }

    @Override
    public List<ScoreDetail> getScoreDetailByUserIdAndItemName(int userId, String itemName) {
        String hql = "from ScoreDetail where userId=? and itemName=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, itemName);
        return query.list();
    }
}
