package com.lucky.dao.impl;

import com.lucky.dao.UserDetailDao;
import com.lucky.entity.UserDetail;
import org.hibernate.SessionFactory;
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
            return false;
        }
    }

    @Override
    public boolean updateUserDetail(UserDetail userDetail) {
        try {
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
            e.printStackTrace();
        }
        return userDetailsList;
    }
}
