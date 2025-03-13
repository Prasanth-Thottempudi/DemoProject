package com.usermanagement.request;


import java.util.Date;
import lombok.Data;

@Data
public class UserDetailsAndBusinessRequest {

    // User details
    private String fullName;
//    private String username;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private String gender;
    private String nationality;

    // Business details
    private String serviceName;
    private String businessName;
    private String businessEmail;
    private String businessMobileNumber;
    private String businessRegion;
    private String language;
    private String emergencyContact;
    private String businessDescription;

    // Files/URLs for business logo and license proof (if needed in future)
    // private String businessLogoUrl;
    // private String businessLicenseProof;
}
