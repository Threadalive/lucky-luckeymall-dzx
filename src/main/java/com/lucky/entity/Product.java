package com.lucky.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Description 商品信息的实体类
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/5 00:27
 */
@Entity
@Table(name = "t_products")
public class Product {
    /**
     * 产品id
     */
    private int id;
    /**
     * 产品名
     */
    private String productName;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商品关键字
     */
    private String keyWord;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 商品库存
     */
    private int counts;
    /**
     * 商品类型
     */
    private int type;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment") //设置主键自增
    @GeneratedValue(generator = "generator")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "key_word")
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "counts")
    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
