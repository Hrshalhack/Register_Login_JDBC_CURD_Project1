package com.techm.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Delete_User_Action extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
	System.out.println("I am doPost in Delete_User_Action.java");
	System.out.println("hello boy ,program runs in Delete_User_Action.java");
    
    HttpSession session = request.getSession();
    String username = (String) session.getAttribute("username");
    
    try {
    	
		//step1 : load the driver 
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//step2 : creating a connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db","root","Harshal12345");//connection bn gya hai , ab ap is connection ko use kr skte ho.

		//we check connection is created or not
		if(con.isClosed())
		{
			System.out.println("connection is Closed");
		}
		else {
			System.out.println("Connection created.....");
		}
		
		
		
		//create a query to update the data in table
		String q= "DELETE FROM table2 WHERE username = ?";
       
		//get the object of PreparedStatement
		PreparedStatement pstmt = con.prepareStatement(q);
		
		//set the values to query
		
		pstmt.setString(1,username);
		
		
		pstmt.executeUpdate();//we not pass the query q here, because we already passed it when getting the obj
		
		System.out.println("Hello boy, data inserted.....");
		
		con.close();//connection closed

		
		response.sendRedirect("data_deleted_success.html");
		
		
		
		
		
    }catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//we use cj here 
	
    
    
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("I am doGet in Update_Password_Action.java");
}

}
