package com.lavor.springboot.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 该类与启动类放在同一级目录中
 * @Configuration注解，让Spring来加载该类配置。
 * 再通过@EnableSwagger2注解来启用Swagger2
 * Created by lei.zeng on 2017/8/3.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    /**
     * 创建Docket的Bean
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lavor.springboot.rest"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该Api的基本信息（这些基本信息会展现在文档页面中）
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot中使用Swagger2构建RESTful APIs")
                .description("更多SpringBoot相关文章请关注：http://www.jianshu.com/c/aa1a5ba0e677")
                .termsOfServiceUrl("http://www.jianshu.com/c/aa1a5ba0e677")
                .contact("lavor")
                .version("1.0")
                .build();
    }
}
