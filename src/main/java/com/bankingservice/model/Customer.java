package com.bankingservice.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Customer {

	@Id
	@Column(name ="customer_id")
	private String customerId;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	private String gender;
	private String city;
	
	@Column(name ="pin_code")
	private long pinCode;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	        name = "customer_account", 
	        joinColumns = { @JoinColumn(name = "account_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "customer_id") })
	private List<Account> account;
	
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}

	public Customer() {}

	public Customer(String customerId, String customerName, Date dateOfBirth, String gender, String city, long pinCode,List<Account> account) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.city = city;
		this.pinCode = pinCode;
		this.account=account;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}
}
