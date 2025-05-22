package com.bank.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.controller.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddBeneficiaryServlet extends HttpServlet {

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
            String sql = "SELECT c.customer_id, c.name FROM customer c " +
                         "WHERE c.customer_id != ? AND c.customer_id NOT IN (" +
                         "SELECT b.beneficiary_customer_id FROM beneficiary b WHERE b.customer_id = ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, customerId);
            stmt.setString(2, customerId);
            ResultSet rs = stmt.executeQuery();
            List<String> customers = new ArrayList<>();
            while (rs.next()) {
                customers.add(rs.getString("customer_id") + " - " + rs.getString("name"));
            }

            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/JSP/AddBeneficiary.jsp").forward(request, response);
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
        String beneficiaryCustomerId = request.getParameter("beneficiaryCustomerId");

        if (beneficiaryCustomerId == null || beneficiaryCustomerId.isEmpty()) {
            request.setAttribute("error", "Please select a beneficiary");
            doGet(request, response);
            return;
        }

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO beneficiary (customer_id, beneficiary_customer_id) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, customerId);
            stmt.setString(2, beneficiaryCustomerId.split(" - ")[0]);
            stmt.executeUpdate();

            response.sendRedirect(request.getContextPath() + "/JSP/CustomerDashboard.jsp?message=Beneficiary added successfully");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Duplicate entry
                request.setAttribute("error", "This beneficiary is already added");
                doGet(request, response);
            } else {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
            }
        }
    }
}