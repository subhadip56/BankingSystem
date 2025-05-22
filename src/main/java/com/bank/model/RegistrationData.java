package com.bank.model;

import java.io.Serializable;
import java.util.Date;

public class RegistrationData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String fatherName;
	private String gender;
	private Date dob;
	private String email;
	private String maritalStatus;
	private String address;
	private String city;
	private String pinCode;
	private String state;
	private String religion;
	private String category;
	private String income;
	private String education;
	private String occupation;
	private String pan;
	private String aadhar;
	private boolean seniorCitizen;
	private boolean existingAccount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public boolean isSeniorCitizen() {
		return seniorCitizen;
	}

	public void setSeniorCitizen(boolean seniorCitizen) {
		this.seniorCitizen = seniorCitizen;
	}

	public boolean isExistingAccount() {
		return existingAccount;
	}

	public void setExistingAccount(boolean existingAccount) {
		this.existingAccount = existingAccount;
	}

}
