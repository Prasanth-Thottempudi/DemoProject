package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.externalservices.UserManagementClient;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserManagementClient userManagementClient;

	
	@GetMapping("/dashboard")
	public String getDas() {
		return userManagementClient.getDashboard();
	}
}
