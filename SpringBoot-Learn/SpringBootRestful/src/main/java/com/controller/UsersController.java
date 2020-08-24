package com.controller;

import com.entity.Users;
import com.restful.ResultEntity;
import com.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//因为和类名相同
@RestController
public class UsersController {
    @Resource
    private UserService userService;

    @RequestMapping("/users/insert")
    public ResultEntity userInsert(Model model, String name, Integer age){
        if (name!=null&&age!=null) {
            Users users = new Users();
            users.setAge(age);
            users.setName(name);
            if (userService.userInsert(users)==1){
                //使用RestEntity处理
                //return new ResultEntity(0,"保存成功").putNest("name",users.getName()).putNest("age",users.getAge());
                return new ResultEntity(0,"保存成功").put("users", users);
            }else{
                return new ResultEntity(1,"数据错误");
            }

        } else {
            return new ResultEntity(404,"对象为空");
        }
    }
}
