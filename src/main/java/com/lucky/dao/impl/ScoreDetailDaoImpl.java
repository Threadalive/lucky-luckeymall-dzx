package com.lucky.dao.impl;

import com.lucky.dao.ScoreDetailDao;
import com.lucky.entity.ScoreDetail;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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
            e.printStackTrace();
            return false;
        }
    }
}
