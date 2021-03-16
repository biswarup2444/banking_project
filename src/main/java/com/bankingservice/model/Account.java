package com.bankingservice.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {
	@Id
	@Column(name = "account_id")
	private String accountId;
	
	@Column(name = "account_type")
	private String accountType;
	private String location;
	private Double balance;
	private Date creationDate;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	        name = "customer_account", 
	        joinColumns = { @JoinColumn(name = "customer_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "account_id") })
	private List<Customer> customer;
	
	public List<Customer> getCustomer() {
		return customer;
	}
	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
	public Account() {}
	public Account(String accountId, String accountType, String location, Double balance, Date creationDate,List<Customer> customer) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.location = location;
		this.balance = balance;
		this.creationDate = creationDate;
		this.customer=customer;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	} 
	
}
