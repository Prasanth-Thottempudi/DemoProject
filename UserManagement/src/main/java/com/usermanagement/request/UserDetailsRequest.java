package com.usermanagement.request;

import java.util.Date;

import lombok.Data;

@Data
public class UserDetailsRequest extends ServiceBusinessDetailsRequest {
	
	private String fullName;
	private String username;
	private String email;
	private String phoneNumber;
	private Date dateOfBirth;
	private String gender;
	private String Nationality;
	private String idProof;

}
