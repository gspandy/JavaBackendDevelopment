package com.lavor.springboot.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lei.zeng on 2017/8/3.
 */
@Controller
public class UserController {
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    /**
     * 认证后才可以访问的页面
     * @return
     */
    @RequestMapping("/shiro")
    public String shiro() {
        return "/shiroPage.html";
    }

    /**
     * 需要权限才可以访问的页面
     * @return
     */
    @RequestMapping("/permission")
    @RequiresPermissions("permission")//权限管理;
    public String permission(){
        return "/permissionPage.html";
    }
    /**
     * 登陆成功后的页面
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "/indexPage.html";
    }

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "/loginPage.html";
    }

    /**
     * 登录执行的页面，判断登录是否成功
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/doLogin")
    public String doLogin(String username,String password) {
        //得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //登录，即身份验证
            subject.login(token);
            System.out.println("身份验证成功");
            return shiroFilterFactoryBean.getSuccessUrl();
        } catch (AuthenticationException e) {
            //身份验证失败
            System.out.println("身份验证失败");
            return shiroFilterFactoryBean.getLoginUrl();
        }
    }
}
