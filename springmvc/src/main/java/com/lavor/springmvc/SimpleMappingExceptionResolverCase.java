package com.lavor.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SimpleMappingExceptionResolver的使用:在Spring的xml配置中定义了该类的Bean
 * SimpleMappingExceptionResolver提供了通过异常类型exceptionMappings来进行异常与视图之间的映射关系，
 * 提供了在发生异常时通过statusCodes来映射异常返回的视图名称和对应的HttpServletResponse的返回码。
 * Created by lei.zeng on 2017/7/5.
 */
@Controller
public class SimpleMappingExceptionResolverCase {
    @RequestMapping("/null")
    public void testNullPointerException() {
        System.out.println("null");
        User user = null;
        //这里就会发生空指针异常，然后就会返回定义在SpringMVC配置文件中的null视图
        System.out.println(user.getId());
    }

    @RequestMapping("/number")
    public void testNumberFormatException() {
        System.out.println("number");
        //这里就会发生NumberFormatException，然后就会返回定义在SpringMVC配置文件中的number视图
        Integer.parseInt("lavor");
    }

    @RequestMapping("/default")
    public void testDefaultException() {
        //由于该异常类型在SpringMVC的配置文件中没有指定，所以就会返回默认的异常视图
        throw new RuntimeException("Error!");
    }
}
