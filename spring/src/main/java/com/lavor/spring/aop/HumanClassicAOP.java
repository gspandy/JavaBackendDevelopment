package com.lavor.spring.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;


/**
 * Created by shitian on 2017-06-26.
 */

/**
 * 经典的基于代理的AOP
 * 可以实现MethodBeforeAdvice,AfterReturningAdvice,ThrowsAdvice,MethodInterceptor,IntroductionInterceptor等通知接口
 * HumanClassicAOP是代理类，也就是Spring AOP中的通知
 */
public class HumanClassicAOP implements MethodBeforeAdvice,AfterReturningAdvice{
    /**
     * 正常返回后通知，在切入点方法返回后执行
     * @param returnValue 切入点方法的返回值
     * @param method 切入点方法
     * @param arguments 切入点方法中的参数
     * @param target 目标，即被通知的对象
     * @throws Throwable
     */
    public void afterReturning(Object returnValue, Method method, Object[] arguments, Object target) throws Throwable {
        System.out.println("正常返回后通知");

    }

    /**
     * 前置通知，在切入点方法执行前执行
     * @param method 切入点方法
     * @param arguments 切入点方法中的参数
     * @param target 目标，即被通知的对象
     * @throws Throwable
     */
    public void before(Method method, Object[] arguments, Object target) throws Throwable {
        System.out.println("前置通知");
    }
    public static void main(String[] args){
        //根据classpath下面的spring的xml配置文件来获取应用程序上下文
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("aop/spring-classic-aop.xml");
        //根据应用程序上下文，来获取其中的bean
        Sleepable sleeper = (Sleepable)context.getBean("proxy");
        sleeper.sleep();
    }

}
