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

import com.bank.model.PendingFundTransfer;
import com.bank.controller.DBUtil;


public class ViewPendingFundTransfersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PendingFundTransfer> transfers = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT p.*, c1.name AS sender_name, c2.name AS beneficiary_name " +
                         "FROM pending_fund_transfers p " +
                         "JOIN customer c1 ON p.sender_customer_id = c1.customer_id " +
                         "JOIN customer c2 ON p.beneficiary_customer_id = c2.customer_id " +
                         "WHERE p.status = 'Pending'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PendingFundTransfer transfer = new PendingFundTransfer();
                transfer.setRequestId(rs.getInt("request_id"));
                transfer.setSenderCustomerId(rs.getString("sender_customer_id"));
                transfer.setSenderName(rs.getString("sender_name"));
                transfer.setSenderAccountNumber(rs.getString("sender_account_number"));
                transfer.setBeneficiaryCustomerId(rs.getString("beneficiary_customer_id"));
                transfer.setBeneficiaryName(rs.getString("beneficiary_name"));
                transfer.setAmount(rs.getDouble("amount"));
                transfer.setDescription(rs.getString("description"));
                transfer.setCreatedAt(rs.getTimestamp("created_at"));
                transfers.add(transfer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to retrieve pending transfers due to a database error.");
        }
        request.setAttribute("transfers", transfers);
        request.getRequestDispatcher("JSP/pendingFundTransfers.jsp").forward(request, response);
    }
}