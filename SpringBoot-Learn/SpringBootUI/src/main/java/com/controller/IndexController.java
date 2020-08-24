package com.controller;

import com.util.URLUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
public class IndexController {
    @RequestMapping("/userLogin")
    public String userLogin(Model model){
        System.out.println("进入");
        return "login";
    }
    @RequestMapping("/users/insert")
    public String insert(String name, Integer age) throws IOException {
        String urlContent = URLUtil.getURLContent("http://127.0.0.1:8080/users/insert?name=" + URLEncoder.encode(name,"UTF-8") + "&age=" + age + "");
        System.out.println("获取到的内容是："+urlContent);
        return "success";
    }
}
