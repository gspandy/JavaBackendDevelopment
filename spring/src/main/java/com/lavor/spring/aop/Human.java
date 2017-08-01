package com.lavor.spring.aop;

/**
 * Created by shitian on 2017-06-26.
 */
public class Human implements Sleepable {
    public void sleep() {
        System.out.println("切入点方法");
    }

}
