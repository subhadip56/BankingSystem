package com.bank.servlet;

import com.bank.controller.DBUtil;
import com.bank.model.FDRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

//@WebServlet("/ViewPendingFDServlet")
public class ViewPendingFDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<FDRequest> pend = new ArrayList<>();
		String sql = "SELECT * FROM fd_rd_request WHERE status='Pending' ORDER BY created_at";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				FDRequest r = new FDRequest();
				r.setRequestId(rs.getInt("request_id"));
				r.setCustomerId(rs.getString("customer_id"));
				r.setDepositType(rs.getString("deposit_type"));
				r.setAmount(rs.getBigDecimal("amount"));
				r.setTenureMonths(rs.getInt("tenure_months"));
				r.setInterestRate(rs.getBigDecimal("interest_rate"));
				r.setCreatedAt(rs.getTimestamp("created_at"));
				pend.add(r);
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		req.setAttribute("pendingFD", pend);
		req.getRequestDispatcher("JSP/AdminPendingFD.jsp").forward(req, resp);
	}
}
