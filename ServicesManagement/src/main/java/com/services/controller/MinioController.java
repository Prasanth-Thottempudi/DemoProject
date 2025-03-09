package com.services.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.services.service.MinioServices;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@RestController
@RequestMapping("/service/minio")
public class MinioController {

	@Autowired
	private MinioServices  minioServices;
	
	@PostMapping("/upload")
	public ResponseEntity<Boolean> uploadImage(@RequestParam("image") MultipartFile image, 
	                                           @RequestParam("fileName") String fileName) {
	    boolean saveImage = false;
	    try {
	        saveImage = minioServices.saveImage(image, fileName);
	        return ResponseEntity.ok(saveImage);
	    } catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
	            | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
	            | IllegalArgumentException | IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);  // Return false if an error occurs
	    }
	}

	
}
