package com.bank.servlet;

import com.bank.controller.DBUtil;
import com.bank.model.FDRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

//@WebServlet("/FDStatusServlet")
public class FDStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = (String) req.getSession().getAttribute("customerId");
		if (custId == null) {
			resp.sendRedirect(req.getContextPath() + "JSP/Customer_login.jsp");
			return;
		}

		List<FDRequest> list = new ArrayList<>();
		String sql = "SELECT * FROM fd_rd_request WHERE customer_id = ? ORDER BY created_at DESC";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, custId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					FDRequest r = new FDRequest();
					r.setRequestId(rs.getInt("request_id"));
					r.setDepositType(rs.getString("deposit_type"));
					r.setAmount(rs.getBigDecimal("amount"));
					r.setTenureMonths(rs.getInt("tenure_months"));
					r.setInterestRate(rs.getBigDecimal("interest_rate"));
					r.setStatus(rs.getString("status"));
					r.setCreatedAt(rs.getTimestamp("created_at"));
					list.add(r);
				}
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		req.setAttribute("fdList", list);
		req.getRequestDispatcher("JSP/FDStatus.jsp").forward(req, resp);
	}
}
