package com.bank.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bank.controller.DBUtil;

public class FundTransferServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customerId") == null) {
            response.sendRedirect(request.getContextPath() + "/JSP/Customer_login.jsp");
            return;
        }

        String customerId = (String) session.getAttribute("customerId");

        try (Connection conn = DBUtil.getConnection()) {
            // Retrieve customer's accounts
            String sqlAccounts = "SELECT account_number, account_type FROM account WHERE customer_id = ?";
            PreparedStatement stmtAccounts = conn.prepareStatement(sqlAccounts);
            stmtAccounts.setString(1, customerId);
            ResultSet rsAccounts = stmtAccounts.executeQuery();
            List<String> accounts = new ArrayList<>();
            while (rsAccounts.next()) {
                accounts.add(rsAccounts.getString("account_number") + " - " + rsAccounts.getString("account_type"));
            }

            // Retrieve customer's beneficiaries
            String sqlBeneficiaries = "SELECT b.beneficiary_customer_id, c.name " +
                                      "FROM beneficiary b " +
                                      "JOIN customer c ON b.beneficiary_customer_id = c.customer_id " +
                                      "WHERE b.customer_id = ?";
            PreparedStatement stmtBeneficiaries = conn.prepareStatement(sqlBeneficiaries);
            stmtBeneficiaries.setString(1, customerId);
            ResultSet rsBeneficiaries = stmtBeneficiaries.executeQuery();
            List<String> beneficiaries = new ArrayList<>();
            while (rsBeneficiaries.next()) {
                beneficiaries.add(rsBeneficiaries.getString("beneficiary_customer_id") + " - " + rsBeneficiaries.getString("name"));
            }

            request.setAttribute("accounts", accounts);
            request.setAttribute("beneficiaries", beneficiaries);
            request.getRequestDispatcher("JSP/FundTransfer.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customerId") == null) {
            response.sendRedirect(request.getContextPath() + "/JSP/Customer_login.jsp");
            return;
        }

        String customerId = (String) session.getAttribute("customerId");
        String senderAccount = request.getParameter("senderAccount");
        String beneficiaryCustomerId = request.getParameter("beneficiaryCustomerId");
        String amountStr = request.getParameter("amount");
        String description = request.getParameter("description");

        if (senderAccount == null || beneficiaryCustomerId == null || amountStr == null) {
            request.setAttribute("error", "All required fields must be filled");
            doGet(request, response);
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Please enter a valid positive amount");
            doGet(request, response);
            return;
        }

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO pending_fund_transfers (sender_customer_id, sender_account_number, beneficiary_customer_id, amount, description, status) VALUES (?, ?, ?, ?, ?, 'Pending')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, customerId);
            stmt.setString(2, senderAccount.split(" - ")[0]);
            stmt.setString(3, beneficiaryCustomerId.split(" - ")[0]);
            stmt.setDouble(4, amount);
            stmt.setString(5, description);
            stmt.executeUpdate();

            response.sendRedirect(request.getContextPath() + "/JSP/CustomerDashboard.jsp?message=Fund transfer request submitted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
        }
    }
}