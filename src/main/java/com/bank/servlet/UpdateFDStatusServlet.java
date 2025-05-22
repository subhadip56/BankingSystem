package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

public class UpdateFDStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("requestId"));
		String newStatus = req.getParameter("action"); // “Approved” or “Rejected”

		String sql = "UPDATE fd_rd_request SET status=?, created_at=created_at WHERE request_id=?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, newStatus);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		resp.sendRedirect(req.getContextPath() + "/ViewPendingFDServlet?message=Request+updated");
	}
}
