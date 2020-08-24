package com.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebAppConfig {
	@Resource
	private AccessTokenInterceptor accessTokenInterceptor;
	//定义拦截器  配置拦截那些接口地址 拦截 openApi请求 也就是拦截 MemberController 内的请求
//	@RequestMapping("/openApi")
//	public class MemberController extends BaseApiService {
	@Bean
	public WebMvcConfigurer WebMvcConfigurer() {
		return new WebMvcConfigurer() {
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(accessTokenInterceptor).addPathPatterns("/openApi/*");
			};
		};
	}

}
