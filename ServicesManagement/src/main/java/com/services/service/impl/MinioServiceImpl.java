package com.services.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.services.config.MinioConfig;
import com.services.service.MinioServices;

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

@Service
public class MinioServiceImpl implements MinioServices {

	@Autowired
	private MinioConfig minioConfig;

	

	@Override
	public boolean uploadImage(MultipartFile image) {
		return false;
	}

	@Override
	public String getImageUrl(String fileName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, XmlParserException, ServerException, IllegalArgumentException, IOException {
		MinioClient minioClient = minioConfig.minioClient();
		String presignedUrl = minioClient.getPresignedObjectUrl(
		        GetPresignedObjectUrlArgs.builder()
		                .bucket("nexgenhub")
		                .object(fileName)
		                .method(Method.GET)
		                .expiry(2, TimeUnit.HOURS) 
		                .build());
		return presignedUrl;
	}

	@Override
	public boolean saveImage(MultipartFile image,String fileName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		MinioClient minioClient = minioConfig.minioClient();
		String bucketName = "nexgenhub";
		boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
		if (!found) {
			minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
		}
		InputStream inputStream = image.getInputStream();
		minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(fileName)
				.stream(inputStream, image.getSize(), -1).contentType(image.getContentType()).build());
		return false;
	}

}
