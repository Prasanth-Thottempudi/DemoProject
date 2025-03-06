package com.services.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="services")
@Data
public class ServicesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serviceId;
	private String serviceName;
	private String serviceDescription;
	
	

}
