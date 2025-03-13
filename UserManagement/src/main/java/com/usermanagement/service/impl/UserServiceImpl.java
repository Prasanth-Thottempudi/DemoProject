package com.usermanagement.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.usermanagement.dao.UserDetailsDao;
import com.usermanagement.entity.UserDetailsEntity;
import com.usermanagement.exceptions.BussinessPatnerException;
import com.usermanagement.exceptions.UserAlreadyExistsException;
import com.usermanagement.externalservices.ServiceClient;
import com.usermanagement.request.AddBusinessPatnerRequest;
import com.usermanagement.request.UserDetailsAndBusinessRequest;
import com.usermanagement.request.UserDetailsRequest;
import com.usermanagement.response.AddBusinessPatnerResponse;
import com.usermanagement.response.Response;
import com.usermanagement.service.MinioServices;
import com.usermanagement.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	private ServiceClient serviceClient;
	
	@Autowired
	private MinioServices minioServices;

	@Override
	public Response addUser(UserDetailsRequest request) {

		Response response = new Response();
		boolean isEmailFound = userDetailsDao.existsByEmail(request.getEmail());
		if (isEmailFound) {
			throw new UserAlreadyExistsException("user is already existed with given email" + request.getEmail());
		}
		UserDetailsEntity userDetailsEntity = new UserDetailsEntity();

		BeanUtils.copyProperties(request, userDetailsEntity);
		userDetailsDao.save(userDetailsEntity);

		return response;
	}

	

	public String getDashboard() {
		return serviceClient.getDashboard();
	}

	

	@Override
	@Transactional
	public Response addBusinessPatner(MultipartFile serviceImage, MultipartFile businessLogo,
			MultipartFile businessLicenseProof, UserDetailsAndBusinessRequest request) {
		Response res = new Response();
		String serviceImageFileName=null;
		String businessLogoFileName=null;
		String businessLicenseProofFileName=null;
		try {
			UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
			userDetailsEntity.setFullName(request.getFullName());
			userDetailsEntity.setEmail(request.getEmail());
			userDetailsEntity.setGender(request.getGender());
			userDetailsEntity.setDateOfBirth(request.getDateOfBirth());	
			 serviceImageFileName=serviceImage.getOriginalFilename();
			 businessLogoFileName=businessLogo.getOriginalFilename();
			 businessLicenseProofFileName=businessLicenseProof.getOriginalFilename();
			userDetailsEntity.setNationality(request.getNationality());
			userDetailsEntity.setRoleId("USER");
			userDetailsEntity.setPhoneNumber(request.getPhoneNumber());	

			
			
			String serviceImageFileUrl=minioServices.saveImage(serviceImage, serviceImageFileName).getImageUrl();			
			System.out.println(serviceImageFileUrl);
			

			String businessLicenseProofUrl=minioServices.saveImage(businessLicenseProof, businessLicenseProofFileName).getImageUrl();			

			String businessLogoUrl=minioServices.saveImage(businessLogo, businessLogoFileName).getImageUrl();			

			
			
			AddBusinessPatnerRequest businessPatnerRequest=new AddBusinessPatnerRequest();
			businessPatnerRequest.setBusinessDescription(request.getBusinessDescription());
			businessPatnerRequest.setBusinessEmail(request.getBusinessEmail());
			businessPatnerRequest.setBusinessLicenseProofUrl(businessLicenseProofUrl);
			businessPatnerRequest.setBusinessLogoUrl(businessLogoUrl);
			businessPatnerRequest.setBusinessMobileNumber(request.getBusinessMobileNumber());
			businessPatnerRequest.setBusinessName(request.getBusinessName());
			businessPatnerRequest.setServiceName(request.getServiceName());
			businessPatnerRequest.setBusinessRegion(request.getBusinessRegion());
			businessPatnerRequest.setEmergenceyContact(request.getEmergencyContact());
			businessPatnerRequest.setLanguage(request.getLanguage());	
			 ResponseEntity<AddBusinessPatnerResponse> businessPatner = serviceClient.addBusinessPatner(businessPatnerRequest);
			System.out.println(businessPatner.getBody());
			
			userDetailsEntity.setIdProofUrl(serviceImageFileUrl);
			userDetailsEntity.setServiceBusinessId(businessPatner.getBody().getBusinessPatnerid());
			UserDetailsEntity savedUserDetails = userDetailsDao.save(userDetailsEntity);
			System.out.println(savedUserDetails);
			
			
			res.setResponseMessage("registered successfully");
			res.setResponseStatus("1");
		}catch (Exception e) {
			
			System.out.println(e.getMessage());
//            rollbackUploadedFiles(serviceImageFileName, businessLogoFileName, businessLicenseProofFileName);
			
            res.setResponseMessage("Error occurred while adding business partner: " + e.getMessage());
            res.setResponseStatus("0");
            throw new BussinessPatnerException("Failed to add business partner");
        }
		
		return res;
	}
	
	private void rollbackUploadedFiles(String... fileNames) {
        for (String fileName : fileNames) {
            if (fileName != null) {
                try {
                    serviceClient.deleteImage(fileName);
                } catch (Exception e) {
                    System.err.println("Failed to delete file from MinIO: " + fileName);
                }
            }
        }
    }
}
