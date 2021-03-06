package com.lucky.dao.impl;

import com.lucky.dao.UserDao;
import com.lucky.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 用于操作用户基本信息表的dao层，包括添加、
 * 删除、更新和获取用户。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/1 15:48
 */
@Repository
public class UserDaoImpl implements UserDao {
    /**
     * Hibernate中的session工厂，用于获取会话创建查询。
     */
    @Resource
    private SessionFactory sessionFactory;

    /**
     * 封装的Hibernate增删改查模板方法对象
     */
    @Resource
    private HibernateTemplate hibernateTemplate;

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void addUser(User user) {
        hibernateTemplate.save(user);
    }

    @Override
    public User getUser(String nameOrEmail) {
        String hql = "from User where email=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, nameOrEmail);
        if(query.uniqueResult() == null){
            //若通过邮箱查询不到用户，则使用用户名再次查询
            LOGGER.info("无该邮箱");
            hql = "from User where userName=?";
            query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter(0, nameOrEmail);
        }
        return (User)query.uniqueResult();
    }

    @Override
    public User getUser(int id) {
        return hibernateTemplate.get(User.class,id);
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            hibernateTemplate.delete(hibernateTemplate.load(User.class, id));
            return true;
        } catch (Exception e) {
            LOGGER.error("删除用户失败",e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            User user1 = hibernateTemplate.get(User.class,user.getId());
            user1.setNickName(user.getNickName());
            user1.setUserName(user.getUserName());
            user1.setEmail(user.getEmail());
            hibernateTemplate.update(user1);
            return true;
        } catch (Exception e) {
            LOGGER.error("更新用户失败",e.getMessage());
            return false;
        }
    }

    @Override
    public List<User> getAllUser(int offset,int length) {
        List<User> userList = null;
        //Spring4于Hibernate5之间存在冲突，类型无法转换，这里用Hibernate4
        try {
            String hql = "from User";
            Query query =  sessionFactory.getCurrentSession().createQuery(hql);
            //设置页面起始记录和截止记录位置
            query.setFirstResult(offset);
            query.setMaxResults(length);
            userList = query.list();
        } catch (Exception e) {
            LOGGER.error("获取用户失败",e.getMessage());
        }
        return userList;
    }

    @Override
    public int getUserCount() {
        String hql = "select count(*) from User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        Object count = query.uniqueResult();
        return Integer.parseInt(count.toString());
    }
}
