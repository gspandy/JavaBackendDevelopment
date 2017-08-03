package com.lavor.springboot.deployment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用devtools进行热部署的工程，官方推荐热部署方法
 * 还可以通过Spring Loaded和JRebel实现热部署
 */
@SpringBootApplication
public class SpringbootDeploymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDeploymentApplication.class, args);
	}
}
