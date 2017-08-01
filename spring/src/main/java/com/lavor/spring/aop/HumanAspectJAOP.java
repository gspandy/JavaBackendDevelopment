package com.lavor.spring.aop;

/**
 * Created by shitian on 2017-06-26.
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @AspectJ注解驱动的切面
 * @AspectJ注解驱动支持五种通知：
 * @Before、@After、@AfterReturing、@AfterThrowing、@Around
 * 除了Spring的jar包，还需要额外添加aspectjweaver这个jar包
 */
@Aspect
public class HumanAspectJAOP {
    /**
     * 使用@Pointcut注解来定义切入点方法，切入点为所有名为sleep的方法
     * args中的是传递的参数，也可以不要args
     */
    @Pointcut("execution(* *.sleep())")
    public void sleepPoint() {
        //方法体实际上不会被执行
    }

    /**
     * @Around注解标记的是一个环绕通知
     * 其后面的括号里的是@Pointcut注解标记的方法（即切入点）
     */
    @Around("sleepPoint()")
    public void around(ProceedingJoinPoint jp) {
        try {
            System.out.println("前置通知");
            //执行被拦截的方法
            jp.proceed();
            System.out.println("正常返回后通知");
        } catch (Throwable e) {
            System.out.println("异常后通知");
        }
    }
    public static void main(String[] args){
        //根据classpath下面的spring的xml配置文件来获取应用程序上下文
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("aop/spring-aspectj-aop.xml");
        //根据应用程序上下文，来获取其中的bean
        Sleepable sleeper = (Sleepable)context.getBean("human");
        sleeper.sleep();
    }
}
