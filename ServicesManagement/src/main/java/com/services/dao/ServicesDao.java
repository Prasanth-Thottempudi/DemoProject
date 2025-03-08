package com.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.entity.ServicesEntity;

public interface ServicesDao extends JpaRepository<ServicesEntity, String>{

	Boolean existsByServiceName(String serviceName);

}
