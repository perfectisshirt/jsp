package com.chenxi.model1;
import java.sql.*;
import java.util.*;
//这是一个处理类，有些人喜欢叫BO:business object，主要是封装对users表的各种操作
public class UserBeanC1 {

	private Statement sm = null;
	private ResultSet rs = null;
	private Connection ct = null;
	private int pageCount = 0;
	private int pageSize = 3;
	private int rowCount = 0;
	
	//返回分页的总页数
	public int getPageCount() {
		 try{
			 ct = new ConnDB().getConn();
			 sm = ct.createStatement();
			 rs = sm.executeQuery("select count(*) from users");
		        
		        if (rs.next()) {
		        	rowCount = rs.getInt(1);
		        }    	
		        
		        if (rowCount % pageSize == 0) {
		            pageCount = rowCount / pageSize;
		        } else {
		        	pageCount = rowCount / pageSize + 1;
		        }
			 
		 } catch(Exception e) {
			 e.printStackTrace();
		 } finally {
			 this.close();
		 }
		return pageCount;
	}
	
	//得到用户需要显示的信息
	public ArrayList<UserBean> getUserByPage (int pageNow) {
		ArrayList<UserBean> al = new ArrayList<>();
		
		try {
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
	        
	        
	        //查询出需要显示的记录
	        rs = sm.executeQuery("select * from users order by userId limit "+pageSize *(pageNow-1)+","+pageSize*pageNow+"");
	        
	        //开始将rs封装到ArrayList中
	        while (rs.next()) {
	        	UserBean ub = new UserBean();
	        	ub.setUserId(rs.getInt(1));
	        	ub.setUsername(rs.getString(2));
	        	ub.setPassword(rs.getString(3));
	        	ub.setEmail(rs.getString(4));
	        	ub.setGrade(rs.getInt(5));
	        	
	        	//将ub放入到al中
	        	al.add(ub);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return al;
	}
	
	//关闭资源
	public void close() {
		try {
			 if (rs != null) {
				 rs.close();
				 //加速资源的释放
				 rs = null;
			 }
			 if (sm!=null) {
				 sm.close();
				 sm = null;
			 }
			 if (ct!=null) {
				 ct.close();
				 ct = null;
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	}
	
	//验证用户是否存在
	public boolean checkUser(String username, String password) {
		 boolean b = false;
		 try {
			 ct = new ConnDB().getConn();
			 
			//create statement
			  sm = ct.createStatement();
			   //search database
			    rs = sm.executeQuery("select password from users where username='"+username+"'");
			   //judge 
			   if (rs.next()) {
			    	//说明用户名存在
			    	if (rs.getString(1).equals(password)) {
			    		//合法
			    		b = true;
			    	}
			   } 
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 //关闭打开的各种资源
			 this.close();
		 }
		 return b;
	}
	
}
