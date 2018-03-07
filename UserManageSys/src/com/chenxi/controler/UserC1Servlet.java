package com.chenxi.controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chenxi.model1.*;
//这个控制器将处理用户的分页显示，添加
public class UserC1Servlet extends HttpServlet {

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

		//得到用户希望显示的pageNow
		String stringPageNow = request.getParameter("pageNow");
		try{
			int pageNow = Integer.parseInt(stringPageNow);
			//调用userbeanC1
			UserBeanC1 ubc = new UserBeanC1();
			ArrayList<UserBean> al = ubc.getUserByPage(pageNow);
			int pageCount = ubc.getPageCount();
			//将al,pageCount放入request中
			request.setAttribute("result",al);
			request.setAttribute("pageNow",pageNow+"");
			request.setAttribute("pageCount",pageCount+"");
			
			//重新跳转回wel.jsp
			request.getRequestDispatcher("wel.jsp").forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
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

		this.doGet(request, response);
	}

}
