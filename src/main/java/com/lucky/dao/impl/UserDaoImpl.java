/**
 * @Description TODO
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/1 15:48
 */
package com.lucky.dao.impl;

import com.lucky.dao.UserDao;
import com.lucky.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUser(String nameOrEmail) {
        String hql = "from User where email=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, nameOrEmail);
        if(query.uniqueResult() == null){
            hql = "from User where userName=?";
            query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter(0, nameOrEmail);
        }
        return (User)query.uniqueResult();
    }

    @Override
    public User getUser(int id) {
        String hql = "from User where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,id);
        return (User) query.uniqueResult();
    }

    @Override
    public boolean deleteUser(int id) {
        String hql = "delete User where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateUser(User user) {
        String hql = "update User set userName = ?,email=?,nickName=? where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,user.getUserName());
        query.setParameter(1,user.getEmail());
        query.setParameter(2,user.getNickName());
        query.setParameter(3,user.getId());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<User> getAllUser() {
        String hql = "from User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
