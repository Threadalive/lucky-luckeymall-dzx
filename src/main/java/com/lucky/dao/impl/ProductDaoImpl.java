package com.lucky.dao.impl;

import com.lucky.dao.ProductDao;
import com.lucky.entity.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 对产品数据库实现操作的具体dao层。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/5 00:45
 */
@Repository
public class ProductDaoImpl implements ProductDao {

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
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);

    @Override
    public Product getProduct(int id) {
        return hibernateTemplate.get(Product.class, id);
    }

    @Override
    public Product getProduct(String productName) {
        return hibernateTemplate.get(Product.class, productName);
    }

    @Override
    public void addProduct(Product product) {
        hibernateTemplate.save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        try {
            hibernateTemplate.delete(hibernateTemplate.load(Product.class, id));
            return true;
        } catch (Exception e) {
            LOGGER.error("商品删除失败",e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            hibernateTemplate.update(product);
            return true;
        } catch (Exception e) {
            LOGGER.error("商品更新失败",e.getMessage());
            return false;
        }
    }

    @Override
    public List<Product> getProductsByKeyWord(String searchKeyWord) {
        //在输入的每个关键字首尾加%符号，在数据库中进行like匹配
        String hql = "from Product where productName like concat('%',?,'%') or keyWord like concat('%',?,'%')";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, searchKeyWord);
        query.setParameter(1, searchKeyWord);
        return query.list();
    }

    @Override
    public List<Product> getProductsByType(int type) {
        String hql = "from Product where type=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, type);
        return query.list();
    }

    @Override
    public List<Product> getAllProduct(int offset,int length) {
        List<Product> productList = null;
        //Spring4于Hibernate5之间存在冲突，类型无法转换，这里用Hibernate4
        try {
            String hql = "from Product";
            Query query =  sessionFactory.getCurrentSession().createQuery(hql);
            query.setFirstResult(offset);
            query.setMaxResults(length);
            productList = query.list();
        } catch (Exception e) {
            LOGGER.error("商品获取失败",e.getMessage());
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public int countProduct() {
        String hql = "select max(id) from Product";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        Object count = query.list().get(0);
        return Integer.parseInt(count.toString());
    }

    @Override
    public int getProductCount() {
        String hql = "select count(*) from Product";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        Object count = query.uniqueResult();
        return Integer.parseInt(count.toString());
    }
}
