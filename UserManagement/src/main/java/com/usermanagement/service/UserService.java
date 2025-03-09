package com.usermanagement.service;

import com.usermanagement.request.UserDetailsRequest;
import com.usermanagement.response.Response;

public interface UserService {
	
	public Response addUser(UserDetailsRequest request);

	public Response addBusiness(UserDetailsRequest request);

}
