package com.web.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			response.setContentType("text/html"); 
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/project1");
			Connection conn = null;
			try {
			     conn = ds.getConnection();
			     PreparedStatement ps = conn.prepareStatement("SELECT * FROM USERS");
			     ps.execute();
			     ResultSet rs = ps.getResultSet();
			     if(rs!=null){
			    	 response.getWriter().write("Users:");
			    	 response.getWriter().write("<BR>");
			    	 while (rs.next()) {
			             String name = rs.getString(2);
			             response.getWriter().write(name);
			             response.getWriter().write("<BR>");
			             
			    	 }
			     }
			} finally {
			     if (conn!=null) { conn.close(); }
			}
			response.getWriter().write("<H3><a href=\""+request.getContextPath()+"/\">Main Page</a></H3>");
			response.flushBuffer();
			
		}catch(Exception e){
			e.printStackTrace();
			 response.getWriter().write("There is a problem on server");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
