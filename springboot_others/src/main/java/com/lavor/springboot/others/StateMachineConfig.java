package com.lavor.springboot.others;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * 状态机配置
 * @EnableStateMachine开启状态机
 * Created by lei.zeng on 2017/8/4.
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<StatusEnum, EventEnum> {
    /**
     * 初始化状态机状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<StatusEnum, EventEnum> states) throws Exception {
        states.withStates()
                // 定义初始状态
                .initial(StatusEnum.NOTLANDED)
                // 设置状态机状态类
                .states(EnumSet.allOf(StatusEnum.class));
    }

    /**
     * 初始化状态迁移事件
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<StatusEnum, EventEnum> transitions)
            throws Exception {
        transitions
                // 登陆事件，使得状态从未登录状态迁移到登录状态
                .withExternal()
                .source(StatusEnum.NOTLANDED)
                .target(StatusEnum.LANDED)
                .event(EventEnum.Login)
                .and()
                // 登出事件，使得状态从登录状态迁移到未登录状态
                .withExternal()
                .source(StatusEnum.LANDED)
                .target(StatusEnum.NOTLANDED)
                .event(EventEnum.Logout);
    }
}
