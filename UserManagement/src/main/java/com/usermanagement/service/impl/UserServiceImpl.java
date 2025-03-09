package com.usermanagement.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermanagement.dao.UserDetailsDao;
import com.usermanagement.entity.UserDetailsEntity;
import com.usermanagement.exceptions.UserAlreadyExistsException;
import com.usermanagement.request.UserDetailsRequest;
import com.usermanagement.response.Response;
import com.usermanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Override
	public Response addUser(UserDetailsRequest request) {
		
		Response response = new Response();
		boolean isEmailFound = userDetailsDao.existsByEmail(request.getEmail());
		if (isEmailFound) {
			throw new UserAlreadyExistsException("user is already existed with given email" + request.getEmail());
		}
		UserDetailsEntity userDetailsEntity=new UserDetailsEntity();
		
		BeanUtils.copyProperties(request, userDetailsEntity);
		userDetailsDao.save(userDetailsEntity);
		
//		userDetailsDao.existsByPhoneNumber(request.getEmail());
		return response;
	}

	@Override
	public Response addBusiness(UserDetailsRequest request) {
		Response response=new Response();
		UserDetailsEntity userDetailsEntity=new UserDetailsEntity();
		userDetailsEntity.setFullName(request.getFullName());
		userDetailsEntity.setEmail(request.getEmail());
		userDetailsEntity.setDateOfBirth(request.getDateOfBirth());
		userDetailsEntity.setGender(request.getGender());
//		userDetailsEntity.setIdProof(null);
		userDetailsEntity.setNationality(request.getNationality());
		userDetailsEntity.setPhoneNumber(request.getPhoneNumber());
//		userDetailsEntity.setRoleId(request.get);
		userDetailsEntity.setUsername(request.getUsername());
		
		
		return response;
	}

}
