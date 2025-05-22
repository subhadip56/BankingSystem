package com.bank.model;

import java.sql.Timestamp;

public class PendingFundTransfer {
	private int requestId;
	private String senderCustomerId;
	private String senderName;
	private String senderAccountNumber;
	private String beneficiaryCustomerId;
	private String beneficiaryName;
	private double amount;
	private String description;
	private Timestamp createdAt;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getSenderCustomerId() {
		return senderCustomerId;
	}

	public void setSenderCustomerId(String senderCustomerId) {
		this.senderCustomerId = senderCustomerId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}

	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}

	public String getBeneficiaryCustomerId() {
		return beneficiaryCustomerId;
	}

	public void setBeneficiaryCustomerId(String beneficiaryCustomerId) {
		this.beneficiaryCustomerId = beneficiaryCustomerId;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}