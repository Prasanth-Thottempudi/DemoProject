package com.services.service.response;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private String errorMessage;
	private Integer statusCode;

}
