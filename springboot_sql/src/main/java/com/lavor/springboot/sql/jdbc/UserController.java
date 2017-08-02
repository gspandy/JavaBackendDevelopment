package com.lavor.springboot.sql.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lei.zeng on 2017/8/2.
 */
@Controller("jdbcUserController")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/jdbc")
    public String getUserById(){
        User user=userService.selectUserById(1);
        System.out.println(user.toString());
        return "/index.html";
    }
}
