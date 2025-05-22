package com.bank.model;

import java.sql.Timestamp;

public class Grievance {
	private int grievanceId;
	private String customerId;
	private String subject;
	private String description;
	private String status;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Getters & setters
	public int getGrievanceId() {
		return grievanceId;
	}

	public void setGrievanceId(int id) {
		this.grievanceId = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String cid) {
		this.customerId = cid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String sub) {
		this.subject = sub;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String st) {
		this.status = st;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp t) {
		this.createdAt = t;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp t) {
		this.updatedAt = t;
	}
}
