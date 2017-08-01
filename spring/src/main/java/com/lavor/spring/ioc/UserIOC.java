package com.lavor.spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by shitian on 2017-06-26.
 */
public class UserIOC {
    /**
     * 根据类型自动装配bean，只有UserIOC的baen才可以自动装配
     * 通过new创建的UserIOC实例不能进行自动装配
     */
    @Autowired
    public User user;

    /**
     * 基于XML文件的bean的装配
     */
    public static User xmlIOC(){
        //根据classpath下面的spring的xml配置文件来获取应用程序上下文
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ioc/spring-ioc.xml");
        //根据应用程序上下文，来获取其中的bean
        User user=context.getBean("user",User.class);
        return user;
    }

    /**
     *基于JavaConfig的bean的装配
     * 这里也可以使用ClassPathXmlApplicationContext读取xml中的配置文件,
     * 前提是配置文件配置了context:component-scan来扫描JavaConfig的类
     */
    public static User javaConfigIOC(){
        //根据JavaConfig（@Configuration注解类）来获取应用程序上下文
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(UserJavaConfig.class);
        //根据应用程序上下文，来获取@Configuration注解类下面的@Bean注解方法的返回值
        User user=context.getBean("user2",User.class);
        return user;
    }

    /**
     *自动装配bean
     * Spring的xml配置文件需要添加以下代码：
     * <context:annotation-config></context:annotation-config>
     */
    public static User autoIOC(){
        //根据classpath下面的spring的xml配置文件来获取应用程序上下文
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ioc/spring-ioc.xml");
        //根据应用程序上下文，来获取其中的bean
        UserIOC userIOC=context.getBean("userIOC",UserIOC.class);
        //因为UserIOC中的字段user被@Autowired标记注解，所以上下文环境中的User这个bean会被自动装配到user字段上
        User user=userIOC.getUser();
        return user;
    }
    /**
     * @Repository，@Service，@Controller，@Component四个注解都可以扫描装配bean
     *  Spring的xml配置文件需要添加以下代码：
     *  <context:component-scan base-package="com.lavor"/>
     */
    public static UserScan scanIOC(){
        //根据classpath下面的spring的xml配置文件来获取应用程序上下文
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ioc/spring-ioc.xml");
        //根据应用程序上下文，来获取其中的bean
        UserScan userScan=context.getBean("userScan",UserScan.class);
        return userScan;
    }
    public static void main(String[] args){
//        System.out.println(scanIOC().getName());
//        System.out.println(xmlIOC().getName());
        System.out.println(javaConfigIOC().getName());
        System.out.println(autoIOC().getName());

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
