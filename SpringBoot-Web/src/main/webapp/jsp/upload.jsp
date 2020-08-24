<%--
  Created by IntelliJ IDEA.
  User: 10482
  Date: 2020/5/27
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="/upl/upload" enctype="multipart/form-data" method="post">上传用户：
<input type="text" name="username"><br/>
上传文件1：<input type="file" name="file1"><br/>
<input type="submit" value="提交">
</form>
</body>
</html>
