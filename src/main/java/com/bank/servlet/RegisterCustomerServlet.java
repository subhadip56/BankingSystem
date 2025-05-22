package com.bank.servlet;

import com.bank.controller.DBUtil;
import com.bank.model.RegistrationData;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class RegisterCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private String generateReferenceNumber() {
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 8; i++) {
			sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
		}
		return sb.toString();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("registrationData") == null) {
			// no data → start over
			response.sendRedirect("JSP/Register1.jsp");
			return;
		}

		// 1) Pull everything from our DTO
		RegistrationData data = (RegistrationData) session.getAttribute("registrationData");

		// 2) This page’s inputs
		String accountType = request.getParameter("accountType");
		String[] servicesArr = request.getParameterValues("services");
		String services = (servicesArr != null) ? String.join(",", servicesArr) : "";

		// 3) Build ref #
		String referenceNumber = generateReferenceNumber();

		String sqlPending = "INSERT INTO pending_customers(" + "name, father_name, gender, dob, email, marital_status,"
				+ "address, city, pin_code, state, religion, category, income,"
				+ "education, occupation, pan, aadhar, senior_citizen, existing_account,"
				+ "account_type, services, reference_number" + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		String sqlHistory = "INSERT INTO status_history(reference_number, status) VALUES (?, 'Pending')";

		try (Connection con = DBUtil.getConnection()) {
			con.setAutoCommit(false);

			// a) pending_customers
			try (PreparedStatement ps1 = con.prepareStatement(sqlPending);
					PreparedStatement ps2 = con.prepareStatement(sqlHistory)) {

				// 1–3 are from steps 1&2
				ps1.setString(1, data.getName());
				ps1.setString(2, data.getFatherName());
				ps1.setString(3, data.getGender());
				// convert util.Date → sql.Date
				Date sqlDob = new Date(data.getDob().getTime());
				ps1.setDate(4, sqlDob);
				ps1.setString(5, data.getEmail());
				ps1.setString(6, data.getMaritalStatus());
				ps1.setString(7, data.getAddress());
				ps1.setString(8, data.getCity());
				ps1.setString(9, data.getPinCode());
				ps1.setString(10, data.getState());
				ps1.setString(11, data.getReligion());
				ps1.setString(12, data.getCategory());
				ps1.setString(13, data.getIncome());
				ps1.setString(14, data.getEducation());
				ps1.setString(15, data.getOccupation());
				ps1.setString(16, data.getPan());
				ps1.setString(17, data.getAadhar());
				ps1.setBoolean(18, data.isSeniorCitizen());
				ps1.setBoolean(19, data.isExistingAccount());
				ps1.setString(20, accountType);
				ps1.setString(21, services);
				ps1.setString(22, referenceNumber);
				ps1.executeUpdate();

				// then history
				ps2.setString(1, referenceNumber);
				ps2.executeUpdate();

				con.commit();
			} catch (Exception ex) {
				con.rollback();
				throw ex;
			}
		} catch (Exception e) {
			throw new ServletException("Registration failed", e);
		}

		// 4) clean up & forward
		session.invalidate();
		request.setAttribute("referenceNumber", referenceNumber);
		request.getRequestDispatcher("JSP/waiting_page.jsp").forward(request, response);
	}
}
