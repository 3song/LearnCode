package com.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
// @EnableSwagger2 表示开启Swagger2
public class SwaggerConfig {
    @Bean
    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                //api扫包
        .apis(RequestHandlerSelectors.basePackage("com.api")).paths(PathSelectors.any()).build();
    }
    //创建Api文档信息
    private ApiInfo apiInfo() {
        //title 文档标题  description 文档描述
        return new ApiInfoBuilder().title("SpringBoot-Swagger项目").description("SpringCloudLearn").termsOfServiceUrl("www.3song.com").version("1.0").build();
    }
}
