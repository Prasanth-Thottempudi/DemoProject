package com.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.dao.RolesDao;
import com.usermanagement.entity.RolesEntity;
import com.usermanagement.request.UserDetailsAndBusinessRequest;
import com.usermanagement.request.UserDetailsRequest;
import com.usermanagement.response.Response;
import com.usermanagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	private RolesDao rolesDao;
	
	@Autowired
	private UserService userService;
	


	
	

	 
	 
	 @PostMapping("/add-role")
	 public ResponseEntity<String> addRole(@RequestBody RolesEntity roles){
		 rolesDao.save(roles);
		 return ResponseEntity.ok("addd roles successfully");
	 }
	 
	 @PostMapping("/add-user")
	 public ResponseEntity<Response> addUser(@RequestBody UserDetailsRequest userDetailsRequest){
		 Response response=
		 userService.addUser(userDetailsRequest);
		 return ResponseEntity.ok(response);
	 }
	 
	 
	
	 @GetMapping("/user-dashboard")
	 public String getUserDashboard() {
		return  userService.getDashboard();
	 }
	 
	 


	 @PostMapping(value="/register-service-partner",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	 public ResponseEntity<Response> registerServicePartner(
	         @RequestParam("files") MultipartFile[] files, 
	         @RequestParam("data") String data) throws Exception {

	     MultipartFile serviceImage = files.length > 0 ? files[0] : null;
	     MultipartFile businessLogo = files.length > 1 ? files[1] : null;
	     MultipartFile businessLicenseProof = files.length > 2 ? files[2] : null;
	     ObjectMapper objectMapper = new ObjectMapper();
	     UserDetailsAndBusinessRequest req = objectMapper.readValue(data, UserDetailsAndBusinessRequest.class);
	     Response res = userService.addBusinessPatner(serviceImage, businessLogo, businessLicenseProof, req);

	     return ResponseEntity.ok(res);
	 }



	 
	 
}
