package com.lavor.springmvc;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 控制器的使用：使用@Controller实现控制器，一个控制器只能处理多个请求
 * 一般采用此种方式来实现控制器
 * 注意：Controller中/表示项目根目录，页面中/表示服务器根目录
 * Created by shitian on 2017/7/5.
 */
@Controller
public class AnnotationController {
    /**
     * @return 通过返回页面对应的字符串来返回视图页面
     * 返回视图的url地址是以请求url为基础的：
     * 比如请求缺省url（没有ip与端口前缀）为，/hello/hello，返回为index.jsp
     * 那么实际返回的缺省url为/hello/index.jsp
     * 返回视图，以/开头，表示根目录，../表示上级目录，没有以两者开头表示当前目录
     * @RequestMapping注解还可以用在类上，表示类下的方法的请求都要加上类上的请求前缀
     */
    @RequestMapping("/annotationController")
    public String usage(){
        return "/index.jsp";
    }

    /**
     * @return 通过返回页面对应的ModelAndView来返回视图页面
     */
    @RequestMapping("/annotationController2")
    public ModelAndView usage2(){
        return new ModelAndView("/index.jsp");
    }

    @RequestMapping("/converterAndFormatter")
    public ModelAndView converter(Date date){
        System.out.println(date.getTime());
        return new ModelAndView("/index.jsp");
    }

    /**
     * 还有一种方法进行类型转换，就是在@InitBinder注解的方法中注册对应的Editor
     * 这样就会在请求处理前自动进行类型转换了
     * @param binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
    }

    /**
     * 利用Spring自带框架实现的输入验证
     * 可以简化为下面initBinder2与deal2两个方法实现的形式
     * @param user
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/validator")
    public ModelAndView deal(@ModelAttribute("user") User user, BindingResult bindingResult) {
        SpringUserValidator validator = new SpringUserValidator();
        //验证前台的user输入，bindingResult是用来绑定验证结果的，方面返回给前台
        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println("Error!!!");
            return new ModelAndView("/validatorPage.jsp");
        }
        return new ModelAndView("/index.jsp");
    }

    /** 我们知道在Controller类中通过@InitBinder标记的方法只有在请求当前Controller的时候才会被执行,
     * 所以在这里注册校验器，这样Controller处理请求中@Validated标注的对象时，
     * 就会使用这里替换或者添加的验证器进行验证
     */
//    @InitBinder
//    public void initBinder2(DataBinder binder){
//        //此种方法的Spring验证与JSR的验证不能同时在一个Controller中使用，因为两者可能混淆彼此可以验证的对象
//        binder.addValidators(new SpringUserValidator());
//    }

    /**
     * @Validated注解的对象是需要被验证的对象，前提是有对其进行验证的验证器
     */
    @RequestMapping(value = "/validator2", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView deal2(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Error!!!");
            return new ModelAndView("/validatorPage.jsp");
        }
        return new ModelAndView("/index.jsp");
    }

    @ModelAttribute("user")
    public User getUser() {
        User user = new User();
        user.setId(1);
        user.setName("lavor");
        return user;
    }

    /**
     * 测试验证器时，前台测试验证的jsp页面（/validatorPage.jsp）不能直接访问，
     * 需要从另一个请求过渡到它，并且传递“user”这个bean给前台。
     * testValidator方法处理的/testValidator请求就可以充当这个过渡求情
     */
    @RequestMapping(value = "/testValidator", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView testValidator(@ModelAttribute("user") User user) {
        return new ModelAndView("/validatorPage.jsp");
    }

    @ModelAttribute("jsr")
    public JSR getJSR() {
        JSR jsr = new JSR();
        return jsr;
    }

    @RequestMapping(value = "/testHibernateValidator", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView testHibernateValidator(@ModelAttribute("jsr") JSR jsr) {
        System.out.println("Error!!!");
        return new ModelAndView("/JSRValidatorPage.jsp");
    }

    /**
     *
     * @param jsr 需要验证输入的对象用@Valid注解标志
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/hibernateValidator", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView hibernateValidator(@ModelAttribute("jsr") @Valid JSR jsr, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/JSRValidatorPage.jsp");
        }
        return new ModelAndView("/index.jsp");
    }

    @RequestMapping("/error2")
    public String error(){
        int i = 5/0;//这里会出现异常，会自动调用异常处理方法@ExceptionHandler注解的异常处理方法

        return "error.jsp";
    }

    /**
     * 识别请求表头的Accept-language语言区域来进行国际化，这是常规做法
     * 还有一种做法是切换语言区域（一般用户阅读文档），配置LocaleChangeInterceptor的Bean监听某个参数的变化来切换语言区域
     * @param request
     * @return
     */
    @RequestMapping("/i18n")
    public ModelAndView i18n(HttpServletRequest request){
        System.out.println("i18n");
        ModelAndView modelAndView=new ModelAndView("/i18n.jsp");
        //从后台代码获取国际化信息
        RequestContext requestContext = new RequestContext(request);
        System.out.println( requestContext.getMessage("language"));
        modelAndView.addObject("language", requestContext.getMessage("language"));
        return modelAndView;
    }
}
