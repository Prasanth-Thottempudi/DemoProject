package com.usermanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/admin")
public class AdminServiceController {
	
	@GetMapping("/dashboard")
	public String getDashboard() {
		return "admin dashboard";
	}
	
	

}
