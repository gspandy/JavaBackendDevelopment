package com.lavor.springboot.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * 配置jersey
 * Jersey servlet将被注册，并默认映射到/*。
 * 可将@ApplicationPath添加到ResourceConfig来改变该映射。并且默认带有该映射的前缀
 * Created by lei.zeng on 2017/8/3.
 */
@Component
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        //配置jersey的restful的类（也可以是包）
        register(JerseyRest.class);
        //packages("com.lavor.springboot.rest");
    }
}
