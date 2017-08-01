package com.lavor.springboot.web.controller;

import com.lavor.springboot.web.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.Map;

/**
 * Created by lei.zeng on 2017/7/20.
 */
@Controller
public class HelloController {
    //注解获取application.properties中的属性值
    @Value("${spring.messages.basename}")
    private String basename;
    /**
     * 返回Thymeleaf模板页面
     */
    @GetMapping("/toLeaf")
    public ModelAndView toLeaf() {
        ModelAndView modelAndView = new ModelAndView("leaf");
        User user=new User(1,"lavor");
        modelAndView.addObject("user", user);
        System.out.println(basename);
        return modelAndView;
    }
}
