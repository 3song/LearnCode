package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//因为使用了过滤器Filter Filter是Servlet的实现 ，所以要开启注解
@ServletComponentScan
public class AppWeb {
    //http://192.168.50.73:8080/jsp/login.jsp
    //防止XSS 注入 http://192.168.50.73:8080/inWord 输入 <script>location.href="www.baidu.com"</script> 提交
    // login.jsp  密码 输入 123' or '1=1 Sql注入
    // select id,username,password,age from User where username='' and password='123' or '1=1'

    //防盗链技术  浏览器直接使用此链接 http://192.168.50.73:8080/img/test.jpg   经过ImgFilter 判断 跳转到Error图片 Referer字段为null
    // 通过 http://192.168.50.73:8080/jsp/login.jsp 获取图片 referer 字段为 http://192.168.50.73:8080/jsp/login.jsp


    // CSRF (模拟请求) 防止重复请求 新增方法为
    // http://192.168.50.73:8080/insertUser?username=cl&password=qweqwe&age=12

    // 启动Nginx http://www.3song.com/jsp/index.jsp 访问动态资源
    //使用 http://static.3song.com/static/img/IMG_7001.jpg 访问静态资源
    public static void main(String[] args) {
        SpringApplication.run(AppWeb.class, args);
    }
}
