package com.lucky.service.impl;

import com.lucky.dao.ProductDao;
import com.lucky.dao.ShoppingCarDao;
import com.lucky.entity.ShoppingCar;
import com.lucky.service.ShoppingCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 购物车操作的服务层类
 *
 * @Author zhenxing.dong
 * @Date 2019/8/12 00:29
 */
@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {

    /**
     * 商品操作的dao层类
     */
    @Autowired
    ProductDao productDao;

    /**
     * 购物车dao层类
     */
    @Autowired
    ShoppingCarDao shoppingCarDao;

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCarServiceImpl.class);

    @Override
    public Map<String, Object> getShoppingCar(int userId, int productId) {
        shoppingCarDao.getShoppingCar(userId,productId);
        Map<String, Object> resultMap = new HashMap<String,Object>(6);
        resultMap.put("result","success");
        return resultMap;
    }

    @Override
    public Map<String, Object> addShoppingCar(ShoppingCar shoppingCar) {
        LOGGER.info("商品数量为:"+shoppingCar.getCounts());
        //根据用户id和商品id查询购物车订单是否存在
        ShoppingCar shoppingCar1 = shoppingCarDao.getShoppingCar(shoppingCar.getUserId(),
                shoppingCar.getProductId());
        if(shoppingCar1==null){
            //设置价格
            shoppingCar.setProductPrice((productDao.getProduct(shoppingCar.getProductId()).
                    getPrice()).multiply(new BigDecimal(shoppingCar.getCounts())));
            shoppingCarDao.addShoppingCar(shoppingCar);
        }else {
            //设置数量
            shoppingCar.setCounts(shoppingCar1.getCounts()+shoppingCar.getCounts());
            //设置价格
            shoppingCar.setProductPrice((productDao.getProduct(shoppingCar.getProductId()).
                    getPrice()).multiply(new BigDecimal(shoppingCar1.getCounts()+shoppingCar.getCounts())));
            LOGGER.info("购物车信息："+shoppingCar.toString());
            //更新购物车
            updateShoppingCar(shoppingCar);
        }
        Map<String, Object> resultMap = new HashMap<String,Object>(1);
        resultMap.put("result","success");
        return resultMap;
    }

    @Override
    public Map<String, Object> deleteShoppingCar(int userId, int productId) {
        shoppingCarDao.deleteShoppingCar(userId,productId);
        Map<String, Object> resultMap = new HashMap<String,Object>(1);
        resultMap.put("result","success");
        return resultMap;
    }

    @Override
    public boolean updateShoppingCar(ShoppingCar shoppingCar) {
        return shoppingCarDao.updateShoppingCar(shoppingCar);
    }

    @Override
    public Map<String, Object> getShoppingCars(int userId) {
        List<ShoppingCar> shoppingCarList = shoppingCarDao.getShoppingCars(userId);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingCarList);
        return resultMap;
    }
}
