package com.usermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagement.entity.UserDetailsEntity;

public interface UserDetailsDao extends  JpaRepository<UserDetailsEntity, String> {

	Boolean existsByEmail(String email);

	Boolean existsByPhoneNumber(String email);

}
