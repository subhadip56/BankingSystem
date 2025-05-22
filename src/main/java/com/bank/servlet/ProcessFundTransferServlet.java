package com.bank.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bank.controller.DBUtil;


public class ProcessFundTransferServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestIdStr = request.getParameter("requestId");
        String action = request.getParameter("action");
        String comment = request.getParameter("comment");

        int requestId;
        try {
            requestId = Integer.parseInt(requestIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request ID");
            return;
        }

        try (Connection conn = DBUtil.getConnection()) {
            String sqlRequest = "SELECT * FROM pending_fund_transfers WHERE request_id = ? AND status = 'Pending'";
            PreparedStatement stmtRequest = conn.prepareStatement(sqlRequest);
            stmtRequest.setInt(1, requestId);
            ResultSet rsRequest = stmtRequest.executeQuery();

            if (rsRequest.next()) {
                if ("approve".equals(action)) {
                    String senderAccount = rsRequest.getString("sender_account_number");
                    String beneficiaryCustomerId = rsRequest.getString("beneficiary_customer_id");
                    double amount = rsRequest.getDouble("amount");
                    String description = rsRequest.getString("description");

                    String sqlBeneficiaryAccount = "SELECT account_number FROM account WHERE customer_id = ? ORDER BY account_id ASC LIMIT 1";
                    PreparedStatement stmtBeneficiaryAccount = conn.prepareStatement(sqlBeneficiaryAccount);
                    stmtBeneficiaryAccount.setString(1, beneficiaryCustomerId);
                    ResultSet rsBeneficiaryAccount = stmtBeneficiaryAccount.executeQuery();

                    if (rsBeneficiaryAccount.next()) {
                        String beneficiaryAccount = rsBeneficiaryAccount.getString("account_number");

                        String sqlBalance = "SELECT balance FROM account WHERE account_number = ?";
                        PreparedStatement stmtBalance = conn.prepareStatement(sqlBalance);
                        stmtBalance.setString(1, senderAccount);
                        ResultSet rsBalance = stmtBalance.executeQuery();

                        if (rsBalance.next()) {
                            double balance = rsBalance.getDouble("balance");
                            if (balance >= amount) {
                                conn.setAutoCommit(false);
                                try {
                                    String sqlDeduct = "UPDATE account SET balance = balance - ? WHERE account_number = ?";
                                    PreparedStatement stmtDeduct = conn.prepareStatement(sqlDeduct);
                                    stmtDeduct.setDouble(1, amount);
                                    stmtDeduct.setString(2, senderAccount);
                                    stmtDeduct.executeUpdate();

                                    String sqlAdd = "UPDATE account SET balance = balance + ? WHERE account_number = ?";
                                    PreparedStatement stmtAdd = conn.prepareStatement(sqlAdd);
                                    stmtAdd.setDouble(1, amount);
                                    stmtAdd.setString(2, beneficiaryAccount);
                                    stmtAdd.executeUpdate();

                                    String sqlTransactionSender = "INSERT INTO transaction_history (account_number, type, amount, description, transaction_date) VALUES (?, 'Debit', ?, ?, NOW())";
                                    PreparedStatement stmtTransactionSender = conn.prepareStatement(sqlTransactionSender);
                                    stmtTransactionSender.setString(1, senderAccount);
                                    stmtTransactionSender.setDouble(2, amount);
                                    stmtTransactionSender.setString(3, "Fund transfer to " + beneficiaryAccount + ": " + description);
                                    stmtTransactionSender.executeUpdate();

                                    String sqlTransactionBeneficiary = "INSERT INTO transaction_history (account_number, type, amount, description, transaction_date) VALUES (?, 'Credit', ?, ?, NOW())";
                                    PreparedStatement stmtTransactionBeneficiary = conn.prepareStatement(sqlTransactionBeneficiary);
                                    stmtTransactionBeneficiary.setString(1, beneficiaryAccount);
                                    stmtTransactionBeneficiary.setDouble(2, amount);
                                    stmtTransactionBeneficiary.setString(3, "Fund transfer from " + senderAccount + ": " + description);
                                    stmtTransactionBeneficiary.executeUpdate();

                                    String sqlUpdateRequest = "UPDATE pending_fund_transfers SET status = 'Approved', decision_date = NOW() WHERE request_id = ?";
                                    PreparedStatement stmtUpdateRequest = conn.prepareStatement(sqlUpdateRequest);
                                    stmtUpdateRequest.setInt(1, requestId);
                                    stmtUpdateRequest.executeUpdate();

                                    conn.commit();
                                } catch (SQLException e) {
                                    conn.rollback();
                                    throw e;
                                }
                            } else {
                                String sqlUpdateRequest = "UPDATE pending_fund_transfers SET status = 'Rejected', decision_date = NOW(), admin_comments = 'Insufficient balance' WHERE request_id = ?";
                                PreparedStatement stmtUpdateRequest = conn.prepareStatement(sqlUpdateRequest);
                                stmtUpdateRequest.setInt(1, requestId);
                                stmtUpdateRequest.executeUpdate();
                            }
                        }
                    } else {
                        String sqlUpdateRequest = "UPDATE pending_fund_transfers SET status = 'Rejected', decision_date = NOW(), admin_comments = 'Beneficiary has no account' WHERE request_id = ?";
                        PreparedStatement stmtUpdateRequest = conn.prepareStatement(sqlUpdateRequest);
                        stmtUpdateRequest.setInt(1, requestId);
                        stmtUpdateRequest.executeUpdate();
                    }
                } else if ("reject".equals(action)) {
                    String sqlUpdateRequest = "UPDATE pending_fund_transfers SET status = 'Rejected', decision_date = NOW(), admin_comments = ? WHERE request_id = ?";
                    PreparedStatement stmtUpdateRequest = conn.prepareStatement(sqlUpdateRequest);
                    stmtUpdateRequest.setString(1, comment != null && !comment.trim().isEmpty() ? comment : "Rejected by admin");
                    stmtUpdateRequest.setInt(2, requestId);
                    stmtUpdateRequest.executeUpdate();
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or already processed request");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/ViewPendingFundTransfersServlet?message=Request processed successfully");
    }
}