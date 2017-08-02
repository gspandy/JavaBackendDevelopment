package com.lavor.springboot.sql.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lei.zeng on 2017/8/2.
 */
@Controller("jpaUserController")
public class UserController {
    @Autowired
    @Qualifier("jpaUserService")
    private UserService userService;
    @RequestMapping("/jpa")
    public String getUserById(){
        User user=userService.selectUserById(1);
        System.out.println(user.toString());
        return "/index.html";
    }
}
