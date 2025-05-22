package com.bank.dao;

import com.bank.controller.DBUtil;
import java.math.BigDecimal;
import java.sql.*;

public class AccountDAO {
	public BigDecimal getBalance(String custId) throws SQLException {
		String sql = "SELECT balance FROM account WHERE customer_id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, custId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getBigDecimal("balance");
			throw new SQLException("Account not found for " + custId);
		}
	}

	public void updateBalance(String custId, BigDecimal newBal) throws SQLException {
		String sql = "UPDATE account SET balance=? WHERE customer_id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setBigDecimal(1, newBal);
			ps.setString(2, custId);
			ps.executeUpdate();
		}
	}

	public String findCustomerByAccount(String accountNumber) throws SQLException {
		String sql = "SELECT customer_id FROM account WHERE account_number = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getString("customer_id");
			throw new SQLException("No account found: " + accountNumber);
		}
	}
}