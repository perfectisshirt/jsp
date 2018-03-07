package com.chenxi.model1;
import java.util.*;
import java.sql.*;

//得到数据库的链接
public class ConnDB {

	private Connection ct = null;
	public Connection getConn() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/jspuser","root","woailiuzuqi1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ct;
	}
}
