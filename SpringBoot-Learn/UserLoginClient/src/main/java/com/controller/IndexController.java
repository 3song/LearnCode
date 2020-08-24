package com.controller;

import com.entity.Users;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class IndexController {
    //@Resource
    //private UsersService usersService;
    private static Logger logger=Logger.getLogger("info");

    @RequestMapping("/login")
    public String login(Model model){
        //List<Users> usersList = usersService.findAll();
        //int userCount=usersList.size();
        //JSONObject jsonObject=new JSONObject();
        //jsonObject.put("userCount",userCount);
        //model.addAttribute("userCount",userCount);
        return "login";
    }
    //@RequestMapping(value = "/userLogin",method = RequestMethod.POST)

    @ResponseBody
    @RequestMapping("/userLogin")
    public String userLogin(@RequestBody JSONObject data) throws IOException {
        //model.getAttribute();
        //Object name = model.getAttribute("name").toString();
        //Users users = (Users) model.getAttribute("users");
        //JSONObject jsonObject=new JSONObject();
        //System.out.println(model.getAttribute("users").toString());
        //logger.info("进入后台,Users的值为："+users.getAge());
        return "success";
    }
    @ResponseBody
    @RequestMapping("/userLogin")
    public String userLogin(Users users) throws IOException {
        //model.getAttribute();
        //Object name = model.getAttribute("name").toString();
        //Users users = (Users) model.getAttribute("users");
        //JSONObject jsonObject=new JSONObject();
        //System.out.println(model.getAttribute("users").toString());
        //logger.info("进入后台,Users的值为："+users.getAge());
        return "success";
    }
}
