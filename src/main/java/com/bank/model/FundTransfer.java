package com.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FundTransfer {
	private int transferId;
	private String customerId;
	private int beneficiaryId;
	private String beneficiaryCustomerId;
	private BigDecimal amount;
	private String mode;
	private String remarks;
	private String status;
	private LocalDateTime createdAt;

	// getters & setters
	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int id) {
		this.transferId = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String cid) {
		this.customerId = cid;
	}

	public int getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(int bid) {
		this.beneficiaryId = bid;
	}

	public String getBeneficiaryCustomerId() {
		return beneficiaryCustomerId;
	}

	public void setBeneficiaryCustomerId(String bcid) {
		this.beneficiaryCustomerId = bcid;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amt) {
		this.amount = amt;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String m) {
		this.mode = m;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String r) {
		this.remarks = r;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String s) {
		this.status = s;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime t) {
		this.createdAt = t;
	}
}