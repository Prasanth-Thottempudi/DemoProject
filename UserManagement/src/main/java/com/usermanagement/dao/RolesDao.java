package com.usermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagement.entity.RolesEntity;

public interface RolesDao  extends JpaRepository<RolesEntity, String>{

}
