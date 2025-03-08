package com.services.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.services.config.MinioConfig;
import com.services.dao.ServicesDao;
import com.services.entity.ServicesEntity;
import com.services.request.AddServiceRequest;
import com.services.response.Response;
import com.services.response.ServicesResponse;
import com.services.service.Services;
import com.services.service.exceptions.ServiceAlreadyExistsException;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@Service
public class ServicesImpl implements Services {

	@Autowired
	private ServicesDao servicesDao;

	

	@Autowired
	private MinioConfig minioConfig;

	@Override
	public Response addService(AddServiceRequest request, MultipartFile serviceImage) throws IOException, InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException {
		Response response = new Response();
		ServicesEntity servicesEntity = new ServicesEntity();
		BeanUtils.copyProperties(request, servicesEntity);
		Boolean existsByServiceName = servicesDao.existsByServiceName(request.getServiceName());
		if (existsByServiceName) {
			throw new ServiceAlreadyExistsException("service is already taken!");
		}
		MinioClient minioClient = minioConfig.minioClient();
		String bucketName="nexgenhub";
		boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
		if (!found) {
			minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
		}
		String fileName = request.getServiceName()+serviceImage.getOriginalFilename();
		InputStream inputStream = serviceImage.getInputStream();
		minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(fileName)
				.stream(inputStream, serviceImage.getSize(), -1).contentType(serviceImage.getContentType()).build());
		servicesEntity.setServiceImageUrl(fileName);
		ServicesEntity servicesSaved = servicesDao.save(servicesEntity);
		if (servicesSaved.getServiceId()!=null) {
			response.setResponseMessage("service added successfully");
			response.setResponseStatus("1");
		}
		return response;
	}

	@Override
	public List<ServicesResponse> findAllServices() {
		List<ServicesResponse> listOfServices = new ArrayList<>();
		List<ServicesEntity> allServices = servicesDao.findAll();
		
		allServices.forEach(service -> {
			ServicesResponse response = new ServicesResponse();
//			response.setServiceId(service.getServiceId());
			response.setServiceName(service.getServiceName());
			response.setServiceDescription(service.getServiceDescription());
			
			listOfServices.add(response);
		});
		return listOfServices;
	}

}
