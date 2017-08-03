package com.lavor.springboot.deployment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IDEA需要自己手动重新编译一下，才会触发classpath的变化
 * 默认情况下，devtools会监控到classpath中非静态资源。从而触发SpringBoot的重启
 * Created by lei.zeng on 2017/8/3.
 */
@RestController
public class DeploymentController {
    @RequestMapping("/deployment")
    public String deployment(){
        //每次修改返回字符串，然后在浏览器中访问该请求，
        // 看看更新后是否返回新的内容，即热部署是否成功
        return "helldsdso";
    }
}
