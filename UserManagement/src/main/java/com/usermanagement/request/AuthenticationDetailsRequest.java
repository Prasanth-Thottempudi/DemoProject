package com.usermanagement.request;

import lombok.Data;

@Data
public class AuthenticationDetailsRequest {
	private String username;
	private String password;

}
