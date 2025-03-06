package com.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.entity.ImageEntity;

public interface ImageDao extends JpaRepository<ImageEntity, Integer> {

}
