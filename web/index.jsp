<%@ page import="java.util.Map" %>
<%@ page import="Request.Receive" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="Request.Method" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  //获取提交的数据
  Map<String , String[]> data = request.getParameterMap();
  //获取协议头
  Enumeration headerEnu = request.getHeaderNames();
  Map<String , String> header = new HashMap<>();
  while(headerEnu.hasMoreElements()){
    //如果有协议头
    String key = (String) headerEnu.nextElement();
    String value = request.getHeader(key);
    header.put(key,value);
  }
  Method method = null;
  if (request.getMethod().equalsIgnoreCase("get")){
    method = Method.GET;
  }else if (request.getMethod().equalsIgnoreCase("post")){
    method = Method.POST;
  }else{
    method = Method.OTHER;
  }
  String ip = null;
  if ((ip = request.getRemoteHost()) == null){
    ip = request.getRemoteAddr();
  }
  Receive receive = new Receive(data , header , method , ip);//传入数据到javaBean处理
  out.print(receive.Retracement());
  receive.close();//关闭资源占用
%>

