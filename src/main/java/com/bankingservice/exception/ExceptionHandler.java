package com.bankingservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(CustomerNotFoundExcpetion.class)
	public ResponseEntity<ExceptionModel> customExceptionHandler(CustomerNotFoundExcpetion ex,WebRequest req)
	{
		ExceptionModel em=new ExceptionModel(new Date(),ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<ExceptionModel>(em,HttpStatus.CONFLICT);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(AccountNotFoundExcpetion.class)
	public ResponseEntity<ExceptionModel> customExceptionHandler(AccountNotFoundExcpetion ex,WebRequest req)
	{
		ExceptionModel em=new ExceptionModel(new Date(),ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<ExceptionModel>(em,HttpStatus.CONFLICT);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MaximumNumberOfOwnersReachedExcpetion.class)
	public ResponseEntity<ExceptionModel> customExceptionHandler(MaximumNumberOfOwnersReachedExcpetion ex,WebRequest req)
	{
		ExceptionModel em=new ExceptionModel(new Date(),ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<ExceptionModel>(em,HttpStatus.CONFLICT);
	}
	
}
