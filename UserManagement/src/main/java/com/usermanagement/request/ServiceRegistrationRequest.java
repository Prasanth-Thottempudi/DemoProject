package com.usermanagement.request;

import lombok.Data;

@Data
public class ServiceRegistrationRequest {
	
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String serviceType;
	private String servicePointAddress;
	
}
