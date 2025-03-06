package com.services.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.services.request.AddServiceRequest;
import com.services.response.Response;
import com.services.response.ServicesResponse;

public interface Services {
	
	public Response addService(AddServiceRequest request,MultipartFile serviceImage) throws IOException;

	public List<ServicesResponse> findAllServices();

}
