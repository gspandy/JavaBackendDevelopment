package com.lavor.springmvc;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器：可以在处理请求前进行一系列的相关处理，
 * 创建一个拦截器很简单，实现HandlerInterceptor接口就行
 * 使用时，一般需要在Spring的xml配置文件中定义该类的Bean
 * Created by lei.zeng on 2017/7/6.
 */
public class Interceptor implements HandlerInterceptor{
    /**
     * 预处理操作，在请求前进行的操作
     * @return 为false时，请求页面不存在并且不存在对请求的处理方法时，preHandle方法执行两次
     *                     请求页面存在或者存在对请求的处理方法时，preHandle方法执行一次
     *          为true时，preHandle，postHandle，afterCompletion三个方法依次执行依次一次
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("preHandle");
        return true;
    }

    /**
     * DispatcherServlet类导向到view进行render之前依次执行
     * 也就是在Controller中请求返回前一刻执行
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    /**
     * 请求完成后进行的操作，在DispatcherServlet类导向到view进行render之后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
    }
}
