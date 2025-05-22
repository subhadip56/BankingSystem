package com.bank.servlet;

import com.bank.dao.GrievanceDAO;
import com.bank.model.Grievance;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


public class GrievanceStatusServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private GrievanceDAO dao = new GrievanceDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String customerId = (String) req.getSession().getAttribute("customerId");
		List<Grievance> list = dao.findByCustomer(customerId);
		req.setAttribute("grievances", list);
		req.getRequestDispatcher("JSP/GrievanceStatus.jsp").forward(req, resp);
	}
}