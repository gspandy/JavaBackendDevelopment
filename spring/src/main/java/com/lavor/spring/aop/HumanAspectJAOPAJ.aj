package com.lavor.spring.aop;

/**
 * Created by shitian on 2017-06-26.
 */

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 注入式AspectJ切面，注入式AspectJ与@AspectJ注解驱动差不多，只是表现形式变了，功能更加强大了
 * aspect是“.aj”为后缀的文件,最终会被编译成class文件
 * 除了Spring的jar包，还需要额外添加aspectjweaver与aspectjrt这两个jar包
 * 还需要再配置将aj文件编译成class文件的插件aspectj-maven-plugin
 */
public aspect HumanAspectJAOPAJ {
    pointcut sleepPoint():execution(* *.sleep(..));
    before() :sleepPoint(){
        System.out.println("睡觉前");
    }

    after() :sleepPoint(){
        System.out.println("睡觉后");
    }
    public static void main(String[] args){
        //根据classpath下面的spring的xml配置文件来获取应用程序上下文
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("aop/spring-aspectj-aop-aj.xml");
        //根据应用程序上下文，来获取其中的bean
        Sleepable sleeper = (Sleepable)context.getBean("human");
        sleeper.sleep();
    }


}
