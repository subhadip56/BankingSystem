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

import com.bank.model.TransferRequest;
import com.bank.controller.DBUtil;


public class TransferStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customerId") == null) {
            response.sendRedirect(request.getContextPath() + "/JSP/Customer_login.jsp");
            return;
        }

        String customerId = (String) session.getAttribute("customerId");
        List<TransferRequest> transferRequests = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT p.request_id, p.beneficiary_customer_id, c.name AS beneficiary_name, p.amount, p.status, p.admin_comments " +
                         "FROM pending_fund_transfers p " +
                         "JOIN customer c ON p.beneficiary_customer_id = c.customer_id " +
                         "WHERE p.sender_customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TransferRequest Request = new TransferRequest();
                Request.setRequestId(rs.getInt("request_id"));
                Request.setBeneficiaryCustomerId(rs.getString("beneficiary_customer_id"));
                Request.setBeneficiaryName(rs.getString("beneficiary_name"));
                Request.setAmount(rs.getDouble("amount"));
                Request.setStatus(rs.getString("status"));
                Request.setAdminComments(rs.getString("admin_comments"));
                transferRequests.add(Request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to retrieve transfer status due to a database error.");
        }

        request.setAttribute("transferRequests", transferRequests);
        request.getRequestDispatcher("/JSP/TransferStatus.jsp").forward(request, response);
    }
}