package com.lavor.springboot.sql.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lei.zeng on 2017/8/2.
 * 不要以为用@Repository注解了UserDaoMapper就可以直接获取它的Bean
 * 必须使用@MapperScan("com.lavor.springboot.sql.mybatis")扫描到它才行
 */
@Controller("mybatisUserController")
@MapperScan("com.lavor.springboot.sql.mybatis")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/mybatis")
    public String getUserById(){
        User user=userService.selectUserById(1);
        System.out.println(user.toString());
        return "/index.html";
    }
}
