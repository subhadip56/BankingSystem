package com.bank.servlet;

import com.bank.dao.GrievanceDAO;
import com.bank.model.Grievance;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class GrievanceRequestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final GrievanceDAO dao = new GrievanceDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("JSP/GrievanceRequest.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String customerId = (String) req.getSession().getAttribute("customerId");
		if (customerId == null) {
			resp.sendRedirect(req.getContextPath() + "/JSP/Customer_login.jsp?error=Please+login");
			return;
		}

		Grievance g = new Grievance();
		g.setCustomerId(customerId);
		g.setSubject(req.getParameter("subject"));
		g.setDescription(req.getParameter("description"));

		try {
			dao.submitGrievance(g);
			resp.sendRedirect(req.getContextPath() + "/JSP/CustomerDashboard.jsp?message=Grievance+submitted");
		} catch (RuntimeException e) {
			// Log server‐side:
			e.printStackTrace();
			// Show user‐friendly message:
			req.setAttribute("error", "Failed to submit: " + e.getMessage());
			doGet(req, resp);
		}
	}
}
