package com.admin.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="USERMANAGEMENT")
public interface UserManagementClient {

	@GetMapping("/user/admin/dashboard")
	public String getDashboard();
	
}
