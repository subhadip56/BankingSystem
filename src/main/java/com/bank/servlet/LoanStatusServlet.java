package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

//@WebServlet("/LoanStatusServlet")
public class LoanStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String custId = (String) req.getSession().getAttribute("customerId");
		if (custId == null) {
			resp.sendRedirect(req.getContextPath() + "JSP/Customer_login.jsp");
			return;
		}

		List<Map<String, Object>> loans = new ArrayList<>();
		String sql = "SELECT application_id, loan_type, amount, tenure_months, status, created_at, decision_date"
				+ " FROM loan_application WHERE customer_id = ? ORDER BY created_at DESC";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, custId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Map<String, Object> m = new HashMap<>();
					m.put("id", rs.getInt("application_id"));
					m.put("type", rs.getString("loan_type"));
					m.put("amount", rs.getBigDecimal("amount"));
					m.put("tenure", rs.getInt("tenure_months"));
					m.put("status", rs.getString("status"));
					m.put("applied", rs.getTimestamp("created_at"));
					m.put("decided", rs.getDate("decision_date"));
					loans.add(m);
				}
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		req.setAttribute("loans", loans);
		req.getRequestDispatcher("JSP/LoanStatus.jsp").forward(req, resp);
	}
}
