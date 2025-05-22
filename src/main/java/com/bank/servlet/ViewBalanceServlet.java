package com.bank.servlet;

import com.bank.controller.DBUtil;
import com.bank.model.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/ViewBalanceServlet")
public class ViewBalanceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String custId = (String) req.getSession().getAttribute("customerId");
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
			throw new ServletException("Error fetching balances", e);
		}

		req.setAttribute("accounts", accounts);
		req.getRequestDispatcher("JSP/ViewBalance.jsp").forward(req, resp);
	}
}