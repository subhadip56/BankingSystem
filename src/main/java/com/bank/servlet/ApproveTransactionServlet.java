package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

//@WebServlet("/ApproveTransactionServlet")
public class ApproveTransactionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		int id = Integer.parseInt(req.getParameter("id"));
		boolean reject = req.getParameter("reject") != null;
		try (Connection conn = DBUtil.getConnection()) {
			conn.setAutoCommit(false);
			String table = type.equals("Deposit") ? "deposit_request" : "withdrawal_request";
			// Update status
			try (PreparedStatement ps = conn.prepareStatement("UPDATE " + table + " SET status=? WHERE request_id=?")) {
				ps.setString(1, reject ? "Rejected" : "Approved");
				ps.setInt(2, id);
				ps.executeUpdate();
			}
			if (!reject) {
				// On approval, update balance & record transaction
				String sel = "SELECT customer_id,account_number,amount FROM " + table + " WHERE request_id=?";
				String cid, acct;
				double amt;
				try (PreparedStatement ps = conn.prepareStatement(sel)) {
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					rs.next();
					cid = rs.getString("customer_id");
					acct = rs.getString("account_number");
					amt = rs.getDouble("amount");
				}
				// Adjust balance
				String adj = type.equals("Deposit")
						? "UPDATE account SET balance = balance + ? WHERE account_number = ?"
						: "UPDATE account SET balance = balance - ? WHERE account_number = ?";
				try (PreparedStatement ps = conn.prepareStatement(adj)) {
					ps.setDouble(1, amt);
					ps.setString(2, acct);
					ps.executeUpdate();
				}
				// Insert into transaction_history
				try (PreparedStatement ps = conn.prepareStatement(
						"INSERT INTO transaction_history(account_number,type,amount,description) VALUES(?,?,?,?)")) {
					ps.setString(1, acct);
					ps.setString(2, type.equals("Deposit") ? "Credit" : "Debit");
					ps.setDouble(3, amt);
					ps.setString(4, type + " approval");
					ps.executeUpdate();
				}
			}
			conn.commit();
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		resp.sendRedirect("ViewPendingTransactionsServlet");
	}
}