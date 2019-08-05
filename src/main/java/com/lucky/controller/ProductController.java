package com.lucky.controller;

import com.alibaba.fastjson.JSONArray;
import com.lucky.entity.Product;
import com.lucky.service.ProductService;
import com.lucky.util.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 产品管理类，包括了商品的增删改查，以及商品图片的上载功能。
 *
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/5 00:40
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    /**
     * 引用商品管理的服务类。
     */
    @Resource
    private ProductService productService;

    /**
     * 当用户访问此路径时，返回一个商品详情页面的视图。
     * URL如: http:localhost:8080/product?getProductDetail
     *
     * @return org.springframework.web.servlet.ModelAndView
     * @author zhenxing.dong@luckincoffee.com
     */
    @GetMapping(params = "getProductDetail")
    public ModelAndView getProductDetail() {
        ModelAndView view = new ModelAndView();
        view.setViewName("productView/product_detail");
        return view;
    }

    @GetMapping(params = "search")
    public ModelAndView search() {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/search");
        return view;
    }

    /**
     * 根据指定id返回一个商品的商品详情信息。
     *
     * @param id          商品id
     * @param httpSession 临时缓存session
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author zhenxing.dong@luckincoffee.com
     */
    @PostMapping(params = "getProductDetail")
    @ResponseBody
    public Map<String, Object> getProductDetail(int id, HttpSession httpSession) {
        Product product = productService.getProduct(id);
        httpSession.setAttribute("product", product);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "success");
        return resultMap;
    }

    /**
     * 实现商品添加功能。
     *
     * @param product 添加商品的具体对象
     * @return java.util.Map<java.lang.String , java.lang.Object>
     * @author zhenxing.dong@luckincoffee.com
     */
    @PostMapping(params = "addProduct")
    @ResponseBody
    public Map<String, Object> addProduct(Product product) {
        String result = "fail";
        productService.addProduct(product);
        Map<String, Object> resultMap = new HashMap<>();
        result = "success";
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 实现商品的删除功能。
     *
     * @param id 指定要删除的商品id
     * @return com.lucky.util.Response
     * @author zhenxing.dong@luckincoffee.com
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Response deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }


    /**
     * 关键字搜索的前置动作，暂存用户搜索的关键字与session中。
     *
     * @param searchKeyWord 搜索的关键字
     * @param httpSession   临时缓存
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author zhenxing.dong@luckincoffee.com
     */
    @PostMapping(params = "searchPre")
    @ResponseBody
    public Map<String, Object> searchPre(String searchKeyWord, HttpSession httpSession) {
        httpSession.setAttribute("searchKeyWord", searchKeyWord);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "success");
        return resultMap;
    }

    /**
     * 根据商品关键字查询相关商品，信息以字符串形式返回给前台。
     *
     * @param searchKeyWord 用户搜索的关键字
     * @return java.util.Map<java.lang.String , java.lang.Object>
     * @author zhenxing.dong@luckincoffee.com
     */
    @PostMapping(params = "searchProductByKeyWord")
    @ResponseBody
    public Map<String, Object> searchProductByKeyWord(String searchKeyWord) {
        List<Product> productList = new ArrayList<>();
        productList = productService.getProductsByKeyWord(searchKeyWord);

        //将查询到的商品数组转成JSON形式的字符串
        String searchResult = JSONArray.toJSONString(productList);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", searchResult);
        return resultMap;
    }

    /**
     * 实现根据商品的id获取到指定商品，并将其商品信息以字符串形式返回。
     *
     * @param id 要查询的商品id
     * @return java.util.Map<java.lang.String , java.lang.Object>
     * @author zhenxing.dong@luckincoffee.com
     */
    @PostMapping(params = "searchProductById")
    @ResponseBody
    public Map<String, Object> searchProductById(int id) {
        Product product = productService.getProduct(id);
        String searchResult = JSONArray.toJSONString(product);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", searchResult);
        return resultMap;
    }

    /**
     * 实现获取全部商品功能。
     *
     * @return java.util.Map<java.lang.String , java.lang.Object>
     * @author zhenxing.dong@luckincoffee.com
     */
    @PostMapping(params = "getAllProducts")
    @ResponseBody
    public Map<String, Object> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList = productService.getAllProduct();
        String allProducts = JSONArray.toJSONString(productList);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("allProducts", allProducts);
        return resultMap;
    }

    /**
     * 使用MultipartFile实现商品图片的上传功能。
     *
     * @param productImgUpload 图片文件上传对象
     * @param productName      指定商品名
     * @param request          用户请求
     * @return java.util.Map<java.lang.String , java.lang.Object>
     * @author zhenxing.dong@luckincoffee.com
     */
    @PostMapping(params = "uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam MultipartFile productImgUpload, String productName, HttpServletRequest request) {
        String result = "fail";
        try {
            //当productImgUpload不为空时
            if (productImgUpload != null && !productImgUpload.isEmpty()) {
                //通过请求获取文件夹的路径
                String fileRealPath = request.getSession().getServletContext().getRealPath("/static/img");
                //根据获取的商品id选取img文件夹下的对应图片的文件名
                int id = productService.getProduct(productName).getId();
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
