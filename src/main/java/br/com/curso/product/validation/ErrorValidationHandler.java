package br.com.curso.product.validation;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.curso.product.exception.NotFoundException;


@RestControllerAdvice
public class ErrorValidationHandler {
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorValidationProduct handleBadRequest(MethodArgumentNotValidException exception) {
		return new ErrorValidationProduct(HttpStatus.BAD_REQUEST.value(),exception.getBindingResult().getFieldError().getDefaultMessage());
	}

	
	  @ResponseStatus(code = HttpStatus.NOT_FOUND) 
	  @ExceptionHandler(NotFoundException.class) 
	  ResponseEntity<?> handleNotFound(NotFoundException e) { 
		  return ResponseEntity.notFound().build(); 
		  }
	 
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED) 
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ErrorValidationProduct handleNotAllowed(HttpRequestMethodNotSupportedException exception) {
		return new ErrorValidationProduct(HttpStatus.METHOD_NOT_ALLOWED.value(),exception.getMessage());
	}
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public ErrorValidationProduct handleNullPointer(NullPointerException exception) {
		return new ErrorValidationProduct(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage());
	}
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorValidationProduct handleExeception(Exception exception) {
		return new ErrorValidationProduct(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage());
	}
	
	
}
