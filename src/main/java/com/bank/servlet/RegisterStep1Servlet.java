package com.bank.servlet;

import com.bank.model.RegistrationData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterStep1Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		RegistrationData data = (RegistrationData) session.getAttribute("registrationData");
		if (data == null) {
			data = new RegistrationData();
			session.setAttribute("registrationData", data);
		}

		// Parse and set each field
		data.setName(req.getParameter("name"));
		data.setFatherName(req.getParameter("fatherName"));
		data.setGender(req.getParameter("gender"));

		// Convert dob string to java.util.Date
		try {
			Date parsedDob = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("dob"));
			data.setDob(parsedDob);
		} catch (Exception e) {
			// handle parse error (e.g. forward back with an error)
		}

		data.setEmail(req.getParameter("email"));
		data.setMaritalStatus(req.getParameter("maritalStatus"));
		data.setAddress(req.getParameter("address"));
		data.setCity(req.getParameter("city"));
		data.setPinCode(req.getParameter("pinCode"));
		data.setState(req.getParameter("state"));

		RequestDispatcher rd = req.getRequestDispatcher("JSP/Register2.jsp");
		rd.forward(req, resp);
	}
}
