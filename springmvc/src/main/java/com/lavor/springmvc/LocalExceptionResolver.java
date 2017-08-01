package com.lavor.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 局部异常处理
 * 局部异常使用@ExceptionHandler注解来进行处理，处理当前Controller中的异常
 * Created by lei.zeng on 2017/7/5.
 */
@Controller
public class LocalExceptionResolver {
    /**
     * 定义局部异常处理方法
     * 注意：此时返回的url相当于以/error/为基础
     * 而不是以/error为基础（这里和别的地方好像有点不一样，不知道为什么）
     *  @ExceptionHandler注解的方法中出现异常会连着调用自身三次进行处理
     *  最后还处理不了就会交给全局异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){
        ModelAndView modelAndView = new ModelAndView("/error/local.jsp");
        System.out.println("出现局部异常");
        return modelAndView;
    }
    @RequestMapping("/error")
    public String error(){
        int i = 5/0;//这里会出现异常，会自动调用异常处理方法@ExceptionHandler注解的异常处理方法
        return "/error.jsp";
    }
}
