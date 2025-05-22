package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class EditEmployeeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("id");
		if (idParam == null) {
			resp.sendRedirect("ManageEmployeesServlet");
			return;
		}
		int id = Integer.parseInt(idParam);

		String sql = "SELECT employee_id, full_name, email, username, role FROM employee WHERE employee_id = ?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					req.setAttribute("editEmpId", rs.getInt("employee_id"));
					req.setAttribute("fullName", rs.getString("full_name"));
					req.setAttribute("email", rs.getString("email"));
					req.setAttribute("username", rs.getString("username"));
					req.setAttribute("role", rs.getString("role"));
					req.getRequestDispatcher("JSP/edit_employee.jsp").forward(req, resp);
				} else {
					resp.sendRedirect("ManageEmployeesServlet");
				}
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}