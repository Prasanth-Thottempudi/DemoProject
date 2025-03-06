package com.services.service;

import com.services.request.AddServiceRequest;
import com.services.response.Response;

public interface Services {
	
	public Response addService(AddServiceRequest request);

}
