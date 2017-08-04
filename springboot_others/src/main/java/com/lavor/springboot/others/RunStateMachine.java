package com.lavor.springboot.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 运行状态机
 * 实际应用中，我们会有在项目服务启动的时候就去加载一些数据或做一些事情这样的需求。
 为了解决这样的问题，spring Boot 为我们提供了一个方法，通过实现接口 CommandLineRunner 来实现。
 * Created by lei.zeng on 2017/8/4.
 */
@Component
public class RunStateMachine implements CommandLineRunner{
    @Autowired
    private StateMachine<StatusEnum, EventEnum> stateMachine;
    @Override
    public void run(String... strings) throws Exception {
        stateMachine.start();
        stateMachine.sendEvent(EventEnum.Login);
        stateMachine.sendEvent(EventEnum.Logout);
    }
}
