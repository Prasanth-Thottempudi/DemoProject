package com.services.response;

import lombok.Data;

@Data
public class ServicesResponse {
	
	private String serviceId;
	public String serviceName;
	private String serviceDescription;
	private String serviceImageUrl;
	private String serviceImageName;

}
