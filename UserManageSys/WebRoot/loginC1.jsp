<%@ page language="java" import="java.util.*,java.sql.*,com.chenxi.model1.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'loginC1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <%
       //get username and password from login.jsp
	   String username = request.getParameter("username");
	   String password = request.getParameter("password");
	   
	   
	   //调用userbeanC1的方法，完成对用户的验证
	   UserBeanC1 ubc = new UserBeanC1();
	   
	   if (ubc.checkUser(username,password)) {
	   		response.sendRedirect("wel.jsp?user="+username);
	   } else {
	   		response.sendRedirect("login.jsp");
	   }
    %>
    
  </body>
</html>
