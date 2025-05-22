package com.bank.servlet;

import com.bank.controller.DBUtil;
import com.bank.model.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		String customerId = session == null ? null : (String) session.getAttribute("customerId");
		if (customerId == null) {
			resp.sendRedirect(req.getContextPath() + "JSP/Customer_login.jsp");
			return;
		}

		String acct = req.getParameter("accountNumber");
		BigDecimal amount = new BigDecimal(req.getParameter("amount"));

		String error = null;
		String message = null;

		// 1) Check balance
		String balSql = "SELECT balance FROM account WHERE customer_id=? AND account_number=?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(balSql)) {

			ps.setString(1, customerId);
			ps.setString(2, acct);
			try (ResultSet rs = ps.executeQuery()) {
				if (!rs.next()) {
					error = "Selected account not found.";
				} else {
					BigDecimal balance = rs.getBigDecimal("balance");
					if (balance.compareTo(amount) < 0) {
						error = "Insufficient funds. You have only ₹" + balance;
					}
				}
			}

			// 2) If no error, insert withdrawal_request
			if (error == null) {
				String insSql = "INSERT INTO withdrawal_request (customer_id, account_number, amount, status) "
						+ "VALUES (?, ?, ?, 'Pending')";
				try (PreparedStatement ps2 = conn.prepareStatement(insSql)) {
					ps2.setString(1, customerId);
					ps2.setString(2, acct);
					ps2.setBigDecimal(3, amount);
					ps2.executeUpdate();
				}
				message = "₹" + amount + " requested. Waiting for approval.";
			}
		} catch (SQLException e) {
			throw new ServletException("Error processing withdrawal", e);
		}

		// 3) **Always reload** the accounts list before forwarding:
		List<Account> accounts = new ArrayList<>();
		String sql = "SELECT account_number, account_type, balance FROM account WHERE customer_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, customerId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					accounts.add(new Account(rs.getString("account_number"), rs.getString("account_type"),
							rs.getDouble("balance")));
				}
			}
		} catch (SQLException e) {
			throw new ServletException("Could not reload accounts", e);
		}

		req.setAttribute("accounts", accounts);
		req.setAttribute("error", error);
		req.setAttribute("message", message);

		// 4) Forward back to the same JSP:
		req.getRequestDispatcher("JSP/WithdrawPage.jsp").forward(req, resp);
	}
}
