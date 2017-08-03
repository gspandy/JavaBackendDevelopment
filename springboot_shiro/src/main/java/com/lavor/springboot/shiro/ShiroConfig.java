package com.lavor.springboot.shiro;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro的配置
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMVC 通过DispachServlet 来主控制一样。
 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，
 所以我们需要定义一系列关于URL的规则和访问权限。
 * 配置LifecycleBeanPostProcessor，DefaultAdvisorAutoProxyCreator，AuthorizationAttributeSourceAdvisor三个Bean
 * 这样才会保证在 @RequiresPermissions注解的方法被调用时，
 * doGetAuthorizationInfo(PrincipalCollection principalCollection)方法的调用
 * Created by lei.zeng on 2017/8/3.
 */
@Configuration
public class ShiroConfig {
    /**
     * 配置Shiro的安全管理器的Bean
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //为安全管理器设置realm
        securityManager.setRealm(getShiroRealm());
        return securityManager;
    }

    /**
     *  配置Shiro的Filter的Bean，其中会设置Shiro的拦截链
     *  Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
        // 设置安全管理器，必须设置
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        //要求登录时的链接(可根据项目的URL进行替换),非必须的属性,默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功后要跳转的连接,逻辑也可以自定义，例如返回上次请求的页面
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //用户访问未对其授权的资源时,所显示的连接
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        //定义shiro过滤链（Map结构），从上向下顺序执行，一般将 /**放在最下面，
        // Map中key(xml中是指value值)的第一个'/'代表的路径是
        // 相对于HttpServletRequest.getContextPath()的值来的。
        //anon:所有url都都可以匿名访问;
        //authc: 需要认证才能进行访问;
        //user:配置记住我或认证通过可以访问；
        // 它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter */
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/shiro", "authc");
        filterChainDefinitionMap.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置用户认证与授权的realm
     * @return
     */
    @Bean
    public UserRealm getShiroRealm(){
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    /**
     * 配置LifecycleBeanPostProcessor，保证实现了Shiro内部lifecycle方法的执行
     * @return
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 设置默认的AOP代理
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }
    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
