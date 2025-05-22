package com.bank.servlet;

import com.bank.controller.DBUtil;
import com.bank.model.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepositPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String custId = (String) req.getSession().getAttribute("customerId");
		
		if (custId == null) {
			resp.sendRedirect(req.getContextPath() + "JSP/Customer_login.jsp");
			return;
		}

		List<Account> accounts = new ArrayList<>();
		String sql = "SELECT account_number, account_type, balance FROM account WHERE customer_id = ?";
		
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

		 req.setAttribute("accounts", accounts);
	        req.getRequestDispatcher("JSP/DepositPage.jsp").forward(req, resp);
	}
}
