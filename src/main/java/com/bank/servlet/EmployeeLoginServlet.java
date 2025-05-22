package com.bank.servlet;
import jakarta.servlet.ServletException;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import com.bank.controller.DBUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;

public class EmployeeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try (Connection conn = DBUtil.getConnection()) {
			String sql = "SELECT * FROM employee WHERE username = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("employeeUsername", username);
				response.sendRedirect("JSP/employee_dashboard.jsp");
			} else {
				request.setAttribute("errorMessage", "Invalid username or password!");
				RequestDispatcher rd = request.getRequestDispatcher("JSP/employeelogin.jsp");
				rd.forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
		}
	}
}
