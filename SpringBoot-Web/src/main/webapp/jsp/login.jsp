<%--
  Created by IntelliJ IDEA.
  User: 10482
  Date: 2020/5/26
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/js/jquery/jquery-3.4.1.min.js"></script>
<script>
    function submit() {
        console.log("text");
        $.ajax({
            //url:"http://localhost:8080/login",    //请求的url地址
            url:"http://localhost:8080/loginJsonP",    //使用jsonp请求的url地址
            //contentType: 'application/json',
            data:{"name":$("#name").val(),"password":$("#password").val()},
            //使用jsonp 解决跨域问题
            dataType:"jsonp",
            jsonp:"jsonPCallback",
            //data : JSON.stringify(),
            type:"POST",//请求方式
            beforeSend:function(){
                //请求前的处理
                console.log("请求前")
            },
            success:function(isResult){
                //请求成功时处理
                console.log(isResult.msg)
                if (isResult.msg=="true"){
                    location.href="../jsp/index.jsp";
                }else{
                    location.href="../jsp/error.jsp";
                }
            },
            complete:function(){
                //请求完成的处理
            },
            error:function(){
                //请求出错处理
            }
        });
    }
</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<img src="http://192.168.50.73:8080/img/test.jpg">
<table>
    <tr>
        <td>用户名：</td><td><input type="text" id="name" value=""></td>
    </tr>
    <tr>
        <td>密码：</td><td><input type="text" id="password" value=""></td>
    </tr>
    <tr>
        <td><button type="button" value="提交" onclick="submit()">提交信息</button></td><td></td>
    </tr>
</table>
</body>
</html>
