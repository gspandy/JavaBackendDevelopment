package com.lavor.springmvc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * json与ajax的使用，前台利用ajax向后台传递json数据，前台利用ajax获取后台的json数据
 * Created by lei.zeng on 2017/7/18.
 */
@Controller
public class JsonAndAjaxController {
    /**
     * 从前台获取json字符串，自动转换成List<User>数据
     * 这里用List可以接受json与json数组，也可以直接用JavaBean或者Map接受json数据
     * 如果报400或者415错误，一般是json字符串数据不能自动转换成@RequestBody注解的参数
     *  @ResponseBody注解可以返回任何类型的数据，而不限于必须包含视图信息的类型数据
     */
    @RequestMapping("/getJson")
    @ResponseBody
    public String getJson(@RequestBody List<User> list){
        System.out.println(list.toString());
        return "success";
    }

    /**
     * 给前台传递json数据
     * 这里用返回的List被封装成json或者json数组，也可以直接用JavaBean或者Map，它们会被封装成json数据
     */
    @RequestMapping("/setJson")
    @ResponseBody
    public List<User> setJson(){
        List<User> list=new ArrayList<User>();
        User user1=new User();
        user1.setId(1100);
        user1.setName("name1100");
        list.add(user1);
        User user2=new User();
        user2.setId(1100);
        user2.setName("name1100");
        list.add(user2);
        return list;
    }
}
