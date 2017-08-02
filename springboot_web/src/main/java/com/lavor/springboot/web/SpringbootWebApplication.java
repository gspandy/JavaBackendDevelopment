package com.lavor.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类应该放在主包下面，
 * 因为@SpringBootApplication会扫描它所注解的类所在包中的很多信息，然后进行自动配置
 */
@SpringBootApplication
public class SpringbootWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebApplication.class, args);
	}
}
