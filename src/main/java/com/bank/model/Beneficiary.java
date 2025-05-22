package com.bank.model;

public class Beneficiary {
	private String beneficiaryId;
	private String ownerCustomerId; // who created this beneficiary
	private String name; // beneficiary’s name
	private String accountNumber; // beneficiary’s account number
	private String bankIfsc;
	private String nickname;

	// getters & setters
	public String getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(String id) {
		this.beneficiaryId = id;
	}

	public String getOwnerCustomerId() {
		return ownerCustomerId;
	}

	public void setOwnerCustomerId(String cid) {
		this.ownerCustomerId = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String acct) {
		this.accountNumber = acct;
	}

	public String getBankIfsc() {
		return bankIfsc;
	}

	public void setBankIfsc(String ifsc) {
		this.bankIfsc = ifsc;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nick) {
		this.nickname = nick;
	}
}