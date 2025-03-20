package com.usermanagement.response;

import lombok.Data;

@Data
public class AuthenticationResponse {

	private String roleName;
	private Boolean loginApproval;
}
