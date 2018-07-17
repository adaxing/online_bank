package com.userfront.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// database, userId field can not be null;
	// updatable is false means it can not be updated once it is given initial value
	// this is example for adding more columns corresponds 
	@Column(name = "userId", nullable = false, updatable = false)
	private Long userId;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	private String phone;
	
	@OneToOne
	private PrimaryAccount primaryAccount;
	@OneToOne
	private SavingsAccount savingsAccount;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Appointment> appointmentList;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Recipient> recipientList;
	
	private boolean enable=true;

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public PrimaryAccount getPrimaryAccount() {
		return primaryAccount;
	}

	public void setPrimaryAccount(PrimaryAccount primaryAccount) {
		this.primaryAccount = primaryAccount;
	}

	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}

	public List<Recipient> getRecipientList() {
		return recipientList;
	}

	public void setRecipientList(List<Recipient> recipientList) {
		this.recipientList = recipientList;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + ", email=" + email + ", phone=" + phone + ", primaryAccount="
				+ primaryAccount + ", savingsAccount=" + savingsAccount + ", appointmentList=" + appointmentList
				+ ", recipientList=" + recipientList + ", enable=" + enable + "]";
	}
	
	
	

}
