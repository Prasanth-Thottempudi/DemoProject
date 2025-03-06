package com.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.request.AddServiceRequest;
import com.services.response.Response;
import com.services.service.Services;

@RestController
@RequestMapping("/services")
public class ServicesController {
	
	@Autowired
	private Services service;
	
	@GetMapping("/dashboard")
	public String services() {
		return "services controller";
	}
	
	@PostMapping("/add-service")
	public ResponseEntity<Response> addService(@RequestBody AddServiceRequest request){
		Response addServiceResponse = service.addService(request);
		return ResponseEntity.ok(addServiceResponse);
	}

}
