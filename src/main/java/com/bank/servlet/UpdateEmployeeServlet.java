package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String fullName = req.getParameter("fullName");
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String role = req.getParameter("role");

		String sql = "UPDATE employee SET full_name=?, email=?, username=?, role=? WHERE employee_id=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, fullName);
			ps.setString(2, email);
			ps.setString(3, username);
			ps.setString(4, role);
			ps.setInt(5, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		// after update, go back to list
		resp.sendRedirect("ManageEmployeesServlet?message=Employee+updated");
	}
}