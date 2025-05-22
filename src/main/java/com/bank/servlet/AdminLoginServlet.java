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

public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		try (Connection conn = DBUtil.getConnection()) {
			String sql = "SELECT * FROM admin WHERE username = ? AND password_hash = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password); // assuming plain text match

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				HttpSession session = req.getSession();
				session.setAttribute("adminUsername", username);
				resp.sendRedirect("JSP/admin_dashboard.jsp");
			} else {
				req.setAttribute("errorMessage", "Invalid username or password!");
				RequestDispatcher rd = req.getRequestDispatcher("JSP/adminlogin.jsp");
				rd.forward(req, resp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
		}
	}
}
