package br.com.curso.product.validation;

import org.springframework.http.HttpStatus;
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



}