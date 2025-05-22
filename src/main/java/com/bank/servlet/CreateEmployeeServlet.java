package com.bank.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import com.bank.controller.DBUtil;

public class CreateEmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password"); 
		String role = request.getParameter("role");

		try (Connection con = DBUtil.getConnection()) {
			String sql = "INSERT INTO employee (full_name, email, username, password, role) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fullName);
			ps.setString(2, email);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, role);

			int result = ps.executeUpdate();

			if (result > 0) {
				response.sendRedirect("JSP/admin_dashboard.jsp?message=Employee Created Successfully");
			} else {
				response.sendRedirect("JSP/CreateEmployee.jsp?error=Failed to create employee");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("JSP/CreateEmployee.jsp?error=Server error occurred");
		}
	}
}