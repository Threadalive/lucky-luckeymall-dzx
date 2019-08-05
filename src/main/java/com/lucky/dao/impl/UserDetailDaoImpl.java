package com.lucky.dao.impl;

import com.lucky.dao.UserDetailDao;
import com.lucky.entity.UserDetail;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 用于操作用户细节信息表的dao层，包括添加、
 * 删除、更新和获取用户。
 *
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/2 22:16
 */
@Repository
public class UserDetailDaoImpl implements UserDetailDao {
    /**
     * Hibernate中的session工厂，用于获取会话创建查询。
     */
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public UserDetail getUserDetail(int id) {
        String hql = "from UserDetail where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return (UserDetail)query.uniqueResult();
    }

    @Override
    public void addUserDetail(UserDetail userDetail) {
        sessionFactory.getCurrentSession().save(userDetail);
    }

    @Override
    public boolean deleteUserDetail(int id) {
        String hql = "delete UserDetail where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateUserDetail(UserDetail userDetail) {
        String hql = "update UserDetail set password=?,phoneNumber=?,sex=?,birthday=?,postNumber=?,address=? where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userDetail.getPassword());
        query.setParameter(1,userDetail.getPhoneNumber());
        query.setParameter(2,userDetail.getSex());
        query.setParameter(3,userDetail.getBirthday());
        query.setParameter(4,userDetail.getPostNumber());
        query.setParameter(5,userDetail.getAddress());
        query.setParameter(6,userDetail.getId());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<UserDetail> getAllUserDetail() {
        String hql = "from UserDetail";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
