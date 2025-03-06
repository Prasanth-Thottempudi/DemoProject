package com.services.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.services.service.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ServiceAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> serviceAlreadyExistsExceptionHandler(ServiceAlreadyExistsException ex,WebRequest request){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorMessage(ex.getMessage());
		errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
		return ResponseEntity.ok(errorResponse);
	}

}
