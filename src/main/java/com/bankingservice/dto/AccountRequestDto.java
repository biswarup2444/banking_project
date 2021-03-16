package com.bankingservice.dto;

import com.bankingservice.model.Account;

public class AccountRequestDto {
	private String customerId;
	private Account acc;
	public AccountRequestDto() {}
	public AccountRequestDto(String customerId, Account acc) {
		super();
		this.customerId = customerId;
		this.acc = acc;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Account getAcc() {
		return acc;
	}
	public void setAcc(Account acc) {
		this.acc = acc;
	}
	
	
}
