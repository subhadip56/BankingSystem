package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Map;

//@WebServlet("/FDRequestServlet")
public class FDRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// simple static map of interest rates by (type,tenure)
	private static final Map<String, Double> FD_RATES = Map.of("FD-6", 5.0, "FD-12", 6.0, "FD-24", 6.5, "FD-36", 7.0,
			"RD-6", 4.5, "RD-12", 5.0, "RD-24", 5.5, "RD-36", 6.0);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// just forward to the JSP
		req.getRequestDispatcher("JSP/FDAccountRequest.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String custId = (String) req.getSession().getAttribute("customerId");
		if (custId == null) {
			resp.sendRedirect(req.getContextPath() + "JSP/Customer_login.jsp");
			return;
		}

		String type = req.getParameter("depositType");
		BigDecimal amt = new BigDecimal(req.getParameter("amount"));
		int tenure = Integer.parseInt(req.getParameter("tenure"));

		// look up rate
		String key = type + "-" + tenure;
		Double rate = FD_RATES.getOrDefault(key, 0.0);

		String sql = "INSERT INTO fd_rd_request" + " (customer_id, deposit_type, amount, tenure_months, interest_rate) "
				+ "VALUES (?, ?, ?, ?, ?)";
		String msg;
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, custId);
			ps.setString(2, type);
			ps.setBigDecimal(3, amt);
			ps.setInt(4, tenure);
			ps.setBigDecimal(5, BigDecimal.valueOf(rate));
			ps.executeUpdate();
			msg = "Your request has been submitted and is pending approval.";
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		req.setAttribute("message", msg);
		doGet(req, resp);
	}
}
