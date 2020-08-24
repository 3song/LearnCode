package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseApiService;
import com.base.ResponseBase;
import com.entity.Users;
import com.service.UserService;
import com.utils.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class IndexController extends BaseApiService {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public ResponseBase login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("..........登录验证");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        /*
        * Access to XMLHttpRequest at 'http://localhost:8080/login' from origin 'http://192.168.50.73:8080' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
        * */
        // 修改响应头解决跨域问题
        //response.setHeader("Access-Control-Allow-Origin", "*");
        Users users = new Users(name, password);
        Users userByPassword = userService.getUserByPassword(users);
        if (userByPassword == null) {
            return setResultError("false");
        }
        return setResultSuccess("true");
    }

    @RequestMapping("/loginJsonP")
    @ResponseBody
    public ResponseBase loginJsonP(HttpServletRequest request, HttpServletResponse response,String jsonPCallback) throws IOException {
        System.out.println("..........登录验证");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        /*
         * Access to XMLHttpRequest at 'http://localhost:8080/login' from origin 'http://192.168.50.73:8080' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
         * */
        // 修改响应头解决跨域问题
        //response.setHeader("Access-Control-Allow-Origin", "*");
        //使用Jsonp解决跨域问题
        JSONObject jsonObject=new JSONObject();
        PrintWriter writer=response.getWriter();
        writer.println(jsonPCallback+"("+jsonObject.toJSONString()+")");
        writer.flush();
        writer.close();
        Users users = new Users(name, password);
        Users userByPassword = userService.getUserByPassword(users);
        if (userByPassword == null) {
            return setResultError("false");
        }
        return setResultSuccess("true");
    }

    @RequestMapping("/indexPage")
    public String indexPage(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入方法跳转页面");
        return "indexPage";
        //return "redirect:/jsp/indexPage";
    }

    @RequestMapping("/index")
    //@ResponseBody
    public void index(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("重定向页面indexPage");
        response.setStatus(302);
        response.setHeader("location", "indexPage");
        return;
        //return setResultSuccess("true");
    }

    @RequestMapping("/inWord")
    //@ResponseBody
    public String inWord(HttpServletRequest request, HttpServletResponse response) {
        return "inWord";
    }

    @RequestMapping("/showWord")
    //@ResponseBody
    public String showWord(HttpServletRequest request, HttpServletResponse response) {
        String inWord = request.getParameter("inWord");
//        XSSHttpServletRequestWrapper xssHttpServletRequestWrapper=new XSSHttpServletRequestWrapper(request);
//        xssHttpServletRequestWrapper.getParameter("inWord");
        System.out.println(inWord + "1231231231323");
        request.setAttribute("inWord", inWord);
        return "showWord";
        //return setResultSuccess("true");
    }

    @RequestMapping(value = "/insertUser")
    @ResponseBody
    public ResponseBase insertUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        Users users = new Users(username, password, new Integer(age));
        int i = userService.insertUser(users);
        if (i == 0) {
            return setResultError("添加失败");
        }
        return setResultSuccessData(200, users);
    }


    @RequestMapping(value = "getToken")
    public String getToken() {
        return TokenUtils.getToken();
    }

    //add方法验证Token
    @RequestMapping(value = "/insertUserToken", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseBase insertUserToken(HttpServletRequest request) {
        //@RequestBody Users users
        //代码步骤
        // 1.获取令牌并存放在请求头中
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            return setResultError("参数错误!!!");
        }
        // 2.判断令牌是否在缓存中有对应数据
        // 3.如果缓存中没有该令牌，直接报错（请勿重复提交）
        // 4.如果缓存中有该令牌，直接执行当前业务逻辑
        // 5.执行完成业务逻辑之后，直接删除该令牌
        if (!TokenUtils.findToken(token)){
            setResultError("请勿重复提交!!!");
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        Users users = new Users(username, password, new Integer(age));
        int i = userService.insertUser(users);
        if (i == 0) {
            return setResultError("添加失败");
        }
        return setResultSuccessData(200, users);
    }
}




