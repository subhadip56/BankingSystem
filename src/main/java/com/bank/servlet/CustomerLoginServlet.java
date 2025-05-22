package com.bank.servlet;

import com.bank.controller.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String customerId = req.getParameter("customerId");
		String password = req.getParameter("password");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn
						.prepareStatement("SELECT name, password FROM customer WHERE customer_id = ?")) {

			ps.setString(1, customerId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next() && rs.getString("password").equals(password)) {
					// success
					HttpSession session = req.getSession();
					session.setAttribute("customerId", customerId);
					session.setAttribute("customerName", rs.getString("name"));
					resp.sendRedirect("JSP/CustomerDashboard.jsp");
					return;
				}
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}

		    // failure
		    resp.sendRedirect("JSP/Customer_login.jsp?error=invalid");
		 

	}
}
