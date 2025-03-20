package com.services.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.services.request.AddBusinessPatnerRequest;
import com.services.request.ApprovalRequest;
import com.services.response.ApprovalResponse;
import com.services.response.RequestedBusinessApprovalsResponse;
import com.services.service.response.AddBusinessPatnerResponse;

public interface BusinessPatnerService {
	
	public AddBusinessPatnerResponse addBusinessPatner(AddBusinessPatnerRequest request);

	public List<RequestedBusinessApprovalsResponse> getAllPendingBusinesses();

	public ApprovalResponse approveServiceRequest(ApprovalRequest request);

}
