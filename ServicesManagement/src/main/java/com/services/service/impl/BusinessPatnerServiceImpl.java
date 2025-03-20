package com.services.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.services.dao.BusinessPatnerDao;
import com.services.entity.BusinessPatnerEntity;
import com.services.request.AddBusinessPatnerRequest;
import com.services.request.ApprovalRequest;
import com.services.request.EmailDetails;
import com.services.response.ApprovalResponse;
import com.services.response.RequestedBusinessApprovalsResponse;
import com.services.service.BusinessPatnerService;
import com.services.service.extserv.UserServiceManagementClient;
import com.services.service.response.AddBusinessPatnerResponse;

@Service
public class BusinessPatnerServiceImpl implements BusinessPatnerService {
	
	@Autowired
	private BusinessPatnerDao businessPatnerDao;
	
	@Autowired
	private UserServiceManagementClient userServiceManagementClient;
	

	@Override
	public AddBusinessPatnerResponse addBusinessPatner(AddBusinessPatnerRequest request) {
		AddBusinessPatnerResponse response=new AddBusinessPatnerResponse();
		BusinessPatnerEntity entity=new BusinessPatnerEntity();
		BeanUtils.copyProperties(request, entity);
		entity.setStatus("pending");
		entity.setOffline(Boolean.TRUE);
		BusinessPatnerEntity savedBusinessPatnerEntity = businessPatnerDao.save(entity);
		response.setBusinessPatnerid(savedBusinessPatnerEntity.getBusinessId());
		response.setResponseMessage("business registered successfully");
		response.setResponseStatusCode("1");
		return response;
	}

	@Override
	public List<RequestedBusinessApprovalsResponse> getAllPendingBusinesses() {
	    List<BusinessPatnerEntity> allPendingBusinesses = businessPatnerDao.findAll();
	    List<RequestedBusinessApprovalsResponse> pendingBusinessesList = new ArrayList<>();
	    
	    allPendingBusinesses.stream()
	    .filter(i->i.getStatus().equalsIgnoreCase("pending"))
	    .forEach(i -> {
	        RequestedBusinessApprovalsResponse pendingBusinesses = new RequestedBusinessApprovalsResponse();
	        pendingBusinesses.setId(i.getBusinessId());
	        pendingBusinesses.setBusinessProofUrl(i.getBusinessLicenseProofUrl());
	        pendingBusinesses.setBusinessLogoUrl(i.getBusinessLogoUrl());
	        pendingBusinesses.setBusinessMobileNumber(i.getBusinessMobileNumber());
	        pendingBusinesses.setBusinessEmail(i.getBusinessEmail());
	        pendingBusinesses.setBusinessName(i.getBusinessName());
	        pendingBusinesses.setBusinessRegion(i.getBusinessRegion());
	        pendingBusinesses.setBusinessContact(i.getBusinessMobileNumber());
	        pendingBusinesses.setServiceName(i.getServiceName());
	        
	        pendingBusinessesList.add(pendingBusinesses);
	    });
	    
	    return pendingBusinessesList;
	}

	@Override
	public ApprovalResponse approveServiceRequest(ApprovalRequest request) {
		
		ApprovalResponse response=new ApprovalResponse();
		BusinessPatnerEntity businessPatnerEntity = businessPatnerDao.getById(request.getId());
		if(!request.getApproval()) {
			businessPatnerEntity.setStatus("rejected");
		}else {
			businessPatnerEntity.setStatus("approved");
		}
		BusinessPatnerEntity  businessEntity= businessPatnerDao.save(businessPatnerEntity);
		if(businessEntity.getStatus().equalsIgnoreCase("approved")) {
			EmailDetails req=new EmailDetails();
			req.setSubject("Business approval request");
			req.setMsgBody("business request got approved ");
			req.setRecipient("tprasanth678@gmail.com");
			userServiceManagementClient.sendMail(req);
		}
		response.setId(businessEntity.getBusinessId());
		response.setResponseMessage("approved successfully");
		response.setResponseStatus("0");
		return response;
	}


}
