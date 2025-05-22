package com.bank.servlet;

import com.bank.controller.DBUtil;
import com.bank.model.Transaction;

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

//@WebServlet("/MiniStatementServlet")
public class MiniStatementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String custId = (String) req.getSession().getAttribute("customerId");
		List<Transaction> txns = new ArrayList<>();

		String sql = "SELECT t.transaction_date, t.description, t.type, t.amount" + " FROM transaction_history t"
				+ " JOIN account a ON t.account_number = a.account_number" + " WHERE a.customer_id = ?"
				+ " ORDER BY t.transaction_date DESC LIMIT 10";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, custId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					txns.add(new Transaction(rs.getTimestamp("transaction_date"), rs.getString("description"),
							rs.getString("type"), rs.getDouble("amount")));
				}
			}

		} catch (SQLException e) {
			throw new ServletException("Error fetching mini statement", e);
		}

		req.setAttribute("transactions", txns);
		req.getRequestDispatcher("JSP/MiniStatement.jsp").forward(req, resp);
	}
}