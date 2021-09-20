<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/20
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String text = request.getParameter("code");
  System.out.println("有消息");
  System.out.println(text);
  //out.print(text);
  String xx = "{\"token\":\"asd\"}";
  out.print(xx);


%>

