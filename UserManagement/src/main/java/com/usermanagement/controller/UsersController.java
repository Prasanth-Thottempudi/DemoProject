package com.usermanagement.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.request.UserRegistrationRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UsersController {

	
	
	 @PostMapping("/register")
	    public ResponseEntity<?> registerUser(
	            @Valid @RequestBody UserRegistrationRequest userRegistrationRequest,
	            BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            Map<String, String> errors = new HashMap<>();
	            bindingResult.getFieldErrors().forEach(error -> {
	                errors.put(error.getField(), error.getDefaultMessage());
	            });
	            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	    }
}
