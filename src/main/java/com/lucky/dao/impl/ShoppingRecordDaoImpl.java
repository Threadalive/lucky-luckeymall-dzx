package com.lucky.dao.impl;

import com.lucky.dao.ShoppingRecordDao;
import com.lucky.entity.ShoppingRecord;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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

    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private HibernateTemplate hibernateTemplate;

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
//        String hql = "from ShoppingRecord where userId=? and productId=?";
//        Query query = sessionFactory.getCurrentSession().createQuery(hql);
//        query.setParameter(0,userId);
//        query.setParameter(1,productId);
//        try {
//            hibernateTemplate.deleteAll(query.list());
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
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
//1、
//        无法映射到具体表
//        String hql = "update ShoppingReocrd set orderStatus=? where userId=? and productId=? and createTime=?";
//        query.setParameter(0,shoppingRecord.getOrderStatus());
//        query.setParameter(1,shoppingRecord.getUserId());
//        query.setParameter(2,shoppingRecord.getProductId());
//        query.setParameter(3,shoppingRecord.getCreateTime());

//2、
//该方法出现A different object with the same identifier value was already associated with the session 异常
//session中已经存在一个与当前对象不同但是标识符相同的对象
//        try {
//            hibernateTemplate.update(shoppingRecord);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            return false;
        }
    }
}
