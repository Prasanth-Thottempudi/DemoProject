package com.services.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity 
@Table(name="business_patner_details")
public class BusinessPatnerEntity {
	
	@Id
	  @GeneratedValue(generator = "business-patner-id-generator")
    @GenericGenerator(
        name = "business-patner-id-generator", 
        strategy = "com.services.idgenerator.BusinessPatnerIdGenerator"  // Ensure this is the correct fully qualified path
    )
    @Column(name = "business_id", updatable = false, nullable = false)
	private String businessId;

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
	
	private String status;
	private Boolean online;
	private Boolean offline;
}
