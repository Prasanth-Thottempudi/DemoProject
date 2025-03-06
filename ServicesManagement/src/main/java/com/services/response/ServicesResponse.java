package com.services.response;

import lombok.Data;

@Data
public class ServicesResponse {
	
	private Long serviceId;
	public String serviceName;
	private String serviceDescription;
	private String serviceImage;
	private String serviceImageName;

}
