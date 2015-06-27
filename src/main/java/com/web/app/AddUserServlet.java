package com.web.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class AddUserServlet extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6528379298087975198L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html"); 
		String name = request.getParameter("name");
		
		try{
			if(name!=null){
				Context ctx = new InitialContext();
				Context envContext = (Context) ctx.lookup("java:comp/env");
				DataSource ds = (DataSource) envContext.lookup("jdbc/project1");
				Connection conn = null;
				try {
				     conn = ds.getConnection();
				     PreparedStatement ps = conn.prepareStatement("INSERT INTO USERS (NAME) VALUES (?)");
				     ps.setString(1,name);
				     ps.executeUpdate();
				     response.getWriter().write("added user "+name);
				     response.getWriter().write("\n");
				} finally {
				     if (conn!=null) { conn.close(); }
				}
			}
			response.getWriter().write("<H3><a href=\""+request.getContextPath()+"/\">Main Page</a></H3>");
		
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().write("There is a problem on server");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
	}
	
	
	

	
	
	
}
