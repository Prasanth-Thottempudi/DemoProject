package com.services.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.services.dao.ServicesDao;
import com.services.entity.ServicesEntity;
import com.services.request.AddServiceRequest;
import com.services.response.Response;
import com.services.service.Services;
import com.services.service.exceptions.ServiceAlreadyExistsException;

@Service
public class ServicesImpl implements Services{
	
	@Autowired
	private ServicesDao servicesDao;

	@Override
	public Response addService(AddServiceRequest request) {
		ServicesEntity servicesEntity=new ServicesEntity();
		BeanUtils.copyProperties(request, servicesEntity);
		
		Boolean existsByServiceName = servicesDao.existsByServiceName(request.getServiceName());
		if(existsByServiceName) {
			throw new ServiceAlreadyExistsException("service is already taken!");
		}
		ServicesEntity servicesSaved = servicesDao.save(servicesEntity);
		if(servicesSaved.getServiceId()>0L) {
			
		}
		return null;
	}

}
