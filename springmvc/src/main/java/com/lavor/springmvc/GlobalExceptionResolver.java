package com.lavor.springmvc;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常异常处理：实现HandlerExceptionResolver接口来处理全局异常,处理所有Controller中的异常
 * 使用时，一般需要在Spring的xml配置文件中配置该全局异常处理类
 * 异常处理优先级:局部异常>全局异常>500错误页面设置>页面出错
 * SimpleMappingExceptionResolver也是一种全局处理异常的方式,哪个全局异常处理的bean先在Spring中注册，就启用谁
 * Created by shitian on 2017/7/5.
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String uri = httpServletRequest.getRequestURI();
        System.out.println(uri);
        System.out.println("出现全局异常");
        if (uri.equals("/null")){
            throw new NullPointerException();
        }
        return new ModelAndView("/error/global.jsp");
    }
}
