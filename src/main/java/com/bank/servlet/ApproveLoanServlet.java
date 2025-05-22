package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

//@WebServlet("/ApproveLoanServlet")
public class ApproveLoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		boolean reject = req.getParameter("reject") != null;
		String newStatus = reject ? "Rejected" : "Approved";
		String sql = reject ? "UPDATE loan_application SET status=? WHERE application_id=?"
				: "UPDATE loan_application SET status=?, decision_date=CURRENT_DATE() WHERE application_id=?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, newStatus);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		resp.sendRedirect(req.getContextPath() + "/ViewPendingLoansServlet");
	}
}
