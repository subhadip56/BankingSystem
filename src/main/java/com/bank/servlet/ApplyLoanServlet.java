package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;

//@WebServlet("/ApplyLoanServlet")
public class ApplyLoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String custId = (String) req.getSession().getAttribute("customerId");
		if (custId == null) {
			resp.sendRedirect(req.getContextPath() + "JSP/Customer_login.jsp");
			return;
		}

		String acct = req.getParameter("accountNumber");
		String loanType = req.getParameter("loanType");
		BigDecimal amt = new BigDecimal(req.getParameter("amount"));
		int tenure = Integer.parseInt(req.getParameter("tenure"));

		String msg;
		String insert = "INSERT INTO loan_application" + " (customer_id, loan_type, amount, tenure_months, status)"
				+ " VALUES (?, ?, ?, ?, 'Pending')";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(insert)) {

			ps.setString(1, custId);
			ps.setString(2, loanType);
			ps.setBigDecimal(3, amt);
			ps.setInt(4, tenure);
			ps.executeUpdate();
			msg = "Loan application submitted. Awaiting approval.";

		} catch (SQLException e) {
			throw new ServletException(e);
		}

		req.setAttribute("message", msg);
		// forward back to GET to reload accounts, rates
		new LoanRequestServlet().doGet(req, resp);
	}
}
