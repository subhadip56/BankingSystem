package com.bank.model;

public class Account {
	private String accountNumber;
	private String accountType;
	private double balance;

	public Account(String accountNumber, String accountType, double balance) {
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public double getBalance() {
		return balance;
	}
}