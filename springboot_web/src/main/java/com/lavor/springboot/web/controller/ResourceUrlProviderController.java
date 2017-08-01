package com.lavor.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

/**
 * @ControllerAdvice注解，顾名思义是控制器通知
 * 可以在控制器调用时，进行一些处理
 * Created by shitian on 2017-07-23.
 */
@ControllerAdvice
public class ResourceUrlProviderController {
    @Autowired
    private ResourceUrlProvider resourceUrlProvider;

    /**
     * @ModelAttribute("urls")可以让我们在控制器处理请求时，引入该属性
     * 凡是控制器中返回的页面，都会自动加入该属性及其值
     */
    @ModelAttribute("urls")
    public ResourceUrlProvider urls() {
        return this.resourceUrlProvider;
    }
}