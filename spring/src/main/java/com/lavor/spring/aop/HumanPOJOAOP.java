package com.lavor.spring.aop;

/**
 * Created by shitian on 2017-06-26.
 */

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 纯POJO切面
 * 侵入性最小的Spring AOP方法
 */
public class HumanPOJOAOP {
    /**
     * 前置通知
     */
    public void before() {
        System.out.println("前置通知");
    }

    /**
     * 返回后通知，不管切入点方法正常返回，还是抛出异常返回都会执行该通知
     */
    public void after() {
        System.out.println("返回后通知");
    }
    public static void main(String[] args){
        //根据classpath下面的spring的xml配置文件来获取应用程序上下文
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("aop/spring-pojo-aop.xml");
        //根据应用程序上下文，来获取其中的bean
        Sleepable sleeper = (Sleepable)context.getBean("human");
        sleeper.sleep();
    }
}
