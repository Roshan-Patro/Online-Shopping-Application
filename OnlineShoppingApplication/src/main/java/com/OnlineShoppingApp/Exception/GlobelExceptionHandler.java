package com.OnlineShoppingApp.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobelExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> myHandler2(Exception e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> myHandler3(NoHandlerFoundException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AddressException.class)
	public ResponseEntity<ErrorDetails> myHandler1(AddressException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CartException.class)
	public ResponseEntity<ErrorDetails> myHandler4(CartException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<ErrorDetails> myHandler5(CategoryException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetails> myHandler5(CustomerException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ErrorDetails> myHandler6(OrderException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorDetails> myHandler7(ProductException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CartProductException.class)

	public ResponseEntity<ErrorDetails> myHandler8(CartProductException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ErrorDetails> myHandler9(AdminException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorDetails> myHandler10(LoginException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
}
