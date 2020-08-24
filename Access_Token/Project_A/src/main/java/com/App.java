package com;

import com.utils.HttpClientUtils;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URLEncoder;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月18日 下午2:21:59<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@MapperScan("com.mapper")
@SpringBootApplication
public class App {
	//java 提供 http协议 特殊字符转码
	//http://127.0.0.1:8080/auth/getAccessToken?appId=73b5d54c09451531897238555&appSecret=821464dc-b10444399f8c 获取token
	// 调用接口  http://127.0.0.1:8080/openApi/getUser?accessToken=dbefce61e1304de1b20ffad66615bafa
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	@Test
	public void sdf(){
		String url="http://127.0.0.1:8080/index?username="+ URLEncoder.encode("1+1");
		HttpClientUtils.httpGet(url);
	}

}
