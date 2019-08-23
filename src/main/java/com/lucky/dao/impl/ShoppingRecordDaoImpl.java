package com.lucky.dao.impl;

import com.lucky.dao.ShoppingRecordDao;
import com.lucky.entity.ShoppingRecord;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 操作用户购物记录数据库表的dao层实现类
 *
 * @Author zhenxing.dong
 * @Date 2019/8/5 17:58
 */
@Repository
public class ShoppingRecordDaoImpl implements ShoppingRecordDao {

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
    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingRecordDaoImpl.class);

    @Override
    public ShoppingRecord getShoppingRecord(int userId, int productId, long createTime) {
        String hql= "from ShoppingRecord where userId=? and productId=? and createTime=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        query.setParameter(1,productId);
        query.setParameter(2,createTime);
        return (ShoppingRecord) query.uniqueResult();
    }

    @Override
    public void addShoppingRecord(ShoppingRecord shoppingRecord) {
        hibernateTemplate.save(shoppingRecord);
    }

    @Override
    public boolean deleteShoppingRecord(int userId, int productId) {
        String hql = "delete ShoppingRecord where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateShoppingRecord(ShoppingRecord shoppingRecord) {
        //使用由于另两种方法有bug，这里使用sql直接查询
        String sql = "update t_shopping_record set order_status="+shoppingRecord.getOrderStatus()+
                " where user_id="+shoppingRecord.getUserId()+
                " and product_id="+shoppingRecord.getProductId()+" and create_time='"+shoppingRecord.getCreateTime()+"'";

        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        return query.executeUpdate()>0;
    }

    @Override
    public List<ShoppingRecord> getShoppingRecords(int userId) {
        String hql = "from ShoppingRecord where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        return query.list();
    }

    @Override
    public List<ShoppingRecord> getAllShoppingRecords() {
        List<ShoppingRecord> shoppingRecordList = null;
        try {
            shoppingRecordList = (List<ShoppingRecord>) hibernateTemplate.find("from com.lucky.entity.ShoppingRecord");
        } catch (Exception e) {
            LOGGER.error("获取订单记录失败",e.getMessage());
        }
        return shoppingRecordList;
    }

    @Override
    public List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus) {
        String hql = "from ShoppingRecord where orderStatus=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, orderStatus);
        return query.list();
    }

    @Override
    public boolean getUserProductRecord(int userId, int productId) {
        String hql = "from ShoppingRecord where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        query.setParameter(1,productId);
        return query.list().size()>0;
    }

    @Override
    public boolean deleteShoppingRecordByUser(int userId) {
        String hql = "from com.lucky.entity.ShoppingRecord where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        try {
            hibernateTemplate.deleteAll(query.list());
            return true;
        } catch (Exception e) {
            LOGGER.error("删除订单记录失败",e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteShoppingRecordByProductId(int productId) {
        String hql = "from com.lucky.entity.ShoppingRecord where productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,productId);
        try {
            hibernateTemplate.deleteAll(query.list());
            return true;
        } catch (Exception e) {
            LOGGER.error("根据商品删除订单记录失败",e.getMessage());
            return false;
        }
    }

    @Override
    public int getOrderCount() {
        String hql = "select count(*) from ShoppingRecord";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        Object count = query.uniqueResult();
        return Integer.parseInt(count.toString());
    }
}
