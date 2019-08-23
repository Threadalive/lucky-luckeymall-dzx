package com.lucky.service.impl;

import com.lucky.dao.ProductDao;
import com.lucky.dao.ShoppingRecordDao;
import com.lucky.dao.UserDetailDao;
import com.lucky.entity.Product;
import com.lucky.entity.ScoreDetail;
import com.lucky.entity.ShoppingRecord;
import com.lucky.service.ScoreDetailService;
import com.lucky.service.ShoppingRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 订单记录模块管理的服务类
 * @Author zhenxing.dong
 * @Date 2019/8/6 11:39
 */
@Service
public class ShoppingRecordServiceImpl implements ShoppingRecordService {

    /**
     * 积分规则
     */
    private static final int RATE = 10;

    /**
     * 订单记录的操作层
     */
    @Autowired
    ShoppingRecordDao shoppingRecordDao;

    /**
     * 商品类操作层
     */
    @Autowired
    ProductDao productDao;

    /**
     * 用户详细信息操作层
     */
    @Autowired
    UserDetailDao userDetailDao;

    /**
     * 积分明细表的服务层
     */
    @Autowired
    ScoreDetailService scoreDetailService;

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingRecordServiceImpl.class);

    @Override
    public ShoppingRecord getShoppingRecord(int userId, int productId, long createTime) {
        return shoppingRecordDao.getShoppingRecord(userId, productId, createTime);
    }

    /**
     * 下单流程：
     * 1、检查库存
     * 2、判断是否积分支付
     * 3、更新库存
     * 4、下订单
     * 5、通知
     *
     * @param shoppingRecord 订单记录对象
     * @return 订单添加结果
     */
    @Override
    @Transactional
    public Map<String, Object> addShoppingRecord(ShoppingRecord shoppingRecord, int type) {
        Map<String, Object> resultMap = new HashMap<>(2);
        Product product = productDao.getProduct(shoppingRecord.getProductId());

        //判断商品库存是否足够
        if (shoppingRecord.getCounts() <= product.getCounts()) {
            //实际支付
            if (type == 0) {
                LOGGER.info("直接支付");
                try {
                    shoppingRecord.setCreateTime(System.currentTimeMillis());
                    shoppingRecord.setOrderStatus(0);
                    //商品总价
                    shoppingRecord.setProductPrice(product.getPrice().multiply(new BigDecimal(shoppingRecord.getCounts())));

                    //库存减去购买的数量
                    product.setCounts(product.getCounts() - shoppingRecord.getCounts());

                    //更新商品信息
                    productDao.updateProduct(product);

                    shoppingRecordDao.addShoppingRecord(shoppingRecord);
                    resultMap.put("result", "success");
                } catch (Exception e) {
                    LOGGER.error("添加订单失败",e.getMessage());
                    resultMap.put("result", "fail");
                }
            } else if (type == 1) {
                LOGGER.info("积分支付");
                int userId = shoppingRecord.getUserId();
                //用户当前积分
                int userScore = userDetailDao.getUserDetail(userId).getScore();
                //商品耗费积分
                int scoreCost = (product.getPrice().multiply(new
                        BigDecimal(shoppingRecord.getCounts())).multiply(new BigDecimal(RATE))).intValue();
                if (userScore < scoreCost) {
                    resultMap.put("result", "scoreUnEnough");
                } else {
                    shoppingRecord.setCreateTime(System.currentTimeMillis());
                    shoppingRecord.setOrderStatus(0);
                    //商品总价
                    shoppingRecord.setProductPrice(product.getPrice().multiply(new BigDecimal(shoppingRecord.getCounts())));

                    //库存减去购买的数量
                    product.setCounts(product.getCounts() - shoppingRecord.getCounts());

                    //更新商品库存
                    productDao.updateProduct(product);

                    LOGGER.info(shoppingRecord.toString());
                    //添加订单
                    shoppingRecordDao.addShoppingRecord(shoppingRecord);

                    userScore = userScore - scoreCost;
                    //设置用户当前积分余额
                    userDetailDao.updateUserScore(userId, userScore);

                    ScoreDetail scoreDetail = new ScoreDetail();
                    //设置明细表
                    scoreDetail.setScore(userScore);
                    scoreDetail.setExpend(scoreCost);
                    scoreDetail.setIncome(0);
                    scoreDetail.setCreateTime(System.currentTimeMillis());
                    scoreDetail.setUserId(userId);
                    scoreDetail.setItemName(product.getProductName());
                    scoreDetailService.addScoreDetail(scoreDetail);
                    resultMap.put("result", "scorePaySuccess");
                }
            }
        } else {
            resultMap.put("result", "unEnough");
        }
        return resultMap;
    }

    @Override
    public boolean deleteShoppingRecord(int userId, int productId) {
        return shoppingRecordDao.deleteShoppingRecord(userId, productId);
    }

    @Override
    public Map<String, Object> updateShoppingRecord(ShoppingRecord shoppingRecord) {
        Map<String, Object> resultMap = new HashMap<String, Object>(2);

        ShoppingRecord oldShoppingRecord = shoppingRecordDao.getShoppingRecord(
                shoppingRecord.getUserId(), shoppingRecord.getProductId(), shoppingRecord.getCreateTime());
        if (oldShoppingRecord == null) {
            LOGGER.warn("订单不存在");
            resultMap.put("result", "noExist");
        } else {
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
        Map<String, Object> resultMap = new HashMap<String, Object>(6);
        resultMap.put("result", shoppingRecordList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getShoppingRecords(int userId) {
        List<ShoppingRecord> shoppingRecordList = shoppingRecordDao.getShoppingRecords(userId);
        Map<String, Object> resultMap = new HashMap<String, Object>(4);
        resultMap.put("result", shoppingRecordList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getAllShoppingRecords() {
        List<ShoppingRecord> shoppingRecordList = shoppingRecordDao.getAllShoppingRecords();
        Map<String, Object> resultMap = new HashMap<String, Object>(6);
        resultMap.put("result", shoppingRecordList);
        return resultMap;
    }

    @Override
    public boolean getUserProductRecord(int userId, int productId) {
        return shoppingRecordDao.getUserProductRecord(userId, productId);
    }

    @Override
    public Map<String, Object> getOrderCount() {
        Map<String, Object> resultMap = new HashMap<>(1);
        int orderCount = shoppingRecordDao.getOrderCount();
        resultMap.put("result", orderCount);
        return resultMap;
    }
}
