package com.lavor.springboot.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 要访问webapp目录中的文件，包括jsp的访问，一般打包成war包启动或者spring-boot:run启动
 * 其他方式启动可能会出错
 * 我把该项目打成war部署到tomcat7.0上不能访问Controller
 * 部署到比tomcat7.0高的一些版本上就好了
 */
@SpringBootApplication
public class SpringbootJspApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootJspApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJspApplication.class, args);
	}
}
