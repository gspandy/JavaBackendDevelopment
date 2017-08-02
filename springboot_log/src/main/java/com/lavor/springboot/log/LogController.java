package com.lavor.springboot.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Logback是log4j框架的作者开发的新一代日志框架，它效率更高、能够适应诸多的运行环境，同时天然支持SLF4J。
 * 它是SpringBoot官方推荐使用的日志框架，也是默认的日志框架
 * Created by shitian on 2017-08-02.
 */
@Controller
public class LogController {
    private static Logger logger= LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/log")
    public String log(){
        logger.debug("LogController中的/log请求");
        return "/index.html";
    }
}
