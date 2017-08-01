package com.lavor.springmvc;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 控制器的使用：实现Controller接口实现的控制器，一个控制器只能处理一个请求，不常用
 * Created by shitian on 2017/7/5.
 */
public class JavaController implements Controller{
    /**
     * 处理请求
     * @param httpServletRequest 请求
     * @param httpServletResponse 响应
     * @return
     * @throws Exception
     */
    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        //请求处理完毕后返回的视图，返回index.jsp页面
        return new ModelAndView("/index.jsp");
    }
}
