package com.services.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.services.dao.BusinessPatnerDao;
import com.services.entity.BusinessPatnerEntity;
import com.services.request.AddBusinessPatnerRequest;
import com.services.service.BusinessPatnerService;
import com.services.service.response.AddBusinessPatnerResponse;

@Service
public class BusinessPatnerServiceImpl implements BusinessPatnerService {
	
	@Autowired
	private BusinessPatnerDao businessPatnerDao;

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

}
