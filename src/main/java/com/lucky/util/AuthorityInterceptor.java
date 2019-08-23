package com.lucky.util;

import com.lucky.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 权限校验拦截器
 * @Author zhenxing.dong
 * @Date 2019/8/20 17:27
 */
@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    /**
     * 日志服务对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            //检查用户是否登录
            User user = (User) httpServletRequest.getSession().getAttribute("currentUser");

            if (user == null) {
                //未登录，跳到登录界面
                LOGGER.info("AuthorityInterceptor 检查到未登录系统，强制跳转到登录页面");
                httpServletResponse.sendRedirect("/login");
            }
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
