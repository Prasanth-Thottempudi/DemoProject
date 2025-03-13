package com.services.service;

import com.services.request.AddBusinessPatnerRequest;
import com.services.service.response.AddBusinessPatnerResponse;

public interface BusinessPatnerService {
	
	public AddBusinessPatnerResponse addBusinessPatner(AddBusinessPatnerRequest request);

}
