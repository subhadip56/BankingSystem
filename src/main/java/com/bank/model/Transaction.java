package com.bank.model;

import java.sql.Timestamp;

public class Transaction {
	private Timestamp date;
	private String description;
	private String type;
	private double amount;

	public Transaction(Timestamp date, String description, String type, double amount) {
		this.date = date;
		this.description = description;
		this.type = type;
		this.amount = amount;
	}

	public Timestamp getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}
}