package com.services.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.request.AddServiceRequest;
import com.services.response.Response;
import com.services.response.ServicesResponse;
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
	public ResponseEntity<Response> addService(@RequestParam("serviceImage") MultipartFile serviceImage,@RequestParam("data") String data) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
	    AddServiceRequest request = objectMapper.readValue(data, AddServiceRequest.class);
	    request.setServiceImage(serviceImage.getBytes());
	    
		Response addServiceResponse = service.addService(request,serviceImage);
		return ResponseEntity.ok(addServiceResponse);
	}
	
	@GetMapping("/get-all-services")
	public ResponseEntity<List<ServicesResponse>> getAllServices(){
		List<ServicesResponse> allServices = service.findAllServices();
		return ResponseEntity.ok(allServices);
	}
	
//	@GetMapping("/get-all-types")
//	public ResponseEntity<List<String>> getAllServiceTypes(){
//		
//	}
	
}
