package com.lavor.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shitian on 2017-06-26.
 */

/**
 * 用@Configuration注解类，然后在该类中创建装配bean的方法，用@Bean注解该方法
 */
@Configuration
public class UserJavaConfig{
    @Bean(name="user2" )
    public User getUser(){
        return new User(1,"lavor");
    }

}