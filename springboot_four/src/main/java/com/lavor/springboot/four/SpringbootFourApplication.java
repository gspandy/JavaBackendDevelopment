package com.lavor.springboot.four;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 过滤器，监听器，Servlet的配置使用，相当于在web.xml中配置了它们
 * 三者的加载顺序listener -> filter -> servlet
 * 三者中只有Servlet需要用注解（@ServletComponentScan）扫描才可以使用
 * 拦截器的配置使用，相当于SpringMVC中在xml中配置了拦截器
 */
@ServletComponentScan
@SpringBootApplication
public class SpringbootFourApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFourApplication.class, args);
	}
}
