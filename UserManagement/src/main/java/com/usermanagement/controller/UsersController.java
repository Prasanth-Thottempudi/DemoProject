package com.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.dao.RolesDao;
import com.usermanagement.entity.RolesEntity;
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

	
	
//	 @PostMapping("/register")
//	    public ResponseEntity<?> registerUser(
//	            @Valid @RequestBody UserRegistrationRequest userRegistrationRequest,
//	            BindingResult bindingResult) {
//	        if (bindingResult.hasErrors()) {
//	            Map<String, String> errors = new HashMap<>();
//	            bindingResult.getFieldErrors().forEach(error -> {
//	                errors.put(error.getField(), error.getDefaultMessage());
//	            });
//	            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	        }
//	        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
//	    }
	 
	 
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
	 
	 
	 @PostMapping("/add-business")
	 public ResponseEntity<Response> addBusiness(@RequestBody UserDetailsRequest request){
		 Response response=
				 userService.addBusiness(request);
				 return ResponseEntity.ok(response);
	 }
	 
	 
	 
	 
	 
}
