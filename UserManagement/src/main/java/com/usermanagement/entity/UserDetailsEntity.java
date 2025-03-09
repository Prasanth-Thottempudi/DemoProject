package com.usermanagement.entity;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="user_details")
@Entity
public class UserDetailsEntity {
	
	@Id
	@Column(name="user_id")
    @GeneratedValue(generator = "user-id-generator")
    @GenericGenerator(
        name = "user-id-generator", 
        strategy = "com.usermanagement.idgenerator.UserIdGenerator"  // Ensure this is the correct fully qualified path
    )
	private String userId;
	private String fullName;
	private String username;
	private String email;
	private String phoneNumber;
	private Date dateOfBirth;
	private String gender;
	private String Nationality;
	
	private String idProof;
	
	private String serviceBusinessId;
	
	private String roleId;
	

}