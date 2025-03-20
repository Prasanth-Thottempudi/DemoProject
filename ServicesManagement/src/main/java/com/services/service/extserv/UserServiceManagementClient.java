package com.services.service.extserv;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.services.request.EmailDetails;


@FeignClient(name = "USERMANAGEMENT")
public interface UserServiceManagementClient {
	
	@PostMapping("/user/email/sendMail")
	public String sendMail(@RequestBody EmailDetails details) ;

}
