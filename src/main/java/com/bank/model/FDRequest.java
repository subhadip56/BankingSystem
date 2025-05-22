package com.bank.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class FDRequest {
	private int requestId;
	private String customerId;
	private String depositType;
	private BigDecimal amount;
	private int tenureMonths;
	private BigDecimal interestRate;
	private String status;
	private Timestamp createdAt;

	public FDRequest() {
	}

	public FDRequest(int requestId, String customerId, String depositType, BigDecimal amount, int tenureMonths,
			BigDecimal interestRate, String status, Timestamp createdAt) {
		this.requestId = requestId;
		this.customerId = customerId;
		this.depositType = depositType;
		this.amount = amount;
		this.tenureMonths = tenureMonths;
		this.interestRate = interestRate;
		this.status = status;
		this.createdAt = createdAt;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getTenureMonths() {
		return tenureMonths;
	}

	public void setTenureMonths(int tenureMonths) {
		this.tenureMonths = tenureMonths;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "FDRequest{" + "requestId=" + requestId + ", customerId='" + customerId + '\'' + ", depositType='"
				+ depositType + '\'' + ", amount=" + amount + ", tenureMonths=" + tenureMonths + ", interestRate="
				+ interestRate + ", status='" + status + '\'' + ", createdAt=" + createdAt + '}';
	}
}
