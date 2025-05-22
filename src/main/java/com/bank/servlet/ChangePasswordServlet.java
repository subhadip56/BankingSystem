package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

//@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Show form
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ensure user is logged in
		String custId = (String) req.getSession().getAttribute("customerId");
		if (custId == null) {
			resp.sendRedirect(req.getContextPath() + "JSP/Customer_login.jsp");
			return;
		}
		req.getRequestDispatcher("JSP/ChangePassword.jsp").forward(req, resp);
	}

	// Handle submission
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String custId = (String) session.getAttribute("customerId");
		if (custId == null) {
			resp.sendRedirect(req.getContextPath() + "JSP/Customer_login.jsp");
			return;
		}

		String current = req.getParameter("currentPassword");
		String next = req.getParameter("newPassword");
		String confirm = req.getParameter("confirmPassword");

		// 1) basic check
		if (!next.equals(confirm)) {
			req.setAttribute("error", "New password and confirmation do not match.");
			doGet(req, resp);
			return;
		}

		// 2) fetch existing password from DB
		String sqlFetch = "SELECT password FROM customer WHERE customer_id = ?";
		String storedPwd = null;
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sqlFetch)) {
			ps.setString(1, custId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					storedPwd = rs.getString("password");
				}
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		if (storedPwd == null || !storedPwd.equals(current)) {
			req.setAttribute("error", "Current password is incorrect.");
			doGet(req, resp);
			return;
		}

		// 3) update to new password
		String sqlUpdate = "UPDATE customer SET password = ? WHERE customer_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
			ps.setString(1, next);
			ps.setString(2, custId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		req.setAttribute("message", "Your password has been updated.");
		doGet(req, resp);
	}
}
