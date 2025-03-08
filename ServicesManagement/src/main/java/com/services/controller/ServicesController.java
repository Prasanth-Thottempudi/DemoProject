package com.services.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.config.MinioConfig;
import com.services.request.AddServiceRequest;
import com.services.response.Response;
import com.services.response.ServicesResponse;
import com.services.service.Services;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;

@RestController
@RequestMapping("/services")
public class ServicesController {
	
	@Autowired
	private Services service;
	
	@Autowired
	private  MinioConfig minioConfig;
	
	@GetMapping("/dashboard")
	public String services() throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		MinioClient minioClient = minioConfig.minioClient();

		    boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("nexgenhub").build());
		    if (!found) {
		        minioClient.makeBucket(MakeBucketArgs.builder().bucket("nexgenhub").build());
		    } else {
		        System.out.println("Bucket 'nexgenhub' already exists.");
		    }
		return "services controller";
	}
	
	@PostMapping("/add-service")
	public ResponseEntity<Response> addService(@RequestParam("serviceImage") MultipartFile serviceImage,@RequestParam("data") String data) throws IOException, InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException{
		ObjectMapper objectMapper = new ObjectMapper();
	    AddServiceRequest request = objectMapper.readValue(data, AddServiceRequest.class);
		Response addServiceResponse = service.addService(request,serviceImage);
		return ResponseEntity.ok(addServiceResponse);
	}
	
	
	
	@GetMapping("/get-all-services")
	public ResponseEntity<List<ServicesResponse>> getAllServices(){
		List<ServicesResponse> allServices = service.findAllServices();
		return ResponseEntity.ok(allServices);
	}
	
	@GetMapping("/view-file")
    public String viewFile(@RequestParam String fileName) throws InvalidKeyException, ErrorResponseException,
            InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException,
            ServerException, XmlParserException, IllegalArgumentException, IOException {
		
		MinioClient minioClient = minioConfig.minioClient();

	    boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("nexgenhub").build());
	    if (!found) {
	        minioClient.makeBucket(MakeBucketArgs.builder().bucket("nexgenhub").build());
	    } else {
	        System.out.println("Bucket 'nexgenhub' already exists.");
	    }
        String presignedUrl = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket("nexgenhub")
                        .object(fileName)
                        .method(Method.GET)
                        .expiry(2, TimeUnit.HOURS) 
                        .build()
        );

        return "View the file here: " + presignedUrl;
    }
	
	@PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("bucketName") String bucketName) {
        try {
    		MinioClient minioClient = minioConfig.minioClient();

            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            // Upload the file
            String fileName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            return ResponseEntity.ok("File uploaded successfully: " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
	

	
}
