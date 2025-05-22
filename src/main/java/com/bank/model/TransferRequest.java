package com.bank.model;

public class TransferRequest {
	private int requestId;
	private String beneficiaryCustomerId;
	private String beneficiaryName;
	private double amount;
	private String status;
	private String adminComments;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdminComments() {
		return adminComments;
	}

	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
	}
}