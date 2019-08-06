package com.lucky.dao.impl;

import com.lucky.dao.UserDao;
import com.lucky.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 用于操作用户基本信息表的dao层，包括添加、
 * 删除、更新和获取用户。
 *
 * @Author zhenxing.dong@luckincoffee.com
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
            hql = "from User where userName=?";
            query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter(0, nameOrEmail);
        }
        return (User)query.uniqueResult();

//        User user = (User) hibernateTemplate.find(hql,new String[]{nameOrEmail, nameOrEmail});
//        if(user == null){
//            return null;
//        }else {
//            return user;
//        }
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
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            hibernateTemplate.update(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = null;
        //Spring4于Hibernate5之间存在冲突，类型无法转换，这里用Hibernate4
        try {
            userList = (List<User>) hibernateTemplate.find("from com.lucky.entity.User");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}
