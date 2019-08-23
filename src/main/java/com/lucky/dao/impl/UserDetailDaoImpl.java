package com.lucky.dao.impl;

import com.lucky.dao.UserDetailDao;
import com.lucky.entity.UserDetail;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 用于操作用户细节信息表的dao层，包括添加、
 * 删除、更新和获取用户。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/2 22:16
 */
@Repository
public class UserDetailDaoImpl implements UserDetailDao {
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
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailDaoImpl.class);

    @Override
    public UserDetail getUserDetail(int id) {
        return hibernateTemplate.get(UserDetail.class,id);
    }

    @Override
    public void addUserDetail(UserDetail userDetail) {
        hibernateTemplate.save(userDetail);
    }

    @Override
    public boolean deleteUserDetail(int id) {
        try {
            hibernateTemplate.delete(hibernateTemplate.load(UserDetail.class, id));
            return true;
        } catch (Exception e) {
            LOGGER.error("删除用户失败",e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateUserDetail(UserDetail userDetail) {
        try {
            //更新用户详细信息
            UserDetail userDetail1 = hibernateTemplate.get(UserDetail.class,userDetail.getId());
            userDetail1.setSex(userDetail.getSex());
            userDetail1.setAddress(userDetail.getAddress());
            userDetail1.setPostNumber(userDetail.getPostNumber());
            userDetail1.setBirthday(userDetail.getBirthday());
            userDetail1.setPhoneNumber(userDetail.getPhoneNumber());
            userDetail1.setPassword(userDetail.getPassword());
            hibernateTemplate.update(userDetail1);
            return true;
        } catch (Exception e) {
            LOGGER.error("更新用户失败",e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateUserScore(int userId,int score) {
        try {
            UserDetail user = hibernateTemplate.get(UserDetail.class,userId);
            user.setScore(score);
            hibernateTemplate.update(user);
            return true;
        } catch (Exception e) {
            LOGGER.error("更新用户积分失败",e.getMessage());
            return false;
        }
    }


    @Override
    public List<UserDetail> getAllUserDetail() {
        List<UserDetail> userDetailsList = null;
        //Spring4于Hibernate5之间存在冲突，类型无法转换，这里用Hibernate4
        try {
            userDetailsList = (List<UserDetail>) hibernateTemplate.find("from com.lucky.entity.UserDetail");
        } catch (Exception e) {
            LOGGER.error("获取用户失败",e.getMessage());
        }
        return userDetailsList;
    }

    @Override
    public int getUserScore(int userId) {
        String hql = "from UserDetail where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        UserDetail userDetail = (UserDetail) query.uniqueResult();
        return userDetail.getScore();
    }
}
