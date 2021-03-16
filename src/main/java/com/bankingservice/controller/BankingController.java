package com.bankingservice.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankingservice.dto.AccountRequestDto;
import com.bankingservice.dto.CustomerRequestDto;
import com.bankingservice.dto.ResponseDto;
import com.bankingservice.dto.UpdateDto;
import com.bankingservice.exception.CustomerNotFoundExcpetion;
import com.bankingservice.exception.MaximumNumberOfOwnersReachedExcpetion;
import com.bankingservice.model.Account;
import com.bankingservice.model.Customer;
import com.bankingservice.service.BankingService;

@RestController
public class BankingController {
	
	@Autowired
	BankingService banks;

	@GetMapping("/test")
	public String getMsg()
	{
		return "welcome to our banking service";
	}
	
	@PostMapping("/createcustomer")
	public ResponseEntity<ResponseDto> createCustomer( @RequestBody Customer customer)
	{
		Customer custom=banks.addCustomer(customer);
		ResponseDto res=new ResponseDto(custom.getCustomerId(), custom.getCity(), custom.getCustomerName(), custom.getGender());
		return new  ResponseEntity<ResponseDto>(res,HttpStatus.CREATED);
	}
	
	@PostMapping("/addcustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody CustomerRequestDto cdto) throws AccountNotFoundException, MaximumNumberOfOwnersReachedExcpetion
	{
		return new  ResponseEntity<Customer>(banks.addCustomer(cdto),HttpStatus.CREATED);
	}
	
	@PostMapping("/addaccount")
	public ResponseEntity<Account> addAccount(@RequestBody AccountRequestDto adto) throws CustomerNotFoundExcpetion
	{
		return new  ResponseEntity<Account>(banks.createAccount(adto),HttpStatus.CREATED);

	}
	
	@PutMapping("/updateaccount")
	public Account updateAccount(@RequestBody UpdateDto dto) throws AccountNotFoundException
	{
		return banks.updateAccount(dto);
	}
	
	@PutMapping("/updatecustomer")
	public Customer updateCustomer(@RequestBody UpdateDto dto) throws CustomerNotFoundExcpetion
	{
		return banks.updateCustomer(dto);
	}
	
	@GetMapping("/getcustomer/{id}")
	public Customer getCustomer(@PathVariable String id) throws CustomerNotFoundExcpetion
	{
		return banks.getCustomer(id);
	}
	
	@GetMapping("/getaccount/{id}")
	public Account getAccount(@PathVariable String id) throws AccountNotFoundException
	{
		return banks.getAccount(id);
	}
	
	@GetMapping("/getallaccount/{id}")
	public List<Account> getAllAccount(@PathVariable String id) throws CustomerNotFoundExcpetion
	{
		return banks.getAllAccount(id);
	}
	
	@DeleteMapping("/deletecustomer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable String id) throws CustomerNotFoundExcpetion
	{
		banks.deleteCustomer(id);
		return new ResponseEntity<String>("customer is deleted",HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteaccount/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable String id) throws CustomerNotFoundExcpetion
	{
		banks.deleteAccount(id);
		return new ResponseEntity<String>("account is deleted",HttpStatus.ACCEPTED);
	}
	
}

