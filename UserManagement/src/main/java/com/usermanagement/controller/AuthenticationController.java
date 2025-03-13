package com.usermanagement.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.request.AuthenticationDetailsRequest;

@RestController
@RequestMapping("/user/auth")
@CrossOrigin("*")
public class AuthenticationController {
	
	@PostMapping("/login")
	public Boolean login(@RequestBody AuthenticationDetailsRequest request ) {
		return true;
	}

}
