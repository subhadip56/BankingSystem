package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

//@WebServlet("/ViewPendingTransactionsServlet")
public class ViewPendingTransactionsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (Connection conn = DBUtil.getConnection()) {
			// Fetch deposits
			List<Map<String, Object>> deps = new ArrayList<>();
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM deposit_request WHERE status='Pending'")) {
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Map<String, Object> m = new HashMap<>();
					m.put("type", "Deposit");
					m.put("id", rs.getInt("request_id"));
					m.put("acct", rs.getString("account_number"));
					m.put("amt", rs.getDouble("amount"));
					m.put("cid", rs.getString("customer_id"));
					deps.add(m);
				}
			}
			// Fetch withdrawals similarly
			List<Map<String, Object>> wds = new ArrayList<>();
			try (PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM withdrawal_request WHERE status='Pending'")) {
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Map<String, Object> m = new HashMap<>();
					m.put("type", "Withdrawal");
					m.put("id", rs.getInt("request_id"));
					m.put("acct", rs.getString("account_number"));
					m.put("amt", rs.getDouble("amount"));
					m.put("cid", rs.getString("customer_id"));
					wds.add(m);
				}
			}
			req.setAttribute("deposits", deps);
			req.setAttribute("withdrawals", wds);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		req.getRequestDispatcher("JSP/PendingTransactions.jsp").forward(req, resp);
	}
}