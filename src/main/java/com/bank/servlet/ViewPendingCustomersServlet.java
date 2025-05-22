package com.bank.servlet;

import com.bank.controller.DBUtil;
import com.bank.model.PendingCustomer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class ViewPendingCustomersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<PendingCustomer> pendingList = new ArrayList<>();
		String sql = "SELECT pending_id, name, email, account_type, services, reference_number FROM pending_customers";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				PendingCustomer pc = new PendingCustomer();
				pc.setPendingId(rs.getInt("pending_id"));
				pc.setName(rs.getString("name"));
				pc.setEmail(rs.getString("email"));
				pc.setAccountType(rs.getString("account_type"));
				pc.setServices(rs.getString("services"));
				pc.setReferenceNumber(rs.getString("reference_number"));
				pendingList.add(pc);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}

		request.setAttribute("pendingList", pendingList);
		request.getRequestDispatcher("JSP/manage_customers.jsp").forward(request, response);
	}
}