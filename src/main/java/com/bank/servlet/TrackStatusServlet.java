package com.bank.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bank.controller.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/TrackStatusServlet")
public class TrackStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String referenceNumber = request.getParameter("referenceNumber");

		try (Connection conn = DBUtil.getConnection()) {
			String sql = "SELECT status FROM status_history WHERE reference_number = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, referenceNumber);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				request.setAttribute("status", rs.getString("status"));
			} else {
				request.setAttribute("error", "Invalid Reference Number! Please check and try again.");
			}

			request.getRequestDispatcher("JSP/TrackStatus.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Database error occurred. Please try again.");
			request.getRequestDispatcher("JSP/TrackStatus.jsp").forward(request, response);
		}
	}
}
