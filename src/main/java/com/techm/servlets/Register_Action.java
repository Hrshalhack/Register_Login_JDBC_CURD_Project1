package com.techm.servlets;

import java.io.IOException;
import java.sql.*;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


 


//@WebServlet("/Register")

public class Register_Action extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		System.out.println("I am doPost in Register_Action.java");
		System.out.println("hello boy ,program runs in Register_Action.java");
		
		String username = request.getParameter("username");
        String password = request.getParameter("password");
		
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
			

            // Check if the username already exists
			String checkQuery = "SELECT * FROM table2 WHERE username = ?";
			PreparedStatement checkStmt = con.prepareStatement(checkQuery);
			checkStmt.setString(1, username);
			
             ResultSet resultSet = checkStmt.executeQuery();
             if (resultSet.next()) {
                 // Username already exists
            	 response.sendRedirect("User_already_exist.html");
                 
             } else {
                 // Insert the new username and password
                

			//create a query to insert the data in table
			String q= "insert into table2(username,password) Values (?,?)";
			
			//get the object of PreparedStatement
			PreparedStatement pstmt = con.prepareStatement(q);
			
			//set the values to query
			
			pstmt.setString(1,username); //save the data in table
			pstmt.setString(2,password);
			
			
			pstmt.executeUpdate();//we not pass the query q here, because we already passed it when getting the obj
			
			System.out.println("Hello boy, data inserted.....");
			
			con.close();//connection closed

			

			response.sendRedirect("register_success_msg.html");
             }

			
			
        }catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//we use cj here 
		
        
        
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("I am doGet in Register_Action.java");
    }
	
}
