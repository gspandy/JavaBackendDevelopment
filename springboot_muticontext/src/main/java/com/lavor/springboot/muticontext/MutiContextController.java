package com.lavor.springboot.muticontext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Java可以执行jar包或者war包时设置环境，比如：
 * java -jar xxx.jar --spring.profiles.active=test
 * Created by shitian on 2017-08-02.
 */
@RestController
public class MutiContextController {
    @Value("${context.name}")
    private String contextName;

    /**
     * 返回环境名的json数据
     * @return
     */
    @RequestMapping("/getContextName")
    public String getContextName(){
        System.out.println(contextName);
        return contextName;
    }
}
