package com.bank.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bank.controller.DBUtil;
import com.bank.model.Grievance;

public class GrievanceDAO {

	public void submitGrievance(Grievance g) {
		String sql = "INSERT INTO grievance(customer_id, subject, description,created_at) VALUES (?,?,?,NOW())";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, g.getCustomerId());
			ps.setString(2, g.getSubject());
			ps.setString(3, g.getDescription());

			int updated = ps.executeUpdate();
			if (updated != 1) {
				throw new RuntimeException("Expected 1 row inserted, got " + updated);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error inserting grievance: " + e.getMessage(), e);
		}
	}

	public List<Grievance> findByCustomer(String customerId) {
		String sql = "SELECT * FROM grievance WHERE customer_id=? ORDER BY created_at DESC";
		List<Grievance> list = new ArrayList<>();
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(map(rs));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error fetching grievances: " + e.getMessage(), e);
		}
		return list;
	}

	public List<Grievance> findAll() {
		String sql = "SELECT * FROM grievance ORDER BY created_at DESC";
		List<Grievance> list = new ArrayList<>();
		try (Connection con = DBUtil.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				list.add(map(rs));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error fetching all grievances: " + e.getMessage(), e);
		}
		return list;
	}

	public void updateStatus(int id, String status) {
		String sql = "UPDATE grievance SET status=? WHERE grievance_id=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, status);
			ps.setInt(2, id);

			int updated = ps.executeUpdate();
			if (updated != 1) {
				throw new RuntimeException("Expected 1 row updated, got " + updated);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error updating grievance status: " + e.getMessage(), e);
		}
	}

	private Grievance map(ResultSet rs) throws SQLException {
		Grievance g = new Grievance();
		g.setGrievanceId(rs.getInt("grievance_id"));
		g.setCustomerId(rs.getString("customer_id"));
		g.setSubject(rs.getString("subject"));
		g.setDescription(rs.getString("description"));
		g.setStatus(rs.getString("status"));
		g.setCreatedAt(rs.getTimestamp("created_at"));
		g.setUpdatedAt(rs.getTimestamp("updated_at"));
		return g;
	}
}
