package com.lavor.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 拦截器的使用用例
 * Created by lei.zeng on 2017/7/6.
 */
@Controller
public class InterceptorCase {
    @RequestMapping("/interceptor")
    public String interceptor(){
        System.out.println("request");
        return "index.jsp";
    }
    @RequestMapping("/interceptor/2")
    public String interceptor2(){
        return "index.jsp";
    }
}
