package com.lucky.dao.impl;

import com.lucky.dao.ShoppingCarDao;
import com.lucky.entity.ShoppingCar;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 购物车操作dao层的具体实现类
 *
 * @Author zhenxing.dong
 * @Date 2019/8/12 00:16
 */
@Repository
public class ShoppingCarDaoImpl implements ShoppingCarDao {

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
    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCarDaoImpl.class);

    @Override
    public ShoppingCar getShoppingCar(int userId, int productId) {
        String hql= "from ShoppingCar where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        query.setParameter(1,productId);
        LOGGER.info("参数设置成功"+userId+'\n'+productId);
        return (ShoppingCar) query.uniqueResult();
    }

    @Override
    public void addShoppingCar(ShoppingCar shoppingCar) {
        hibernateTemplate.save(shoppingCar);
    }

    @Override
    public boolean deleteShoppingCar(int userId, int productId) {
        String hql = "delete ShoppingCar where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        LOGGER.info("删除参数设置成功"+userId+'\n'+productId);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateShoppingCar(ShoppingCar shoppingCar) {
        try {
            //清一下session中存在的对象防止重复主键异常
            sessionFactory.getCurrentSession().clear();
            hibernateTemplate.update(shoppingCar);
            return true;
        } catch (Exception e) {
            LOGGER.error("购物车更新成功",e.getMessage());
            return false;
        }
    }

    @Override
    public List<ShoppingCar> getShoppingCars(int userId) {
        String hql = "from ShoppingCar where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        return query.list();
    }

    @Override
    public boolean deleteShoppingCarByUserId(int userId) {
        try {
            hibernateTemplate.delete(hibernateTemplate.load(ShoppingCar.class, userId));
            return true;
        } catch (Exception e) {
            LOGGER.error("购物车删除成功",e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteShoppingCarByProduct(int productId) {
        try {
            hibernateTemplate.delete(hibernateTemplate.load(ShoppingCar.class, productId));
            return true;
        } catch (Exception e) {
            LOGGER.error("商品购物车删除成功",e.getMessage());
            return false;
        }
    }
}
