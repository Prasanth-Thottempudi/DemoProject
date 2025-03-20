package com.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.request.AddBusinessPatnerRequest;
import com.services.request.ApprovalRequest;
import com.services.response.ApprovalResponse;
import com.services.response.RequestedBusinessApprovalsResponse;
import com.services.service.BusinessPatnerService;
import com.services.service.response.AddBusinessPatnerResponse;

@RestController
@RequestMapping("/services/business")
public class BusinessController {
	
	@Autowired
	private BusinessPatnerService businessPatnerService;
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "business dashboard";
	}
	

	@PostMapping("/add-business-patner")
	public  ResponseEntity<AddBusinessPatnerResponse> addBusinessPatner(@RequestBody AddBusinessPatnerRequest request){
		AddBusinessPatnerResponse businessPatner = businessPatnerService.addBusinessPatner(request);
		return ResponseEntity.ok(businessPatner);
	}
	
	
	@GetMapping("/approval-requested-services")
	public ResponseEntity<List<RequestedBusinessApprovalsResponse>> getAllPendingBusiness(){
		 List<RequestedBusinessApprovalsResponse> allPendingBusinesses = businessPatnerService.getAllPendingBusinesses();
		 return ResponseEntity.ok(allPendingBusinesses);
	}
	
	@PutMapping("/business-approval-request")
	public ResponseEntity<ApprovalResponse> appovalRequest(@RequestBody ApprovalRequest request){
		ApprovalResponse response=businessPatnerService.approveServiceRequest(request);
		return ResponseEntity.ok(response);		
	}
}
