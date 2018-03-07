<%@ page language="java" import="java.util.*,java.sql.*,com.chenxi.model1.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'wel.jsp' starting page</title>
    
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
    Welcome!<%= request.getParameter("user")%>
    <a href="login.jsp">back to login</a>
    <h1>用户信息</h1>
    <hr>
    <%
    	
    	
    	//调用userbeanC1的方法，完成分页显示
    	//UserBeanC1 ubc = new UserBeanC1();
    	//要显示的用户信息从request中取
    	
        ArrayList<UserBean> al = (ArrayList<UserBean>)request.getAttribute("result");
        
        //显示
        %>
        <table>
        <tr><td>userId</td><td>username</td><td>password</td><td>email</td><td>grade</td></tr>
        <%
        	for (int i=0;i<al.size();i++) {
        		//从al中取出userbean
        		UserBean ub = al.get(i);
        		%>
        		
        		 <tr><td><%=ub.getUserId() %></td>
        		 <td><%=ub.getUsername() %></td>
        		 <td><%=ub.getPassword() %></td>
        		 <td><%=ub.getEmail() %></td>
        		 <td><%=ub.getGrade() %></td></tr>
        </table>
        <br>
        		<% 
        	}
         %>
       
        
        <%
        int pageNow = Integer.parseInt((String)request.getAttribute("pageNow"));
        if (pageNow != 1) {
        	out.println("<a href=UserC1Servlet?pageNow="+(pageNow-1)+">上一页</a>");
        }
    
    int pageCount = Integer.parseInt((String)request.getAttribute("pageCount"));
        //show hyperlinks 
        for (int i=1;i<=pageCount;i++) {
        	out.println("<a href=UserC1Servlet?pageNow="+i+">["+i+"]</a>");
        }
        
        if (pageNow != pageCount) {
        	out.println("<a href=UserC1Servlet?pageNow="+(pageNow+1)+">下一页</a>");
        }
        
     %>
  </body>
</html>
