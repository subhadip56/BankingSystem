package com.bank.servlet;

import com.bank.controller.DBUtil;
import com.bank.model.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WithdrawPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. Check login
		HttpSession session = req.getSession(false);
		String custId = (session != null) ? (String) session.getAttribute("customerId") : null;
		if (custId == null) {
			resp.sendRedirect(req.getContextPath() + "JSP/Customer_login.jsp");
			return;
		}

		// 2. Load all accounts for this customer
		List<Account> accounts = new ArrayList<>();
		String sql = "SELECT account_number, account_type, balance " + "FROM account WHERE customer_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, custId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					accounts.add(new Account(rs.getString("account_number"), rs.getString("account_type"),
							rs.getDouble("balance")));
				}
			}
		} catch (SQLException e) {
			throw new ServletException("Could not load accounts", e);
		}

		// 3. Forward to JSP
		req.setAttribute("accounts", accounts);
		req.getRequestDispatcher("JSP/WithdrawPage.jsp").forward(req, resp);
	}
}
