package com.lavor.springboot.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * 异步任务与异步任务回调
 * Created by lei.zeng on 2017/8/3.
 */
@RestController
public class TaskController {
    public String task() throws Exception {
        doTask();
        Future<String> task2 = doTask2();
        //一直执行知道确定task2执行完毕
        while(true) {
            if(task2.isDone()) {
                break;
            }
            Thread.sleep(100);
        }
        return "success";
    }

    /**
     * 使用@Async注解定义异步任务
     * @throws Exception
     */
    @Async
    public void doTask() throws Exception {
        System.out.println("我是异步执行的任务，无回调！");
    }
    /**
     * 使用@Async注解定义异步任务，返回类型是Future<String>，表示该异步任务可以回调
     * @throws Exception
     */
    @Async
    public Future<String> doTask2() throws Exception {
        System.out.println("我是异步执行的任务，有回调！");
        return new AsyncResult<>("完成异步回调任务！");
    }

}
