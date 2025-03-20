package com.fooddelivery.services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.multipart.MultipartFile;

import com.fooddelivery.response.MinioServiceResponse;
import com.fooddelivery.response.Response;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

public interface MinioServices {
	
	public boolean uploadImage(MultipartFile image);
	
	public MinioServiceResponse getImageUrl(String fileName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, XmlParserException, ServerException, IllegalArgumentException, IOException;
	
	public MinioServiceResponse saveImage(MultipartFile image,String fileName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException;

	public Response deleteImage(String fileName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException;
	

}
