package com.bankingservice.dto;

import com.bankingservice.model.Customer;

public class CustomerRequestDto {
	private String AccountId;
	private Customer customer;
	public CustomerRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerRequestDto(String accountId, Customer customer) {
		super();
		AccountId = accountId;
		this.customer = customer;
	}
	public String getAccountId() {
		return AccountId;
	}
	public void setAccountId(String accountId) {
		AccountId = accountId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
