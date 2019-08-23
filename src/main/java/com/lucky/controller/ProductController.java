package com.lucky.controller;

import com.alibaba.fastjson.JSONObject;
import com.lucky.entity.PageBean;
import com.lucky.entity.Product;
import com.lucky.service.ProductService;
import com.lucky.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 产品管理类，包括了商品的增删改查，以及商品图片的上载功能。
 *
 * @Author zhenxing.dong
 * @Date 2019/8/5 00:40
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    /**
     * 引用商品管理的服务类。
     */
    @Resource
    private ProductService productService;

    /**
     * 当用户访问此路径时，返回一个商品详情页面的视图。
     * URL如: http:localhost:8080/product?getProductDetail
     *
     * @return 商品详情页
     */
    @GetMapping(params = "getProductDetail")
    public ModelAndView getProductDetail() {
        ModelAndView view = new ModelAndView();
        view.setViewName("productView/product_detail");
        return view;
    }


    /**
     * 返回一个商品信息修改页面
     *
     * @return 商品信息修改页面
     */
    @GetMapping(params = "alterProductMsg")
    public ModelAndView getAlterProductPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("admin/alter_product_msg");
        return view;
    }
    /**
     * 给前台返回一个搜索页面
     *
     * @return 搜索页
     */
    @GetMapping(params = "search")
    public ModelAndView search() {
        ModelAndView view = new ModelAndView();
        view.setViewName("productView/search");
        return view;
    }

    /**
     * 根据指定id返回一个商品的商品详情信息。
     *
     * @param id          商品id
     * @param httpSession 临时缓存session
     * @return 执行结果
     */
    @PostMapping(params = "getProductDetail")
    @ResponseBody
    public Map<String, Object> getProductDetail(int id, HttpSession httpSession) {
        LOGGER.info("根据"+id+"获取商品详情");
        return productService.getProductDetail(id,httpSession);
    }

    /**
     * 实现商品添加功能。
     *
     * @param product 添加商品的具体对象
     * @param request 请求
     * @param file 上传图片
     * @return 商品添加结果
     */
    @PostMapping(params = "addProduct")
    @ResponseBody
    public Map<String, Object> addProduct(Product product, MultipartFile file, HttpServletRequest request) {
        LOGGER.info(JSONObject.toJSONString(file));
        return productService.addProduct(product,file,request);
    }


    /**
     * 实现商品更新功能。
     *
     * @param product 添加商品的具体对象
     * @param request 请求
     * @param file 上传图片
     * @return 商品更新结果
     */
    @PostMapping(params = "updateProduct")
    @ResponseBody
    public Map<String, Object> updateProduct(Product product, MultipartFile file, HttpServletRequest request) {
        Map<String,Object> resultMap = new HashMap<>();
        LOGGER.info(JSONObject.toJSONString(file));
        if(productService.updateProduct(product,file,request)){
            resultMap.put("result","success");
        }else {
            resultMap.put("result","fail");
        }
        return resultMap;
    }
    /**
     * 实现商品的删除功能。
     *
     * @param id 指定要删除的商品id
     * @return Response 删除状态
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Response deleteProduct(@PathVariable int id) {
        LOGGER.info("删除id为"+id+"的商品");
        return productService.deleteProduct(id);
    }

    /**
     * 关键字搜索的前置动作，暂存用户搜索的关键字与session中。
     *
     * @param searchKeyWord 搜索的关键字
     * @param httpSession   临时缓存
     * @return 前置处理结果
     */
    @PostMapping(params = "searchPre")
    @ResponseBody
    public Map<String, Object> searchPre(String searchKeyWord, HttpSession httpSession) {
        httpSession.setAttribute("searchKeyWord", searchKeyWord);
        LOGGER.info("设置session属性 搜索关键字"+searchKeyWord);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "success");
        return resultMap;
    }

    /**
     * 根据商品关键字查询相关商品，信息以字符串形式返回给前台。
     *
     * @param searchKeyWord 用户搜索的关键字
     * @return 搜索结果
     */
    @PostMapping(params = "searchProductByKeyWord")
    @ResponseBody
    public Map<String, Object> searchProductByKeyWord(String searchKeyWord) {
        return productService.getProductsByKeyWord(searchKeyWord);
    }

    /**
     * 根据商品类型查询相关商品，信息以字符串形式返回给前台。
     *
     * @param type 用户查询的类型
     * @return 搜索结果
     */
    @PostMapping(params = "searchProductByType")
    @ResponseBody
    public Map<String, Object> searchProductByType(int type) {
        Map<String,Object> resultMap = new HashMap<>();
        LOGGER.info("搜索商品类型为"+type);
        List<Product> productList = productService.getProductsByType(type);
        resultMap.put("result",productList);
        return resultMap;
    }
    /**
     * 实现根据商品的id获取到指定商品，并将其商品信息以字符串形式返回。
     *
     * @param id 要查询的商品id
     * @return 搜索结果
     */
    @PostMapping(params = "getProductById")
    @ResponseBody
    public Map<String, Object> getProductById(int id) {
        LOGGER.info("搜索商品id为"+id);
        return productService.getProductById(id);
    }

    /**
     * 设置要更新商品的id
     * @param id 商品id
     * @param httpSession 暂存
     * @return 设置结果
     */
    @PostMapping(params = "setId")
    @ResponseBody
    public Map<String,Object> setUpdateProductId(int id, HttpSession httpSession){
        Map<String, Object> resultMap = new HashMap<>();
        httpSession.setAttribute("alterProduct",id);
        LOGGER.info("session设置要修改的商品id"+id);
        resultMap.put("result", "success");
        return resultMap;
    }
    /**
     * 实现获取全部商品功能。
     *
     * @param request 请求
     * @return 获取结果
     */
    @PostMapping(params = "getAllProducts")
    @ResponseBody
    public PageBean getAllProducts(HttpServletRequest request) {
        String pageNo = request.getParameter("pageNo");
        if(pageNo == null){
            pageNo = "1";
        }
        PageBean pageBean = productService.getAllProduct(Integer.valueOf(pageNo), 6);
        LOGGER.info("当前页"+pageNo);
        return pageBean;
    }
    /**
     * 实现获取全部商品功能。
     *
     * @return 获取结果
     */
    @PostMapping(params = "getAllProduct")
    @ResponseBody
    public PageBean getAllProduct() {
        int pageSize = productService.count();
        PageBean pageBean = productService.getAllProduct(1, pageSize);
        return pageBean;
    }

    /**
     * 获取商品数
     *
     * @return 商品数
     */
    @PostMapping(params = "getProductCount")
    @ResponseBody
    public Map<String, Object> getProductCount() {
        return productService.getProductCount();
    }

}
