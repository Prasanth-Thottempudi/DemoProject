package com.usermanagement.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.request.AuthenticationDetailsRequest;
import com.usermanagement.response.AuthenticationResponse;

@RestController
@RequestMapping("/user/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody AuthenticationDetailsRequest request ) {
		AuthenticationResponse res=new AuthenticationResponse();
		res.setLoginApproval(true);
		res.setRoleName("USER");
		return res;
	}

}
