package com.lavor.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据绑定是将用户输入绑定到领域模型的一种特性。
 * 有了数据绑定，类型总是为String的HTTP请求参数，可以用于填充不同类型的对象属性。
 * Spring默认的属性编辑器PropertyEditorRegistrySupport，可以将String类型数据自动转换成一些常用的类型数据
 * Created by lei.zeng on 2017/7/6.
 */
@Controller
public class DataBinding {
    /**
     * 基本类型的数据绑定，比如int
     * 自动将请求中的String类型数据转换成int类型数据
     */
    @RequestMapping("/bind/basicType")
    public String bind(int count){
        System.out.println(count);
        return "/springmvc/index.jsp";
    }
    /**
     * 包装类型的数据绑定，比如Integer
     * 自动将请求中的String类型数据转换成Integer类型数据
     */
    @RequestMapping("/bind/packageType")
    public String bind(Integer number){
        System.out.println(number);
        return "/springmvc/index.jsp";
    }
    /**
     * 简单对象类型的数据绑定
     */
    @RequestMapping("/bind/simpleObject")
    public String bind(User user){
        System.out.println(user.getName());
        return "/springmvc/index.jsp";
    }

    /**
     * 复杂对象类型的数据绑定
     */
    @RequestMapping("/bind/complexObject")
    public String bind(Car car){
        System.out.println(car.getNumber());
        return "/springmvc/index.jsp";
    }
    /**
     * 数组对象类型的数据绑定
     */
    @RequestMapping("/bind/arrayObject")
    public String bind(String[] strings){
        System.out.println(strings[0]);
        System.out.println(strings[1]);
        return "/springmvc/index.jsp";
    }
    /**
     * 集合对象类型的数据绑定，比如List
     * 集合类型需要绑定在对象上，而不能直接写在Controller方法的参数中
     */
    @RequestMapping("/bind/setObject")
    public String bind(StringList stringList){
        System.out.println(stringList.getList().get(0));
        System.out.println(stringList.getList().get(1));
        return "/springmvc/index.jsp";
    }
}
