package com.lucky.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.lucky.dao.ShoppingRecordDao;
import com.lucky.entity.ShoppingRecord;
import com.lucky.service.ShoppingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 订单记录模块管理的服务类
 *
 * @Author zhenxing.dong
 * @Date 2019/8/6 11:39
 */
@Service
public class ShoppingRecordServiceImpl implements ShoppingRecordService {

    @Autowired
    ShoppingRecordDao shoppingRecordDao;


    @Override
    public ShoppingRecord getShoppingRecord(int userId, int productId, String createTime) {
        return shoppingRecordDao.getShoppingRecord(userId,productId,createTime);
    }

    @Override
    public void addShoppingRecord(ShoppingRecord shoppingRecord) {
        shoppingRecordDao.addShoppingRecord(shoppingRecord);
    }

    @Override
    public boolean deleteShoppingRecord(int userId, int productId) {
        return shoppingRecordDao.deleteShoppingRecord(userId,productId);
    }

    @Override
    public Map<String,Object> updateShoppingRecord(ShoppingRecord shoppingRecord) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
            if(shoppingRecordDao.updateShoppingRecord(shoppingRecord)) {
                resultMap.put("result", "success");
            }else {
                resultMap.put("result", "fail");
            }
            return resultMap;
    }

    @Override
    public List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus) {
        return shoppingRecordDao.getShoppingRecordsByOrderStatus(orderStatus);
    }

    @Override
    public Map<String, Object> getShoppingRecords(int userId) {
        List<ShoppingRecord> shoppingRecordList = shoppingRecordDao.getShoppingRecords(userId);
        String shoppingRecords = JSONArray.toJSONString(shoppingRecordList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingRecords);
        return resultMap;
    }

    @Override
    public List<ShoppingRecord> getAllShoppingRecords() {
        return shoppingRecordDao.getAllShoppingRecords();
    }

    @Override
    public boolean getUserProductRecord(int userId, int productId) {
        return shoppingRecordDao.getUserProductRecord(userId,productId);
    }
}
