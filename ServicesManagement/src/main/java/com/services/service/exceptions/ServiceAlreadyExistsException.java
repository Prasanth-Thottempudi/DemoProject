package com.services.service.exceptions;

public class ServiceAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceAlreadyExistsException(String msg){
		super(msg);
	}

}
