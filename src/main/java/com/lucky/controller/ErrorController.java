package com.lucky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 错误处理控制器
 *
 * @Author zhenxing.dong
 * @Date 2019/8/22 14:44
 */
@Controller
@RequestMapping("error")
public class ErrorController {
    /**
     * 设置错误基本路径
     */
    private static final String BASE_DIR = "error/";

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    /**
     * 400错误处理页面
     * @param request 请求
     * @return 400错误处理页
     */
    @RequestMapping("400")
    public String handle400ErrorPage(HttpServletRequest request){
        LOGGER.info("跳转400错误");
        return BASE_DIR + "400";
    }

    /**
     * 404错误处理页面
     * @param request 请求
     * @return 404处理页
     */
    @RequestMapping("404")
    public String handle404ErrorPage(HttpServletRequest request){
        LOGGER.info("跳转404错误");
        return BASE_DIR + "404";
    }

    /**
     * 500错误处理页面
     * @param request 请求
     * @return 500处理页
     */
    @RequestMapping("500")
    public String handle500ErrorPage(HttpServletRequest request){
        LOGGER.info("跳转500错误");
        return BASE_DIR + "500";
    }
}
