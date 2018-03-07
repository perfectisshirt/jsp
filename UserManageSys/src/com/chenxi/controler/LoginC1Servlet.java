package com.chenxi.controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chenxi.model1.*;

//这是一个控制器，主要完成对用户身份的验证
//控制器本身是不会去完成业务逻辑的，它主要是去调用model完成任务
public class LoginC1Servlet extends HttpServlet {

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//得到用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//使用模型完成对用户的验证
		//1.创建一个userbean处理对象
		UserBeanC1 ubc = new UserBeanC1();
		//2.调用方法
		if (ubc.checkUser(username, password)) {
			//合法
			//因为sendRedirect方法效率不高，所以一般使用转发,效率高，同时request中的对象还可以在下一页面使用
			
			
			//在跳转到wel.jsp页面的时候就把数据准备好
			ArrayList<UserBean> al = ubc.getUserByPage(1);
			int pageCount = ubc.getPageCount();
			//将al,pageCount放入request中
			request.setAttribute("result",al);
			request.setAttribute("pageNow","1");
			request.setAttribute("pageCount",pageCount+"");
			
			request.getRequestDispatcher("wel.jsp").forward(request, response);
		} else {
			//不合法
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//合二为一
		this.doGet(request, response);
	}

}
