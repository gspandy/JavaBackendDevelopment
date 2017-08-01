package com.lavor.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

/**
 * RESTful API中，URL中只使用名词来指定资源，原则上不使用动词
 * Rest用HTTP协议里的动词来实现资源的增删改查等操作，Rest一般传递的是json数据
 * 关于json数据的传递可以参考JsonAndAjaxController
 * 比如GET（查询资源），POST（新建或者更新资源），PUT（修改资源中的信息），PATCH（整体替换资源），DELETE（删除资源）
 * Created by shitian on 2017-07-16.
 */
@Controller
@RequestMapping("/user")
public class RestController {
    @RequestMapping(value = "value=/{id}",method = RequestMethod.GET)
    public String get(@PathVariable ("id") Integer id){
        //do:获取数据库中user表中指定id的数据
          return null;
    }
    @RequestMapping(value = "value=/{id}",method = RequestMethod.DELETE)
    public String post(@PathVariable ("id") Integer id){
        //do:删除数据库中user表中指定id的数据
        return null;
    }
}
