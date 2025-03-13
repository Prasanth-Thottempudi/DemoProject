package com.usermanagement.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.usermanagement.config.FeignConfig;
import com.usermanagement.request.AddBusinessPatnerRequest;
import com.usermanagement.response.AddBusinessPatnerResponse;
import com.usermanagement.response.MinioServiceResponse;
import com.usermanagement.response.Response;

@FeignClient(name = "SERVICESMANAGEMENT")
public interface ServiceClient {

//	@PostMapping("/service/minio/upload")
//	public ResponseEntity<Boolean> uploadImage(@RequestParam("image") MultipartFile image, 
//            @RequestParam("fileName") String fileName);
	
	@GetMapping("/services/dashboard")
	public String getDashboard();
	
	
	@GetMapping("/services/get-image/{fileName}")
	public ResponseEntity<MinioServiceResponse> getImage(@PathVariable String fileName);
	
	@PostMapping("/services/business/add-business-patner")
	public  ResponseEntity<AddBusinessPatnerResponse> addBusinessPatner(@RequestBody AddBusinessPatnerRequest request);
	
	@DeleteMapping("/service/minio/delete-image/{fileName}")
	public ResponseEntity<Response> deleteImage(@PathVariable String fileName);
	
	@PostMapping(value="/service/minio/upload-image")
	public ResponseEntity<MinioServiceResponse> uploadImage(@RequestParam("file") MultipartFile file,@RequestParam("fileName") String fileName);

}
