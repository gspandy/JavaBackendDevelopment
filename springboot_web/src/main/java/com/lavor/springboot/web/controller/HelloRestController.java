package com.lavor.springboot.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest请求，可以返回json
 * Created by lei.zeng on 2017/7/20.
 */
@RestController
public class HelloRestController {
    @RequestMapping("/helloRest")
    public String hello(){
        //给请求返回json字符串
        return "helloRest";
    }
}
