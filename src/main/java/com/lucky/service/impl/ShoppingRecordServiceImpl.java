package com.lucky.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.lucky.dao.ProductDao;
import com.lucky.dao.ShoppingRecordDao;
import com.lucky.entity.Product;
import com.lucky.entity.ShoppingRecord;
import com.lucky.service.ShoppingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Autowired
    ProductDao productDao;


    @Override
    public ShoppingRecord getShoppingRecord(int userId, int productId, long createTime) {
        return shoppingRecordDao.getShoppingRecord(userId,productId,createTime);
    }

    @Override
    public Map<String,Object> addShoppingRecord(ShoppingRecord shoppingRecord) {
        Map<String,Object> resultMap = new HashMap<>();
        Product product = productDao.getProduct(shoppingRecord.getProductId());

        //判断商品库存是否足够
        if(shoppingRecord.getCounts()<=product.getCounts()){
            try {
            shoppingRecord.setCreateTime(System.currentTimeMillis());
            shoppingRecord.setOrderStatus(0);
            //商品总价
            shoppingRecord.setProductPrice(product.getPrice().multiply(new BigDecimal(shoppingRecord.getCounts())));

            //库存减去购买的数量
            product.setCounts(product.getCounts()-shoppingRecord.getCounts());

            //更新商品信息
            productDao.updateProduct(product);

            shoppingRecordDao.addShoppingRecord(shoppingRecord);
            resultMap.put("result","success");
        } catch (Exception e) {
            resultMap.put("result","fail");
        }
            return resultMap;
        }else {
            resultMap.put("result","unEnough");
        }
        return resultMap;
    }

    @Override
    public boolean deleteShoppingRecord(int userId, int productId) {
        return shoppingRecordDao.deleteShoppingRecord(userId,productId);
    }

    @Override
    public Map<String,Object> updateShoppingRecord(ShoppingRecord shoppingRecord) {
        Map<String,Object> resultMap = new HashMap<String,Object>();

        ShoppingRecord oldShoppingRecord = shoppingRecordDao.getShoppingRecord(shoppingRecord.getUserId(),shoppingRecord.getProductId(),shoppingRecord.getCreateTime());
        if(oldShoppingRecord == null){
            resultMap.put("result","noExist");
        }else {
            //设置价格不变
            shoppingRecord.setProductPrice(oldShoppingRecord.getProductPrice());
            //设置购买数量不变
            shoppingRecord.setCounts(oldShoppingRecord.getCounts());

            if (shoppingRecordDao.updateShoppingRecord(shoppingRecord)) {
                resultMap.put("result", "success");
            } else {
                resultMap.put("result", "fail");
            }
        }
            return resultMap;
    }

    @Override
    public Map<String, Object> getShoppingRecordByOrderStatus(int orderStatus) {
        List<ShoppingRecord> shoppingRecordList = shoppingRecordDao.getShoppingRecordsByOrderStatus(orderStatus);
        String shoppingRecords = JSONArray.toJSONString(shoppingRecordList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingRecords);
        return resultMap;
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
    public Map<String,Object> getAllShoppingRecords() {
        List<ShoppingRecord> shoppingRecordList = shoppingRecordDao.getAllShoppingRecords();
        String shoppingRecords = JSONArray.toJSONString(shoppingRecordList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingRecords);
        return resultMap;
    }

    @Override
    public boolean getUserProductRecord(int userId, int productId) {
        return shoppingRecordDao.getUserProductRecord(userId,productId);
    }
}
