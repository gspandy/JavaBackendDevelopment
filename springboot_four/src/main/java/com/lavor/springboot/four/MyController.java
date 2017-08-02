package com.lavor.springboot.four;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lei.zeng on 2017/8/2.
 */
@Controller
public class MyController {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello");
        return "index.html";
    }
}
