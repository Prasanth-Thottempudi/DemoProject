package com.services.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.services.request.AddServiceRequest;
import com.services.response.Response;
import com.services.response.ServicesResponse;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

public interface Services {
	
	public Response addService(AddServiceRequest request,MultipartFile serviceImage) throws IOException, InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException;

	public List<ServicesResponse> findAllServices();

}
