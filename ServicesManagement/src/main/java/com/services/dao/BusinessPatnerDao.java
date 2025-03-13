package com.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.entity.BusinessPatnerEntity;

public interface BusinessPatnerDao extends JpaRepository<BusinessPatnerEntity, String> {

}
