package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class AppZuul {
    // 调用地址 ： http://192.168.50.73/api-member/ userToken is null!!!
    // 调用地址 ： http://192.168.50.73/api-order/ userToken is null!!!
    // 由于在filter 加了 过滤参数验证userToken  所以需加上userToken参数
    // 调用地址 ： http://192.168.50.73/api-member?userToken=1
    // 调用地址 ： http://192.168.50.73/api-order?userToken=1
    public static void main(String[] args) {
        SpringApplication.run(AppZuul.class, args);
    }


    //对zuul配置使用SpringCloudConfig 实现实时更新
    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }

    //添加文档来源
    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider {

        /**
         * Retrieves an instance of the appropriate type. The returned object may or may not be a new
         * instance, depending on the implementation.
         *
         * @return an instance of the appropriate type
         */
        @Override
        public List<SwaggerResource> get() {
            List resources=new ArrayList();
            resources.add(swaggerResource("com.member","/api-member/v1/api-docs","2.0"));
            resources.add(swaggerResource("com.member","/api-member/v1/api-docs","2.0"));
            return resources;
        }

        private SwaggerResource swaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource=new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }
    }

}
