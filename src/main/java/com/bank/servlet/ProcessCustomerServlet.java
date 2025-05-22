package com.bank.servlet;

import com.bank.controller.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ProcessCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Generate alphanumeric password
	private String generatePassword(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		}
		return sb.toString();
	}

	// Generate numeric string (e.g., card PIN, account number)
	private String generateNumeric(int length) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(rnd.nextInt(10));
		}
		return sb.toString();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String refNum = request.getParameter("referenceNumber");
		String action = request.getParameter("action");

		try (Connection conn = DBUtil.getConnection()) {
			conn.setAutoCommit(false);

			if ("approve".equals(action)) {
				String selSQL = "SELECT * FROM pending_customers WHERE reference_number = ?";
				try (PreparedStatement selPS = conn.prepareStatement(selSQL)) {
					selPS.setString(1, refNum);
					try (ResultSet rs = selPS.executeQuery()) {
						if (rs.next()) {
							// Prepare customer data
							String custId = "CUST" + System.currentTimeMillis();
							String cardNo = generateNumeric(16);
							String pin = generateNumeric(4);
							String password = generatePassword(5);
							String accountType = rs.getString("account_type");

							// Insert into customer table
							String insCustSQL = "INSERT INTO customer (customer_id, name, father_name, gender, dob, email, "
									+ "marital_status, address, city, pin_code, state, religion, category, income, education, "
									+ "occupation, pan, aadhar, senior_citizen, existing_account, password, card_number, card_pin) "
									+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
							
							try (PreparedStatement insCustPS = conn.prepareStatement(insCustSQL)) {
								
								int idx = 1;
								insCustPS.setString(idx++, custId);
								insCustPS.setString(idx++, rs.getString("name"));
								insCustPS.setString(idx++, rs.getString("father_name"));
								insCustPS.setString(idx++, rs.getString("gender"));
								insCustPS.setDate(idx++, rs.getDate("dob"));
								insCustPS.setString(idx++, rs.getString("email"));
								insCustPS.setString(idx++, rs.getString("marital_status"));
								insCustPS.setString(idx++, rs.getString("address"));
								insCustPS.setString(idx++, rs.getString("city"));
								insCustPS.setString(idx++, rs.getString("pin_code"));
								insCustPS.setString(idx++, rs.getString("state"));
								insCustPS.setString(idx++, rs.getString("religion"));
								insCustPS.setString(idx++, rs.getString("category"));
								insCustPS.setString(idx++, rs.getString("income"));
								insCustPS.setString(idx++, rs.getString("education"));
								insCustPS.setString(idx++, rs.getString("occupation"));
								insCustPS.setString(idx++, rs.getString("pan"));
								insCustPS.setString(idx++, rs.getString("aadhar"));
								insCustPS.setBoolean(idx++, rs.getBoolean("senior_citizen"));
								insCustPS.setBoolean(idx++, rs.getBoolean("existing_account"));
								insCustPS.setString(idx++, password);
								insCustPS.setString(idx++, cardNo);
								insCustPS.setString(idx, pin);
								insCustPS.executeUpdate();
							}

							// Insert into account table with minimum balance
							String acctNo = "ACCT" + generateNumeric(12);
							double minBalance = 2000.00;
							String insAcctSQL = "INSERT INTO account (customer_id, account_number, account_type, balance) "
									+ "VALUES (?, ?, ?, ?)";
							try (PreparedStatement insAcctPS = conn.prepareStatement(insAcctSQL)) {
								insAcctPS.setString(1, custId);
								insAcctPS.setString(2, acctNo);
								insAcctPS.setString(3, accountType);
								insAcctPS.setDouble(4, minBalance);
								insAcctPS.executeUpdate();
							}

							// Update status history
							String updStatusSQL = "UPDATE status_history SET status = 'Approved' WHERE reference_number = ?";
							try (PreparedStatement updPS = conn.prepareStatement(updStatusSQL)) {
								updPS.setString(1, refNum);
								updPS.executeUpdate();
							}

							// Delete from pending_customers
							String delPendingSQL = "DELETE FROM pending_customers WHERE reference_number = ?";
							try (PreparedStatement delPS = conn.prepareStatement(delPendingSQL)) {
								delPS.setString(1, refNum);
								delPS.executeUpdate();
							}
						}
					}
				}
			} else if ("reject".equals(action)) {
				// Handle rejection
				String updStatusSQL = "UPDATE status_history SET status = 'Rejected' WHERE reference_number = ?";
				try (PreparedStatement updPS = conn.prepareStatement(updStatusSQL)) {
					updPS.setString(1, refNum);
					updPS.executeUpdate();
				}
				String delPendingSQL = "DELETE FROM pending_customers WHERE reference_number = ?";
				try (PreparedStatement delPS = conn.prepareStatement(delPendingSQL)) {
					delPS.setString(1, refNum);
					delPS.executeUpdate();
				}
			}

			conn.commit();
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		response.sendRedirect("ViewPendingCustomersServlet");
	}

}
