package com.bankingservice.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingservice.dto.AccountRequestDto;
import com.bankingservice.dto.CustomerRequestDto;
import com.bankingservice.dto.UpdateDto;
import com.bankingservice.exception.CustomerNotFoundExcpetion;
import com.bankingservice.exception.MaximumNumberOfOwnersReachedExcpetion;
import com.bankingservice.model.Account;
import com.bankingservice.model.Customer;
import com.bankingservice.repository.AccountRepository;
import com.bankingservice.repository.CustomerRepository;

@Service
public class BankingService {

	@Autowired
	AccountRepository accRepo;
	
	@Autowired
	CustomerRepository customRepo;
	
	Account generateAccountId(Account acc)
	{
		long c=accRepo.count()+1;
		String aid=acc.getLocation().substring(0,3).toUpperCase()+
				String.format("%09d", c);
		acc.setAccountId(aid);
		return acc;
	}
	
	Customer generateCustomerId(Customer customer)
	{
		long c=customRepo.count()+1;
		String cid=customer.getCustomerName().substring(0,2).toUpperCase()+customer.getCity().substring(0,2).toUpperCase()+
				String.format("%09d", c);
		customer.setCustomerId(cid);
		return customer;
	}
	
	public Account createAccount(AccountRequestDto ac) throws CustomerNotFoundExcpetion
	{
		Customer customer=customRepo.findById(ac.getCustomerId()).orElseThrow(
			()->new CustomerNotFoundExcpetion("This customer id is invalid"));
		
		List<Account> acc=customer.getAccount();
		acc.add(this.generateAccountId(ac.getAcc()));
		customer.setAccount(acc);
		customRepo.save(customer);
		return ac.getAcc();
	}
	
	public Customer addCustomer(CustomerRequestDto customerRequest) throws AccountNotFoundException, MaximumNumberOfOwnersReachedExcpetion
	{
		Account acc=accRepo.findById(customerRequest.getAccountId()).orElseThrow(()->
		new AccountNotFoundException("There no account with such accountId"));
		List<Customer> cus=acc.getCustomer();
		if(cus.size()>2)
			throw new MaximumNumberOfOwnersReachedExcpetion("no more users can be added");
		cus.add(this.generateCustomerId(customerRequest.getCustomer()));
		acc.setCustomer(cus);
		acc.setAccountType("Joint");
		accRepo.save(acc);
		return customerRequest.getCustomer();
	}
	
	public Customer addCustomer(Customer custom)
	{
		
		List<Account> acc=custom.getAccount();
		List<Account> newacc=new ArrayList<Account>();
		for(Account a:acc)
		{
			newacc.add(this.generateAccountId(a));
		}
		custom.setAccount(newacc);
		return customRepo.save(this.generateCustomerId(custom));
	}
	
	public Customer updateCustomer(UpdateDto ud) throws CustomerNotFoundExcpetion
	{
		Customer customer=customRepo.findById(ud.getId()).orElseThrow(
				()->new CustomerNotFoundExcpetion("This customer id is invalid"));
		if(ud.getField().equalsIgnoreCase("customerName"))
			customer.setCustomerName(ud.getNewval());
		else if(ud.getField().equalsIgnoreCase("gender"))
			customer.setGender(ud.getNewval());
		else if(ud.getField().equalsIgnoreCase("pincode"))
			customer.setPinCode(Integer.parseInt(ud.getNewval()));
		else if(ud.getField().equalsIgnoreCase("dateofbirth"))
			customer.setDateOfBirth(Date.valueOf(ud.getNewval()));
		else if(ud.getField().equalsIgnoreCase("city"))
			customer.setCity(ud.getNewval());
		
		return customRepo.save(customer);
	}
	
	public Account updateAccount(UpdateDto ud) throws AccountNotFoundException
	{
		Account acc=accRepo.findById(ud.getId()).orElseThrow(()->
		new AccountNotFoundException("There no account with such accountId"));
		if(ud.getField().equalsIgnoreCase("accountType"))
			acc.setAccountType(ud.getNewval());
		else if(ud.getField().equalsIgnoreCase("balance"))
			acc.setBalance(Double.parseDouble(ud.getNewval()));
		else if(ud.getField().equalsIgnoreCase("creationdate"))
			acc.setCreationDate(Date.valueOf(ud.getNewval()));
		else if(ud.getField().equalsIgnoreCase("location"))
			acc.setLocation(ud.getNewval());
		return accRepo.save(acc);
	}
	
	public Customer getCustomer(String customerId) throws CustomerNotFoundExcpetion
	{
		return customRepo.findById(customerId).orElseThrow(
				()->new CustomerNotFoundExcpetion("This customer id is invalid"));
	}
	
	public List<Account> getAllAccount(String customerId) throws CustomerNotFoundExcpetion{
		return customRepo.findById(customerId).orElseThrow(
				()->new CustomerNotFoundExcpetion("This customer id is invalid")).getAccount();
	}
	
	public Account getAccount(String accountId) throws AccountNotFoundException
	{
		Account acc=accRepo.findById(accountId).orElseThrow(()->
		new AccountNotFoundException("There no account with such accountId"));
		return acc;
	}
	
	public void deleteCustomer(String customerId) throws CustomerNotFoundExcpetion
	{
		
		List<Account> acc=customRepo.findById(customerId).orElseThrow(
				()->new CustomerNotFoundExcpetion("This customer id is invalid")).getAccount();
		acc.forEach(x->accRepo.deleteById(x.getAccountId()));
		customRepo.deleteById(customerId);
	}
	
	public void deleteAccount(String accountId)
	{
		accRepo.deleteById(accountId);
	}
	
}
