package com.lavor.ssm.controller;

import com.lavor.ssm.entity.User;
import com.lavor.ssm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * controller层的控制器
 * @Controller注解controller层的bean
 * Created by lei.zeng on 2017/7/18.
 */
@Controller
public class UserController {
    //获取slf4j的Logger，使用slf4j的Logger而不使用log4j的Logger，
    // 这样可以随时更换jar包，使用其他的日志系统
    //注意：slf4j不是日志系统，而是日志系统的门面（一种封装）
    public static Logger logger= LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userServiceImpl;
    @RequestMapping("/findAllUser")
    public String findAllUser(){
        //日志打印debug信息，只有该类被配置到了log4j.properties中，其日志信息才会被打印
        logger.debug("查询所有User信息！");
        List<User> list=userServiceImpl.findAllUser();
        System.out.println(list);
        return "/index.jsp";
    }

}
