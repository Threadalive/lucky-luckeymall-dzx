package com.lucky.dao.impl;

import com.lucky.dao.ProductDao;
import com.lucky.entity.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 对产品数据库实现操作的具体dao层。
 *
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/5 00:45
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    /**
     * Hibernate中的session工厂，用于获取会话创建查询。
     */
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Product getProduct(int id) {
        String hql = "from Product where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,id);
        return (Product) query.uniqueResult();
    }

    @Override
    public Product getProduct(String productName) {
        String hql = "from Product where productName=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,productName);
        return (Product) query.uniqueResult();
    }

    @Override
    public void addProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        String hql = "delete Product where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,id);
        return query.executeUpdate()>0;
    }

    @Override
    public boolean updateProduct(Product product) {
        String hql = "update Product set productName=?,description=?,keyWord=?,price=?,counts=?,type=? where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,product.getProductName());
        query.setParameter(1,product.getDescription());
        query.setParameter(2,product.getKeyWord());
        query.setParameter(3,product.getPrice());
        query.setParameter(4,product.getCounts());
        query.setParameter(5,product.getType());
        query.setParameter(6,product.getId());
        return query.executeUpdate()>0;
    }

    @Override
    public List<Product> getProductsByKeyWord(String searchKeyWord) {
        //在输入的每个关键字中穿插%符号，在数据库中进行like匹配
        String queryKeyWord = "%";
        for(int i=0;i<searchKeyWord.length();i++){
            queryKeyWord += String.valueOf(searchKeyWord.charAt(i)) +"%";
        }

        String hql = "from Product where productName like ? or keyWord like ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,queryKeyWord);
        query.setParameter(1,queryKeyWord);
        return query.list();
    }

    @Override
    public List<Product> getProductsByType(int type) {
        String hql = "from Product where type=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,type);
        return query.list();
    }

    @Override
    public List<Product> getAllProduct() {
        String hql = "from Product";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
