package com.lavor.spring.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 声明式事务
 * Created by lei.zeng on 2017/7/4.
 */
public class DeclarativeTransaction {
    public static void transaction(int type) {
        ApplicationContext context = null;
        UserDao userDao = null;
        switch (type) {
            //使用TransactionProxyFactoryBean实现声明式事务：每个Bean都有一个代理
            case 1:
                context = new ClassPathXmlApplicationContext("transaction/declarative-transaction.xml");
                userDao = (UserDao) context.getBean("transactionProxyFactoryBean");
                try {
                    userDao.insert();
                } catch (RuntimeException e) {
                }
                break;
            //使用TransactionProxyFactoryBean实现声明式事务：多个Bean共享一个代理
            case 2:
                context = new ClassPathXmlApplicationContext("transaction/declarative-transaction2.xml");
                userDao = (UserDao) context.getBean("userDaoImplBean");
                try {
                    userDao.insert();
                } catch (RuntimeException e) {
                }
                break;
            //使用拦截器实现声明式事务
            case 3:
                context = new ClassPathXmlApplicationContext("transaction/declarative-transaction3.xml");
                userDao = (UserDao) context.getBean("userDaoImpl");
                try {
                    userDao.insert();
                } catch (RuntimeException e) {
                }
                break;
            //使用tx标签配置的拦截器实现声明式事务
            case 4:
                context = new ClassPathXmlApplicationContext("transaction/declarative-transaction4.xml");
                userDao = (UserDao) context.getBean("userDaoImpl");
                try {
                    userDao.insert();
                } catch (RuntimeException e) {
                }
                break;
            //使用注解方式实现声明式事务
            case 5:
                context = new ClassPathXmlApplicationContext("transaction/declarative-transaction5.xml");
                userDao = (UserDao) context.getBean("userDaoAnnotation");
                try {
                    userDao.insert();
                } catch (RuntimeException e) {
                }
                break;
            default:
                break;

        }

    }

    public static void main(String[] args) {
        transaction(5);
    }

}
