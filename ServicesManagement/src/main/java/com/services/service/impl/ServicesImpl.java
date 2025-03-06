package com.services.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.services.dao.ImageDao;
import com.services.dao.ServicesDao;
import com.services.entity.ImageEntity;
import com.services.entity.ServicesEntity;
import com.services.request.AddServiceRequest;
import com.services.response.Response;
import com.services.response.ServicesResponse;
import com.services.service.Services;
import com.services.service.exceptions.ServiceAlreadyExistsException;

@Service
public class ServicesImpl implements Services {

	@Autowired
	private ServicesDao servicesDao;

	@Autowired
	private ImageDao imageDao;

	@Override
	public Response addService(AddServiceRequest request, MultipartFile serviceImage) throws IOException {
		Response response = new Response();
		ServicesEntity servicesEntity = new ServicesEntity();
		BeanUtils.copyProperties(request, servicesEntity);
		ImageEntity image = new ImageEntity();
		image.setImageName(serviceImage.getOriginalFilename());
		image.setImageData(serviceImage.getBytes());
		ImageEntity savedImage = imageDao.save(image);
		Boolean existsByServiceName = servicesDao.existsByServiceName(request.getServiceName());
		if (existsByServiceName) {
			throw new ServiceAlreadyExistsException("service is already taken!");
		}
		servicesEntity.setServiceImageId(savedImage.getImageId());
		ServicesEntity servicesSaved = servicesDao.save(servicesEntity);
		if (servicesSaved.getServiceId() > 0L) {
			response.setResponseMessage("service added successfully");
			response.setResponseStatus("1");
		}
		return response;
	}

	@Override
	public List<ServicesResponse> findAllServices() {
		List<ServicesResponse> listOfServices = new ArrayList<>();
		List<ServicesEntity> allServices = servicesDao.findAll();
		List<ImageEntity> allImages = imageDao.findAll();
		Map<Integer, Object> imageMap = allImages.stream()
				.collect(Collectors.toMap(ImageEntity::getImageId, image -> image));
		allServices.forEach(service -> {
			ServicesResponse response = new ServicesResponse();
			response.setServiceId(service.getServiceId());
			response.setServiceName(service.getServiceName());
			response.setServiceDescription(service.getServiceDescription());
			ImageEntity imageEntity = (ImageEntity) imageMap.get(service.getServiceImageId());
			if (imageEntity != null) {
				String base64Image = Base64.getEncoder().encodeToString(imageEntity.getImageData());
				response.setServiceImage("image url");
				response.setServiceImageName(imageEntity.getImageName());
			} else {
				response.setServiceImage(null);
				response.setServiceImageName(null);
			}
			listOfServices.add(response);
		});
		return listOfServices;
	}

}
