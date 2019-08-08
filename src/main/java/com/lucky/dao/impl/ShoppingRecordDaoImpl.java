package com.lucky.dao.impl;

import com.lucky.dao.ShoppingRecordDao;
import com.lucky.entity.Product;
import com.lucky.entity.ShoppingRecord;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
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
    public ShoppingRecord getShoppingRecord(int userId, int productId, String time) {
        return null;
    }

    @Override
    public void addShoppingRecord(ShoppingRecord shoppingRecord) {
        hibernateTemplate.save(shoppingRecord);
    }

    @Override
    public boolean deleteShoppingRecord(int userId, int productId) {
        return false;
    }

    @Override
    public boolean updateShoppingRecord(ShoppingRecord shoppingRecord) {
        try {
            hibernateTemplate.update(shoppingRecord);
            return true;
        } catch (Exception e) {
            return false;
        }
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
        return null;
    }

    @Override
    public boolean getUserProductRecord(int userId, int productId) {
        return false;
    }

    @Override
    public boolean deleteShoppingRecordByUser(int userId) {
        return false;
    }

    @Override
    public boolean deleteShoppingRecordByProductId(int productId) {
        return false;
    }
}
