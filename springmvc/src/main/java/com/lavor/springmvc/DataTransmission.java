package com.lavor.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * SpringMVC中常见的功能之数据传递
 * 数据从前台传递到后台与数据从后台传递到前台
 * Created by lei.zeng on 2017/7/5.
 */
@Controller
public class DataTransmission {
    /**
     * 前台向后台传递数据，测试url为http://localhost:8080/foreground2background?name=lavor
     * @param name 通过“name”的一致性来传递数据，即前台参数与后台参数同名
     * @param user 将参数传递给实体变量，需要无参数的构造方法与传递参数对应的Setter方法
     * @param param 通过@RequestParam注解，将前台指定变量传递给后台变量，默认与后台变量同名
     * @param httpServletRequest 通过httpServletRequest直接获取获取请求的参数（前台传递的参数）
     */
    @RequestMapping("/foreground2background")
    public String foreground2background(String name, User user, @RequestParam("name")String param, HttpServletRequest httpServletRequest){
        System.out.println(name);
        System.out.println(user.getName());
        System.out.println(param);
        System.out.println(httpServletRequest.getParameter("name"));
        return "springmvc/index.jsp";
    }

    /**
     * 后台向前台传递数据时，记得开启EL表达式，开启方法是jsp文件的头部信息是这样的：
     * <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>`
     * EL表达式一般默认是开启的，但是为了保险起见还是手动开启EL表达式
     * 后台向前台传递数据：使用ModelAndView向前台传递数据
     * 四种方法给前台传递数据的优先级是：ModelAndView>Map>@ModelAttribute>Session
     * @return
     */
    @RequestMapping("/background2foreground")
    public ModelAndView background2foreground(){
        Map map=new HashMap();
        map.put("name","lavor");
        return new ModelAndView("/getBackgroundData.jsp",map);
    }

    /**
     * 后台向前台传递数据：使用Map及其实现类或者Model及其实现类
     * @param map
     * @return
     */
    @RequestMapping("/background2foreground2")
    public String background2foreground2(Map map){
        map.put("name","lavor");
        return "/getBackgroundData.jsp";
    }
    @ModelAttribute("name")
    public String getName(){
        return "lavor";
    }
    /**
     * 后台向前台传递数据：使用@ModelAttribute
     * 在使用@ModelAttribute("name")前，必须要有 @ModelAttribute("name")注解的方法
     * 如果我们不需要对该属性做修改的话，那么这里也可以不添加@ModelAttribute("name")注解的参数
     * @param name
     * @return
     */
    @RequestMapping("/background2foreground3")
    public String background2foreground3(@ModelAttribute("name") String name){
        return "/getBackgroundData.jsp";
    }

    /**
     * 后台向前台传递数据：使用Session
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/background2foreground4")
    public String background2foreground4(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().setAttribute("name","lavor");
        return "/getBackgroundData.jsp";
    }
}
