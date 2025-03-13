package com.services.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.services.response.MinioServiceResponse;
import com.services.response.Response;
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
	


	@PostMapping(value="/upload-image")
	public ResponseEntity<MinioServiceResponse> uploadImage(@RequestParam("file") MultipartFile file,@RequestParam("fileName") String fileName) {
		MinioServiceResponse response=new MinioServiceResponse();
		try {
			response=minioServices.saveImage(file, fileName);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/get-image/{fileName}")
	public ResponseEntity<MinioServiceResponse> getImage(@PathVariable String fileName) {
		MinioServiceResponse res=new MinioServiceResponse();
		try {
			res=minioServices.getImageUrl(fileName);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | XmlParserException | ServerException
				| IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(res);
	}
	
	@DeleteMapping("/delete-image/{fileName}")
	public ResponseEntity<Response> deleteImage(@PathVariable String fileName){
		Response res=new Response();
		try {
			fileName=null;
			res=minioServices.deleteImage(fileName);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(res);
	}
	
}
