package com.usermanagement.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="roles_data")
@Entity
public class RolesEntity {
	@Id
	@Column(name="role_id")
    @GeneratedValue(generator = "role-id-generator")
    @GenericGenerator(
        name = "role-id-generator", 
        strategy = "com.usermanagement.idgenerator.RoleIdGenerator"  // Ensure this is the correct fully qualified path
    )
	private String roleId;
	
	@Column(name="role_name")
	private String roleName;

}
