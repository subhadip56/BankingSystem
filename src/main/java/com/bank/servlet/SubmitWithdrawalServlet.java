package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

//@WebServlet("/SubmitWithdrawalServlet")
public class SubmitWithdrawalServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cid = (String) req.getSession().getAttribute("customerId");
		String acct = req.getParameter("accountNumber");
		double amt = Double.parseDouble(req.getParameter("amount"));
		try (Connection conn = DBUtil.getConnection()) {
			String sql = "INSERT INTO withdrawal_request(customer_id,account_number,amount) VALUES(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setString(2, acct);
			ps.setDouble(3, amt);
			ps.executeUpdate();
			req.setAttribute("message", "Withdrawal request submitted. Awaiting approval.");
		} catch (SQLException e) {
			req.setAttribute("message", "Error submitting withdrawal.");
		}
		req.getRequestDispatcher("JSP/WithdrawPage.jsp").forward(req, resp);
	}
}