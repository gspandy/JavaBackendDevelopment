package com.lavor.springboot.web.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * MyWebAppConfig继承WebMvcConfigurerAdapter，可以修改SpringMVC的默认配置
 * Created by shitian on 2017-07-23.
 */
@Configuration
public class MyWebAppConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //添加静态资源映射，会覆盖系统默认的配置
        registry.addResourceHandler("/mystatic/*").addResourceLocations("classpath:/mystatic/");
        //为了不覆盖掉SpringBoot默认的静态资源映射，我们手动添加上默认映射
        registry.addResourceHandler("/*").addResourceLocations("classpath:/META-INF/resources/","classpath:/resources/","classpath:/static/","classpath:/public/");
        super.addResourceHandlers(registry);
    }
}
