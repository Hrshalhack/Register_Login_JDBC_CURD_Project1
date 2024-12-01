package com.techm.servlets;

import java.io.IOException;
import java.sql.*;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


 
//@WebServlet("/Login")
public class Login_Action extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		System.out.println("I am doPost in Login_Action.java");
		System.out.println("hello boy ,program runs in Login_Action.java");
		
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Store the username in session and send to Update_Password_Action.java
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
       
		
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
			
			
			//create a query to insert the data in table
			String q= "SELECT * FROM table2 WHERE username = ? AND password = ?";
			
			//get the object of PreparedStatement
			PreparedStatement pstmt = con.prepareStatement(q);
			
			//set the values to query
			
			pstmt.setString(1,username); //save the data in table
			pstmt.setString(2,password);
			
			
			
			ResultSet resultSet = pstmt.executeQuery();//we not pass the query q here, because we already passed it when getting the obj
		
			if (resultSet.next()) {
                // If the user is found
				response.sendRedirect("Login_success_msg.html");
				con.close();//connection closed	
				
		        
            } else {
                // If the user is not found
            	response.sendRedirect("User_not_found.html");
            	con.close();//connection closed
            }
        
			
			

			
			
			
			
        }catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//we use cj here 
		
        
        
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("I am doGet in Login_Action.java");
    }
	
}