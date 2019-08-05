package com.lucky.dao.impl;

import com.lucky.dao.ShoppingRecordDao;
import com.lucky.entity.ShoppingRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 操作用户购物记录数据库表的dao层实现类
 *
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/5 17:58
 */
@Repository
public class ShoppingRecordDaoImpl implements ShoppingRecordDao {
    @Override
    public ShoppingRecord getShoppingRecord(int userId, int productId, String time) {
        return null;
    }

    @Override
    public void addShoppingRecord(ShoppingRecord shoppingRecord) {

    }

    @Override
    public boolean deleteShoppingRecord(int userId, int productId) {
        return false;
    }

    @Override
    public boolean updateShoppingRecord(ShoppingRecord shoppingRecord) {
        return false;
    }

    @Override
    public List<ShoppingRecord> getShoppingRecords(int userId) {
        return null;
    }

    @Override
    public List<ShoppingRecord> getAllShoppingRecords() {
        return null;
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
