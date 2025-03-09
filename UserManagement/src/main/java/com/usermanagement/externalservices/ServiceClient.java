package com.usermanagement.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "SERVICESMANAGEMENT")
public interface ServiceClient {

	
	@PostMapping("/service/minio/upload")
	public ResponseEntity<Boolean> uploadImage(@RequestParam("image") MultipartFile image, 
            @RequestParam("fileName") String fileName);
	}
