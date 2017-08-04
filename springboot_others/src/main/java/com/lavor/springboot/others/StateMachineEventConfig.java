package com.lavor.springboot.others;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * 状态监听器，所有StateMachineListener接口中定义的事件都能通过注解的方式来进行配置实现
 * Created by lei.zeng on 2017/8/4.
 */
@WithStateMachine
public class StateMachineEventConfig {
    private static Logger logger = LoggerFactory.getLogger(StateMachineEventConfig.class);
    /**
     * @OnTransition注解时，source和target都是枚举类中字段的字符串形式
     * 监听到状态是事件导致状态从source变为target时，触发该方法
     * 如果source或者target不设置，表示不对其做限制
     * 比如：source和target都不设置，则任何事件都会触发该方法呢
     */
    @OnTransition(source = "NOTLANDED",target = "LANDED")
    public void login() {
        logger.info("登录：状态从NOTLANDED->LANDED");
    }
    @OnTransition(source = "LANDED", target = "NOTLANDED")
    public void logout() {
        logger.info("登出：状态从LANDED->NOTLANDED");
    }

}
