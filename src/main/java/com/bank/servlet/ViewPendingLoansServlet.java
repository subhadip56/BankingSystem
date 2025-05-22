package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

//@WebServlet("/ViewPendingLoansServlet")
public class ViewPendingLoansServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Map<String, Object>> pendings = new ArrayList<>();
		String sql = "SELECT application_id, customer_id, loan_type, amount, tenure_months, created_at"
				+ " FROM loan_application WHERE status='Pending' ORDER BY created_at";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("id", rs.getInt("application_id"));
				m.put("cust", rs.getString("customer_id"));
				m.put("type", rs.getString("loan_type"));
				m.put("amount", rs.getBigDecimal("amount"));
				m.put("tenure", rs.getInt("tenure_months"));
				m.put("appliedOn", rs.getTimestamp("created_at"));
				pendings.add(m);
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		req.setAttribute("pendings", pendings);
		req.getRequestDispatcher("JSP/ManageLoanApplications.jsp").forward(req, resp);
	}
}
