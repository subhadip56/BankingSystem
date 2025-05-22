package com.bank.model;

public class PendingCustomer {
	private int pendingId;
	private String name;
	private String email;
	private String accountType;
	private String services;
	private String referenceNumber;

	public int getPendingId() {
		return pendingId;
	}

	public void setPendingId(int pendingId) {
		this.pendingId = pendingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
}