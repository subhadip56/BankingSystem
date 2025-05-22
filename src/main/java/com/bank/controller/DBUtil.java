package com.bank.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL =
			  "jdbc:mysql://localhost:3306/bank_db"
			+ "?useSSL=false"
			+ "&useLegacyDatetimeCode=false"
			+ "&serverTimezone=Asia/Kolkata";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "12345678";

	public static Connection getConnection() {
		Connection con = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Database Connection Successful!");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("JDBC Driver not found!");
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			System.out.println("Database connection failed!");
			e.printStackTrace();
		}
		return con;
	}
}
