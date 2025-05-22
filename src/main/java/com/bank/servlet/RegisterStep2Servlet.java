package com.bank.servlet;

import com.bank.model.RegistrationData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;

public class RegisterStep2Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		RegistrationData data = (RegistrationData) session.getAttribute("registrationData");
		if (data == null) {
			resp.sendRedirect("JSP/Register1.jsp");
			return;
		}

		data.setReligion(req.getParameter("religion"));
		data.setCategory(req.getParameter("category"));
		data.setIncome(req.getParameter("income"));
		data.setEducation(req.getParameter("education"));
		data.setOccupation(req.getParameter("occupation"));
		data.setPan(req.getParameter("pan"));
		data.setAadhar(req.getParameter("aadhar"));
		data.setSeniorCitizen("Yes".equalsIgnoreCase(req.getParameter("seniorCitizen")));
		data.setExistingAccount("Yes".equalsIgnoreCase(req.getParameter("existingAccount")));

		RequestDispatcher rd = req.getRequestDispatcher("JSP/Register3.jsp");
		rd.forward(req, resp);
	}
}
