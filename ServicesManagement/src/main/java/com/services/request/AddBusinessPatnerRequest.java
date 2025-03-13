package com.services.request;

import lombok.Data;

@Data
public class AddBusinessPatnerRequest {
	
	private String serviceName;
	private String businessName;
	private String businessEmail;
	private String businessMobileNumber;
	private String businessLogoUrl;
	private String businessRegion;
	private String businessLicenseProofUrl;
	private String language;
	private String emergenceyContact;
	private String businessDescription;
	
	
	
	

}
