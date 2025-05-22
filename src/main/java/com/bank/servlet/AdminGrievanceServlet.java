package com.bank.servlet;

import com.bank.dao.GrievanceDAO;
import com.bank.model.Grievance;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class AdminGrievanceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private GrievanceDAO dao = new GrievanceDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Grievance> list = dao.findAll();
		req.setAttribute("allGrievances", list);
		req.getRequestDispatcher("JSP/AdminGrievance.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("grievanceId"));
		String status = req.getParameter("status");
		dao.updateStatus(id, status);
		resp.sendRedirect("AdminGrievanceServlet?message=Status+updated");
	}
}