package com.lavor.springboot.jsp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shitian on 2017-07-31.
 * 控制器中要返回jsp视图，必须保证pom.xml中没有导入Thymeleaf、FreeMarker等模板
 * 否则可能按照模板的方式返回，因而导致不能返回jsp页面
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "index";
    }
}
