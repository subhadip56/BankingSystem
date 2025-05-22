package com.bank.servlet;

import com.bank.controller.DBUtil;
import com.bank.model.Account;
import com.bank.model.Rate;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

//@WebServlet("/LoanRequestServlet")
public class LoanRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Predefined map of loanType → interestRate%
	private static final Map<String, Integer> RATES = Map.of("Home", 7, "Car", 9, "Personal", 12, "Education", 5,
			"Business", 11);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// If someone just came back from a POST and set a message:
		String msg = req.getParameter("message");
		if (msg != null) {
			req.setAttribute("message", msg);
		}

		String custId = (String) req.getSession().getAttribute("customerId");
		if (custId == null) {
			resp.sendRedirect(req.getContextPath() + "JSP/Customer_login.jsp");
			return;
		}

		// 1) load this customer's accounts
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
			throw new ServletException(e);
		}

		// 2) convert the static RATES map into a List<Rate> for the JSP
		List<Rate> rateList = RATES.entrySet().stream().map(e -> new Rate(e.getKey(), e.getValue()))
				.collect(Collectors.toList());

		req.setAttribute("accounts", accounts);
		req.setAttribute("rates", rateList);
		req.getRequestDispatcher("JSP/LoanRequest.jsp").forward(req, resp);
	}
}
