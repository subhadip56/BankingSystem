package com.bank.dao;

import com.bank.controller.DBUtil;
import com.bank.model.FundTransfer;
import java.sql.*;
import java.util.*;

public class FundTransferDAO {
  public void createRequest(FundTransfer ft) throws SQLException {
    String sql = "INSERT INTO fund_transfer "
               + "(customer_id, beneficiary_id, amount, transfer_mode, remarks) "
               + "VALUES (?,?,?,?,?)";
    try (Connection c = DBUtil.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {
      ps.setString(1, ft.getCustomerId());
      ps.setInt(2, ft.getBeneficiaryId());
      ps.setBigDecimal(3, ft.getAmount());
      ps.setString(4, ft.getMode());
      ps.setString(5, ft.getRemarks());
      ps.executeUpdate();
    }
  }

  public List<FundTransfer> listPending() throws SQLException {
    String sql = "SELECT ft.transfer_id, ft.customer_id, ft.beneficiary_id,"
               + " b.beneficiary_customer_id, ft.amount, ft.transfer_mode,"
               + " ft.remarks, ft.status, ft.created_at"
               + " FROM fund_transfer ft"
               + " JOIN beneficiary b ON ft.beneficiary_id=b.beneficiary_id"
               + " WHERE ft.status='Pending'";
    try (Connection c = DBUtil.getConnection();
         Statement s = c.createStatement();
         ResultSet rs = s.executeQuery(sql)) {
      List<FundTransfer> list = new ArrayList<>();
      while (rs.next()) {
        FundTransfer ft = new FundTransfer();
        ft.setTransferId(rs.getInt("transfer_id"));
        ft.setCustomerId(rs.getString("customer_id"));
        ft.setBeneficiaryId(rs.getInt("beneficiary_id"));
        ft.setBeneficiaryCustomerId(rs.getString("beneficiary_customer_id"));
        ft.setAmount(rs.getBigDecimal("amount"));
        ft.setMode(rs.getString("transfer_mode"));
        ft.setRemarks(rs.getString("remarks"));
        ft.setStatus(rs.getString("status"));
        ft.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        list.add(ft);
      }
      return list;
    }
  }

  public FundTransfer findById(int id) throws SQLException {
    String sql = "SELECT ft.transfer_id, ft.customer_id, ft.beneficiary_id,"
               + " b.beneficiary_customer_id, ft.amount, ft.transfer_mode,"
               + " ft.remarks, ft.status, ft.created_at"
               + " FROM fund_transfer ft"
               + " JOIN beneficiary b ON ft.beneficiary_id=b.beneficiary_id"
               + " WHERE ft.transfer_id=?";
    try (Connection c = DBUtil.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (!rs.next()) throw new SQLException("Transfer not found");
      FundTransfer ft = new FundTransfer();
      ft.setTransferId(rs.getInt("transfer_id"));
      ft.setCustomerId(rs.getString("customer_id"));
      ft.setBeneficiaryId(rs.getInt("beneficiary_id"));
      ft.setBeneficiaryCustomerId(rs.getString("beneficiary_customer_id"));
      ft.setAmount(rs.getBigDecimal("amount"));
      ft.setMode(rs.getString("transfer_mode"));
      ft.setRemarks(rs.getString("remarks"));
      ft.setStatus(rs.getString("status"));
      ft.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
      return ft;
    }
  }

  public void updateStatus(int id, String status) throws SQLException {
    String sql = "UPDATE fund_transfer SET status=? WHERE transfer_id=?";
    try (Connection c = DBUtil.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {
      ps.setString(1, status);
      ps.setInt(2, id);
      ps.executeUpdate();
    }
  }
}