package com.OnlineShoppingApp.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> anyExceptionHandler(Exception e, WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),"Validation Error..!",e.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AddressException.class)
	public ResponseEntity<ErrorDetails> addressExceptionHandler(AddressException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<ErrorDetails> cartExceptionHandler(CartException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<ErrorDetails> categoryExceptionHandler(CategoryException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetails> customerExceptionHandler(CustomerException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OrdersException.class)
	public ResponseEntity<ErrorDetails> ordersExceptionHandler(OrdersException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorDetails> productExceptionHandler(ProductException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CartProductException.class)
	public ResponseEntity<ErrorDetails> cartProductExceptionHandler(CartProductException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ErrorDetails> adminExceptionHandler(AdminException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(LoginLogoutException.class)
	public ResponseEntity<ErrorDetails> loginLogoutException(LoginLogoutException e,WebRequest w){
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(),e.getMessage(),w.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}

	
	
	
}
