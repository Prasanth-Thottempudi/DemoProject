package com.usermanagement.service;

import org.springframework.web.multipart.MultipartFile;

import com.usermanagement.request.UserDetailsAndBusinessRequest;
import com.usermanagement.request.UserDetailsRequest;
import com.usermanagement.response.Response;

public interface UserService {
	
	public Response addUser(UserDetailsRequest request);

	
	public String getDashboard();
	

	public Response addBusinessPatner(MultipartFile serviceImage, MultipartFile businessLogo,
			MultipartFile businessLicenseProof, UserDetailsAndBusinessRequest data);

}
