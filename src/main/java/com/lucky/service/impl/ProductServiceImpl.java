package com.lucky.service.impl;

import com.lucky.dao.CommentDao;
import com.lucky.dao.ProductDao;
import com.lucky.dao.ShoppingCarDao;
import com.lucky.dao.ShoppingRecordDao;
import com.lucky.entity.Product;
import com.lucky.service.ProductService;
import com.lucky.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 产品管理类的服务类，包括对商品的增删改查功能。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/5 00:43
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * 用于操作产品类数据库的dao层。
     */
    @Autowired
    ProductDao productDao;
    /**
     * 商品评论的dao层类
     */
    @Autowired
    private CommentDao commentDao;

    /**
     * 购物车的dao层类
     */
    @Autowired
    private ShoppingCarDao shoppingCarDao;

    /**
     * 订单的dao层类
     */
    @Autowired
    private ShoppingRecordDao shoppingRecordDao;
    /**
     * 用户细节信息管理的服务类.
     */

    @Override
    public Product getProduct(String productName) {
        return productDao.getProduct(productName);
    }

    @Override
    public Map<String, Object> addProduct(Product product) {
        String result = "fail";
        productDao.addProduct(product);
        Map<String, Object> resultMap = new HashMap<>();
        result = "success";
        resultMap.put("result", result);
        return resultMap;
    }

    @Override
    public Response deleteProduct(int id) {
        try {
            commentDao.deleteCommentByUserId(id);
            shoppingCarDao.deleteShoppingCarByProduct(id);
            shoppingRecordDao.deleteShoppingRecordByProductId(id);
            productDao.deleteProduct(id);
            return new Response(1, "删除商品成功", null);
        }catch (Exception e){
            return new Response(0,"删除商品失败",null);
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public Map<String, Object> getProductsByKeyWord(String searchKeyWord) {
        List<Product> productList = new ArrayList<>();
        productList = productDao.getProductsByKeyWord(searchKeyWord);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", productList);
        return resultMap;
    }

    @Override
    public List<Product> getProductsByType(int type) {
        return productDao.getProductsByType(type);
    }

    @Override
    public Map<String, Object> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        productList = productDao.getAllProduct();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("allProducts", productList);
        return resultMap;    }

    @Override
    public Map<String, Object> getProductDetail(int id, HttpSession httpSession) {
        Product product = productDao.getProduct(id);
        httpSession.setAttribute("productDetail", product);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "success");
        return resultMap;
    }

    @Override
    public Map<String, Object> getProductById(int id) {
        Product product = productDao.getProduct(id);
//        String searchResult = JSONArray.toJSONString(product);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", product);
        return resultMap;
    }

    @Override
    public Map<String, Object> uploadFile(MultipartFile productImgUpload, String productName, HttpServletRequest request) {
        String result = "fail";
        try {
            //当productImgUpload不为空时
            if (productImgUpload != null && !productImgUpload.isEmpty()) {
                //通过请求获取文件夹的路径
                String fileRealPath = request.getSession().getServletContext().getRealPath("/static/img");
                //根据获取的商品id选取img文件夹下的对应图片的文件名
                int id = getProduct(productName).getId();
                String imgFileName = String.valueOf(id) + ".jpg";
                File fileFolder = new File(fileRealPath);
                //若文件夹不存在则创建一个
                if (!fileFolder.exists()) {
                    fileFolder.mkdirs();
                }
                //获取具体文件，进行上传
                File file = new File(fileRealPath, imgFileName);
                productImgUpload.transferTo(file);
                result = "success";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);
        return resultMap;
    }
}
