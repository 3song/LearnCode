<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String root="E:\\IdeaProjects\\SpringBoot-Web\\src\\main\\resources\\static\\img\\eee.jpg";
    File file=new File(root);
    file.delete();
%>
sss