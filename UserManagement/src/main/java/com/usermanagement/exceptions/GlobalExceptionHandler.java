package com.usermanagement.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.usermanagement.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyException(UserAlreadyExistsException ex, WebRequest request) {
		
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.ALREADY_REPORTED.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setPath(request.getDescription(false));
        return ResponseEntity.ok(errorResponse);
        
    }
	
	
	@ExceptionHandler(BussinessPatnerException.class)
    public ResponseEntity<ErrorResponse> handleBusinessPatnerException(BussinessPatnerException ex, WebRequest request) {
		
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.ALREADY_REPORTED.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setPath(request.getDescription(false));
        return ResponseEntity.ok(errorResponse);
        
    }
}
