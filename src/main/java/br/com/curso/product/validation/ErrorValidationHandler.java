package br.com.curso.product.validation;


import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorValidationHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorValidationProduct handle(MethodArgumentNotValidException exception) {
		return new ErrorValidationProduct(HttpStatus.BAD_REQUEST.value(),exception.getBindingResult().getFieldError().getDefaultMessage());
	}
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public void handle(NoSuchElementException exception) {
	}
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED) 
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ErrorValidationProduct handle(HttpRequestMethodNotSupportedException exception) {
		return new ErrorValidationProduct(HttpStatus.METHOD_NOT_ALLOWED.value(),exception.getMessage());
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ErrorValidationProduct handle(NullPointerException exception) {
		return new ErrorValidationProduct(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ErrorValidationProduct handle(Exception exception) {
		return new ErrorValidationProduct(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage());
	}
	

}