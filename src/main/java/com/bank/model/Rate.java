package com.bank.model;

public class Rate {
	private String type;
	private int rate;

	public Rate() {
	}

	public Rate(String type, int rate) {
		this.type = type;
		this.rate = rate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}
